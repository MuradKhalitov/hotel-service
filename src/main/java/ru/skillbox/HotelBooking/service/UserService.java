package ru.skillbox.HotelBooking.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.skillbox.HotelBooking.dto.ResponseList;
import ru.skillbox.HotelBooking.dto.user.UpsertUserRequest;
import ru.skillbox.HotelBooking.dto.user.UserResponse;
import ru.skillbox.HotelBooking.model.Role;
import ru.skillbox.HotelBooking.model.User;
import ru.skillbox.HotelBooking.mapper.UserMapper;
import ru.skillbox.HotelBooking.repository.UserRepository;
import ru.skillbox.HotelBooking.utils.CopyUtils;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper mapper;
    private final PasswordEncoder passwordEncoder;

    public ResponseList<UserResponse> findAll(int pageNumber, int pageSize) {
        Page<User> page = userRepository.findAll(PageRequest.of(pageNumber, pageSize));
        ResponseList<UserResponse> response = new ResponseList<>();
        response.setItems(page.getContent().stream().map(this::userToResponse).toList());
        response.setTotalCount(page.getTotalElements());
        return response;
    }

    public UserResponse findById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Пользователь не найден"));
        return userToResponse(user);
    }

    public UserResponse add(UpsertUserRequest request, Role role) {
        User user = mapper.requestToUser(request);
        if (userRepository.existsByUsername(user.getUsername())
                || userRepository.existsByEmail(user.getEmail())) {
            throw new EntityExistsException("Пользователь с таким именем или адресом электронной почты уже существует");
        } else {
            user.setRoles(Collections.singleton(role));
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            return userToResponse(userRepository.save(user));
        }
    }

    public UserResponse update(Long id, UpsertUserRequest request,  Role role) {
        request.setPassword(passwordEncoder.encode(request.getPassword()));
        User user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Пользователь не найден"));

        CopyUtils.copyNonNullProperties(mapper.requestToUser(request), user);
        user.getRoles().add(role);

        return userToResponse(userRepository.save(user));
    }

    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username).get();
    }

    public UserResponse userToResponse(User user) {
        return mapper.userToResponse(user);
    }
}
