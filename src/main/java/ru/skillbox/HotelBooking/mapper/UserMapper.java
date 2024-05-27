package ru.skillbox.HotelBooking.mapper;

import ru.skillbox.HotelBooking.dto.event.UserRegisterEvent;
import ru.skillbox.HotelBooking.dto.user.UpsertUserRequest;
import ru.skillbox.HotelBooking.dto.user.UserResponse;
import ru.skillbox.HotelBooking.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper
{
    User requestToUser(UpsertUserRequest request);
    @Mapping(source = "id", target = "userId")
    UserResponse userToResponse(User user);

    @Mapping(source = "id", target = "userId")
    UserRegisterEvent userToEvent(User user);
}
