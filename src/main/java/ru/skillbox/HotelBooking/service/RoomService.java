package ru.skillbox.HotelBooking.service;

import ru.skillbox.HotelBooking.dto.ResponseList;
import ru.skillbox.HotelBooking.dto.room.RoomResponse;
import ru.skillbox.HotelBooking.dto.room.UpsertRoomRequest;
import ru.skillbox.HotelBooking.model.Room;
import ru.skillbox.HotelBooking.mapper.RoomMapper;
import ru.skillbox.HotelBooking.repository.RoomRepository;
import ru.skillbox.HotelBooking.utils.CopyUtils;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoomService
{
    private final RoomRepository roomRepository;
    private final RoomMapper mapper;

    public ResponseList<RoomResponse> findAll(int pageNumber, int pageSize) {
        Page<Room> page = roomRepository.findAll(PageRequest.of(pageNumber, pageSize));
        ResponseList<RoomResponse> response = new ResponseList<>();
        response.setItems(page.getContent().stream().map(this::roomToResponse).toList());
        response.setTotalCount(page.getTotalElements());
        return response;
    }

    public RoomResponse findById(Long id) {
        Room room = roomRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Комната не найдена"));
        return roomToResponse(room);
    }

    public RoomResponse add(UpsertRoomRequest request) {
        Room room = mapper.requestToRoom(request);
        return roomToResponse(roomRepository.save(room));
    }

    public void delete(Long id) {
        roomRepository.deleteById(id);
    }

    public RoomResponse update(Long id, UpsertRoomRequest request) {
        Room room = roomRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Комната не найдена"));

        CopyUtils.copyNonNullProperties(mapper.requestToRoom(request), room);

        return roomToResponse(roomRepository.save(room));
    }
    public RoomResponse roomToResponse(Room room) {
        return mapper.roomToResponse(room);
    }
}
