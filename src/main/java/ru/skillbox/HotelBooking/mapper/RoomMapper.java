package ru.skillbox.HotelBooking.mapper;

import ru.skillbox.HotelBooking.dto.room.RoomResponse;
import ru.skillbox.HotelBooking.dto.room.UpsertRoomRequest;
import ru.skillbox.HotelBooking.model.Room;
import ru.skillbox.HotelBooking.repository.HotelRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public abstract class RoomMapper {
    @Autowired
    protected HotelRepository repository;

    @Mapping(target = "hotel", expression = "java(repository.getReferenceById(request.getHotelId()))")
    public abstract Room requestToRoom(UpsertRoomRequest request);


    @Mappings({
            @Mapping(source = "id", target = "roomId"),
            @Mapping(source = "hotel.id", target = "hotelId")
    })
    public abstract RoomResponse roomToResponse(Room room);
}
