package ru.skillbox.HotelBooking.mapper;

import ru.skillbox.HotelBooking.dto.booking.BookingResponse;
import ru.skillbox.HotelBooking.dto.booking.UpsertBookingRequest;
import ru.skillbox.HotelBooking.model.Booking;
import ru.skillbox.HotelBooking.repository.RoomRepository;
import ru.skillbox.HotelBooking.repository.UserRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE,
        uses = {RoomMapper.class, UserMapper.class})
public abstract class BookingMapper {
    @Autowired
    protected UserRepository userRepository;
    @Autowired
    protected RoomRepository roomRepository;

    @Mappings({
            @Mapping(target = "user",
                    expression = "java(userRepository.findById(request.getUserId()).get())"),
            @Mapping(target = "room",
                    expression = "java(roomRepository.findById(request.getRoomId()).get())"),
    })
    public abstract Booking requestToBooking(UpsertBookingRequest request);

    @Mapping(source = "id", target = "bookingId")
    public abstract BookingResponse bookingToResponse(Booking booking);

}
