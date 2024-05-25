package ru.skillbox.HotelBooking.dto.booking;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpsertBookingRequest
{
    @NotNull(message = "укажите дату прибытия")
    private LocalDate arrivalDate;
    @NotNull(message = "укажите дату выезда")
    private LocalDate departureDate;
    @NotNull(message = "укажите id комнаты")
    private Long roomId;
    @NotNull(message = "укажите id пользователя")
    private Long userId;
}
