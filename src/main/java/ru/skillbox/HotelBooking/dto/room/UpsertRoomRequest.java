package ru.skillbox.HotelBooking.dto.room;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpsertRoomRequest
{
    @NotBlank(message = "введите имя")
    private String name;
    @NotBlank(message = "введите описание")
    private String description;
    @NotNull(message = "укажите номер комнаты")
    private Integer number;
    @NotNull(message = "укажите стоимость")
    private Integer price;
    @NotNull(message = "укажите количество мест")
    private Integer capacity;
    @NotNull(message = "укажите дату когда комната не доступна")
    private LocalDate unavailableAt;
    @NotNull(message = "укажите id отеля в котором расположена комната")
    private Long hotelId;
}
