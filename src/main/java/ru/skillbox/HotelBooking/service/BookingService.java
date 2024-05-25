package ru.skillbox.HotelBooking.service;

import org.springframework.transaction.annotation.Transactional;
import ru.skillbox.HotelBooking.dto.ResponseList;
import ru.skillbox.HotelBooking.dto.booking.BookingResponse;
import ru.skillbox.HotelBooking.dto.booking.UpsertBookingRequest;
import ru.skillbox.HotelBooking.dto.room.RoomResponse;
import ru.skillbox.HotelBooking.model.Booking;
import ru.skillbox.HotelBooking.mapper.BookingMapper;
import ru.skillbox.HotelBooking.repository.BookingRepository;
import jakarta.persistence.EntityExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class BookingService {
    private final BookingRepository bookingRepository;
    private final RoomService roomService;
    private final BookingMapper mapper;


    public ResponseList<BookingResponse> findAll(int pageNumber, int pageSize) {
        Page<Booking> page = bookingRepository.findAll(PageRequest.of(pageNumber, pageSize));
        ResponseList<BookingResponse> response = new ResponseList<>();
        response.setItems(page.getContent().stream().map(mapper::bookingToResponse).toList());
        response.setTotalCount(page.getTotalElements());
        return response;
    }

    @Transactional
    public BookingResponse bookRoom(UpsertBookingRequest request) {
        Booking booking = mapper.requestToBooking(request);
        // Проверяем, доступна ли комната на указанные даты
        if (isRoomUnavailable(booking.getRoom().getId(), booking.getArrivalDate(), booking.getDepartureDate())) {
            throw new EntityExistsException("Комната занята. Выбирайте другие даты или номер!");
        }
        booking = bookingRepository.save(booking);
        return mapper.bookingToResponse(booking);
    }

    private boolean isRoomUnavailable(Long roomId, LocalDate arrivalDate, LocalDate departureDate) {
        int pageNumber = 0;
        int pageSize = 1;
        ResponseList<RoomResponse> response = roomService.findAll(pageNumber, pageSize);

        return response.getItems().stream()
                .anyMatch(room -> room.getRoomId().equals(roomId) &&
                        room.getUnavailableAt() != null &&
                        (room.getUnavailableAt().isEqual(arrivalDate) || room.getUnavailableAt().isEqual(departureDate)));
    }
}