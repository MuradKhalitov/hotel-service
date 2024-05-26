package ru.skillbox.HotelBooking.controller;

import org.springframework.http.HttpStatus;
import ru.skillbox.HotelBooking.dto.ResponseList;
import ru.skillbox.HotelBooking.dto.user.UpsertUserRequest;
import ru.skillbox.HotelBooking.dto.user.UserResponse;
import ru.skillbox.HotelBooking.model.Role;
import ru.skillbox.HotelBooking.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService service;

    @GetMapping
    public ResponseList<UserResponse> findAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        log.info("был вызван метод GET /api/v1/users");
        ResponseList<UserResponse> response = service.findAll(page, size);
        log.info("метод GET /api/v1/users вернул ответ");
        return response;
    }
    @GetMapping("/{id}")
    public UserResponse findById(@PathVariable(required = false) Long id) {
        log.info("был вызван метод GET /api/v1/users/{id}");
        UserResponse userResponse = service.findById(id);
        log.info("метод GET /api/v1/users/{} вернул ответ", id);
        return userResponse;
    }
    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponse create(@RequestBody @Valid UpsertUserRequest request,
                               @RequestParam Role role) {
        log.info("был вызван метод POST /api/v1/users");
        UserResponse userResponse = service.add(request, role);
        log.info("метод POST /api/v1/users вернул ответ");
        return userResponse;
    }

    @PutMapping("/{id}")
    public UserResponse update(@PathVariable(required = false) Long id, @RequestBody UpsertUserRequest request) {
        log.info("был вызван метод PUT /api/v1/users/{id}");
        UserResponse userResponse = service.update(id, request);
        log.info("method PUT /api/v1/users/{} returned the response", id);
        return userResponse;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        log.info("был вызван метод DELETE /api/v1/users/{}", id);
        service.delete(id);
        log.info("метод DELETE /api/v1/users/{} вернул ответ", id);
    }
}
