package ru.skillbox.HotelBooking.dto.user;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpsertUserRequest
{
    @NotBlank(message = "введите имя пользователя")
    private String username;
    @NotBlank(message = "введите пароль")
    private String password;
    @NotBlank(message = "укажите email")
    private String email;
}
