package ru.skillbox.HotelBooking.controller;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import ru.skillbox.HotelBooking.dto.ResponseList;
import ru.skillbox.HotelBooking.dto.booking.BookingResponse;
import ru.skillbox.HotelBooking.dto.booking.UpsertBookingRequest;
import ru.skillbox.HotelBooking.service.BookingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/api/v1/booking")
@RequiredArgsConstructor
public class BookingController
{
    private final BookingService service;

    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseList<BookingResponse> findAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        log.info("был вызван метод GET /api/v1/hotels");
        ResponseList<BookingResponse> response = service.findAll(page, size);
        log.info("метод GET /api/v1/hotels вернул ответ");
        return response;
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BookingResponse book(@RequestBody @Valid UpsertBookingRequest request) {
        log.info("был вызван метод POST /api/v1/booking");
        BookingResponse bookingResponse = service.bookRoom(request);
        log.info("метод POST /api/v1/booking вернул ответ");
        return bookingResponse;
    }


}
