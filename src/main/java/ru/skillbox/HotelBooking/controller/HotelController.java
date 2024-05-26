package ru.skillbox.HotelBooking.controller;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import ru.skillbox.HotelBooking.dto.ResponseList;
import ru.skillbox.HotelBooking.dto.hotel.HotelResponse;
import ru.skillbox.HotelBooking.dto.hotel.UpsertHotelRequest;
import ru.skillbox.HotelBooking.model.Hotel;
import ru.skillbox.HotelBooking.service.HotelService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/api/v1/hotels")
@RequiredArgsConstructor
public class HotelController {
    private final HotelService service;

    @GetMapping
    public ResponseList<HotelResponse> findAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        log.info("был вызван метод GET /api/v1/hotels");
        ResponseList<HotelResponse> response = service.findAll(page, size);
        log.info("метод GET /api/v1/hotels вернул ответ");
        return response;
    }

    @GetMapping("/{id}")
    public HotelResponse findById(@PathVariable(required = false) Long id) {
        log.info("был вызван метод GET /api/v1/hotels/{id}");
        HotelResponse hotelResponse = service.findById(id);
        log.info("метод GET /api/v1/hotels/{} вернул ответ", id);
        return hotelResponse;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public HotelResponse create(@RequestBody @Valid UpsertHotelRequest request) {
        log.info("был вызван метод POST /api/v1/hotels");
        HotelResponse hotelResponse = service.add(request);
        log.info("метод POST /api/v1/hotels вернул ответ");
        return hotelResponse;
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public HotelResponse update(@PathVariable(required = false) Long id, @RequestBody UpsertHotelRequest request) {
        log.info("был вызван метод PUT /api/v1/hotels/{id}");
        HotelResponse hotelResponse = service.update(id, request);
        log.info("метод PUT /api/v1/hotels/{} вернул ответ", id);
        return hotelResponse;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void delete(@PathVariable Long id) {
        log.info("был вызван метод DELETE /api/v1/hotels/{}", id);
        service.delete(id);
        log.info("метод DELETE /api/v1/hotels/{} вернул ответ", id);
    }

    @PutMapping("/rating/{id}")
    public HotelResponse hotelRate(@PathVariable Long id,
                                   @RequestParam @Min(1) @Max(5) Integer rating) {

        log.info("method PUT /api/v1/hotels/rating/{id} was called");
        HotelResponse hotelResponse = service.hotelRate(id, rating);
        log.info("method PUT /api/v1/hotels/rating/{} returned the response", id);
        return hotelResponse;
    }
}
