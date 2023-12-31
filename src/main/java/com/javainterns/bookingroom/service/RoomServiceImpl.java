package com.javainterns.bookingroom.service;

import com.javainterns.bookingroom.exceptions.NoRecordFoundException;
import com.javainterns.bookingroom.exceptions.StartTimeIsGreaterThanEndTime;
import com.javainterns.bookingroom.model.Room;
import com.javainterns.bookingroom.model.dto.RoomRequest;
import com.javainterns.bookingroom.model.mapper.RoomRequestMapper;
import com.javainterns.bookingroom.repository.RoomRepository;
import com.javainterns.bookingroom.utils.Messages;
import com.javainterns.bookingroom.utils.TimeValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService {

    private static final String ROOM_NOT_FOUND = "room.not.found";

    private final RoomRepository roomRepository;
    private final RoomRequestMapper roomRequestMapper;
    private final TimeValidator timevalidation;
    private final Messages messages;

    @Override
    public RoomRequest create(RoomRequest roomRequest) {
        timevalidation.isValidTimeRange(roomRequest.getFinishTime(), roomRequest.getStartTime());
        Room room = roomRequestMapper.toRoom(roomRequest);
        return roomRequestMapper.toRoomRequest(roomRepository.save(room));
    }

    @Override
    public RoomRequest findById(Long id) {
        Room room = roomRepository
                .findById(id)
                .orElseThrow(() ->
                        new NoRecordFoundException(messages.get(ROOM_NOT_FOUND))
                );
        if (
                Boolean.FALSE.equals(room.isActive())
        ) throw new NoRecordFoundException(messages.get(ROOM_NOT_FOUND));
        return roomRequestMapper.toRoomRequest(room);
    }

    @Override
    public List<RoomRequest> findAll() {
        return roomRepository.findByIsActiveTrue().stream().map(roomRequestMapper::toRoomRequest).toList();
    }

    @Override
    public RoomRequest update(RoomRequest roomRequest) {
      timevalidation.isValidTimeRange(roomRequest.getFinishTime(), roomRequest.getStartTime());
        roomRepository
                .findById(roomRequest.getId())
                .orElseThrow(() ->
                        new NoRecordFoundException(messages.get(ROOM_NOT_FOUND))
                );
        Room room = roomRequestMapper.toRoom(roomRequest);
        return roomRequestMapper.toRoomRequest(roomRepository.save(room));
    }

    @Override
    public void delete(Long id) {
        Room room = roomRepository
                .findById(id)
                .orElseThrow(() ->
                        new NoRecordFoundException(messages.get(ROOM_NOT_FOUND))
                );
        room.setActive(false);
        roomRepository.save(room);
    }

    @Override
    public Room findRoom(Long id) {
        Room room = roomRepository
                .findById(id)
                .orElseThrow(() ->
                        new NoRecordFoundException(messages.get(ROOM_NOT_FOUND))
                );
        if (
                Boolean.FALSE.equals(room.isActive())
        ) throw new NoRecordFoundException(messages.get(ROOM_NOT_FOUND));
        return room;
    }
}
