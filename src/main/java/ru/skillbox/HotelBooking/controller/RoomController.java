package ru.skillbox.HotelBooking.controller;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import ru.skillbox.HotelBooking.dto.ResponseList;
import ru.skillbox.HotelBooking.dto.room.RoomRequest;
import ru.skillbox.HotelBooking.dto.room.RoomResponse;
import ru.skillbox.HotelBooking.dto.room.UpsertRoomRequest;
import ru.skillbox.HotelBooking.service.RoomService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/api/v1/rooms")
@RequiredArgsConstructor
public class RoomController {
    private final RoomService roomService;

    @PostMapping("/filter")
    public ResponseList<RoomResponse> filter(@RequestBody @Valid RoomRequest request) {
        log.info("был вызван метод GET /api/v1/rooms");
        ResponseList<RoomResponse> response = roomService.filter(request);
        log.info("метод GET /api/v1/rooms вернул ответ");
        return response;
    }

    @GetMapping
    public ResponseList<RoomResponse> findAll(
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "10") int pageSize) {
        log.info("был вызван метод GET /api/v1/rooms");
        ResponseList<RoomResponse> response = roomService.findAll(pageNumber, pageSize);
        log.info("метод GET /api/v1/rooms вернул ответ");
        return response;
    }


    @GetMapping("/{id}")
    public RoomResponse findById(@PathVariable(required = false) Long id) {
        log.info("был вызван метод GET /api/v1/rooms/{id}");
        RoomResponse roomResponse = roomService.findById(id);
        log.info("метод GET /api/v1/rooms/{} вернул ответ", id);
        return roomResponse;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public RoomResponse create(@RequestBody @Valid UpsertRoomRequest request) {
        log.info("был вызван метод POST /api/v1/rooms");
        RoomResponse roomResponse = roomService.add(request);
        log.info("метод POST /api/v1/rooms вернул ответ");
        return roomResponse;
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public RoomResponse update(@PathVariable(required = false) Long id, @RequestBody UpsertRoomRequest request) {
        log.info("был вызван метод PUT /api/v1/rooms/{id}");
        RoomResponse roomResponse = roomService.update(id, request);
        log.info("метод PUT /api/v1/rooms/{} вернул ответ", id);
        return roomResponse;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void delete(@PathVariable Long id) {
        log.info("был вызван метод DELETE /api/v1/rooms/{}", id);
        roomService.delete(id);
        log.info("метод DELETE /api/v1/rooms/{} вернул ответ", id);
    }
}
