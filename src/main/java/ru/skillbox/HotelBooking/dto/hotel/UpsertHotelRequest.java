package ru.skillbox.HotelBooking.dto.hotel;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpsertHotelRequest
{
    @NotBlank(message = "введите имя")
    private String name;
    @NotBlank(message = "введите заголовок объявления")
    private String adsTitle;
    @NotBlank(message = "укажите город")
    private String city;
    @NotBlank(message = "укажите адрес")
    private String address;
    @NotNull(message = "укажите дистанцию до центра в метрах")
    private Integer distanceFromCentre;
}
