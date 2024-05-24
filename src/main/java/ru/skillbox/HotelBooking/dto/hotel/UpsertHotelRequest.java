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
    @NotBlank(message = "нужно указать имя")
    private String name;
    @NotBlank(message = "нужно указать заголовок объявления")
    private String adsTitle;
    @NotBlank(message = "нужно указать город")
    private String city;
    @NotBlank(message = "нужно указать адрес")
    private String address;
    @NotNull(message = "нужно указать дистанцию до центра в метрах")
    private Integer distanceFromCentre;
}
