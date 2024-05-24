package ru.skillbox.HotelBooking.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Request
{
    @NotNull(message = "требуется указать размер страницы")
    protected Integer pageSize;
    @NotNull(message = "требуется указать номер страницы")
    protected Integer pageNumber;
}
