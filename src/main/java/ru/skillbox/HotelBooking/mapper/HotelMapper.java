package ru.skillbox.HotelBooking.mapper;

import ru.skillbox.HotelBooking.dto.hotel.HotelResponse;
import ru.skillbox.HotelBooking.dto.hotel.UpsertHotelRequest;
import ru.skillbox.HotelBooking.model.Hotel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface HotelMapper
{
    Hotel requestToHotel(UpsertHotelRequest request);
    @Mapping(source = "id", target = "hotelId")
    HotelResponse hotelToResponse(Hotel hotel);
}
