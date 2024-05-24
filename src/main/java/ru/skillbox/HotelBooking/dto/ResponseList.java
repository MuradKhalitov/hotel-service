package ru.skillbox.HotelBooking.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.skillbox.HotelBooking.dto.hotel.HotelResponse;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseList<T> implements Serializable
{
    private List<T> items;
    private Long totalCount;

    public ResponseList(List<HotelResponse> responses) {

    }
}
