package ru.skillbox.HotelBooking.service;

import ru.skillbox.HotelBooking.dto.ResponseList;
import ru.skillbox.HotelBooking.dto.hotel.HotelRequest;
import ru.skillbox.HotelBooking.dto.hotel.HotelResponse;
import ru.skillbox.HotelBooking.dto.hotel.UpsertHotelRequest;
import ru.skillbox.HotelBooking.model.Hotel;
import ru.skillbox.HotelBooking.mapper.HotelMapper;
import ru.skillbox.HotelBooking.repository.HotelRepository;
import ru.skillbox.HotelBooking.repository.HotelSpecification;
import ru.skillbox.HotelBooking.utils.CopyUtils;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HotelService {
    private final HotelRepository hotelRepository;
    private final HotelMapper mapper;

    public ResponseList<HotelResponse> filter(HotelRequest request) {
        Page<Hotel> page = hotelRepository.findAll(
                HotelSpecification.withRequest(request),
                PageRequest.of(request.getPageNumber(), request.getPageSize())
        );
        ResponseList<HotelResponse> response = new ResponseList<>();
        response.setItems(page.getContent().stream().map(this::hotelToResponse).toList());
        response.setTotalCount(page.getTotalElements());
        return response;
    }

    public ResponseList<HotelResponse> findAll(int pageNumber, int pageSize) {
        Page<Hotel> page = hotelRepository.findAll(PageRequest.of(pageNumber, pageSize));
        ResponseList<HotelResponse> response = new ResponseList<>();
        response.setItems(page.getContent().stream().map(this::hotelToResponse).toList());
        response.setTotalCount(page.getTotalElements());
        return response;
    }

    public HotelResponse findById(Long id) {
        Hotel hotel = hotelRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Отель не найден"));
        return hotelToResponse(hotel);
    }

    public HotelResponse add(UpsertHotelRequest request) {
        Hotel hotel = mapper.requestToHotel(request);
        hotel.setRating(0.0);
        hotel.setNumberOfRating(0);
        return hotelToResponse(hotelRepository.save(hotel));
    }

    public HotelResponse update(Long id, UpsertHotelRequest request) {
        Hotel hotel = hotelRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Отель не найден"));
        CopyUtils.copyNonNullProperties(mapper.requestToHotel(request), hotel);

        return hotelToResponse(hotelRepository.save(hotel));
    }

    public void delete(Long id) {
        hotelRepository.deleteById(id);
    }
    public HotelResponse hotelToResponse(Hotel hotel) {
        return mapper.hotelToResponse(hotel);
    }
    public HotelResponse hotelRate(Long id, Integer newMark) {
        Hotel hotel = hotelRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Отель не найден"));
        int numberOfRating = hotel.getNumberOfRating() + 1;
        double totalRating = hotel.getRating() * numberOfRating;
        totalRating = totalRating - hotel.getRating() + newMark;
        double rating = (double) Math.round(totalRating / numberOfRating * 10) / 10;
        hotel.setRating(rating);
        hotel.setNumberOfRating(numberOfRating);
        return hotelToResponse(hotelRepository.save(hotel));
    }
}
