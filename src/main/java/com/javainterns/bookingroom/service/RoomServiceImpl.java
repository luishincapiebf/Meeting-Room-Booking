package com.javainterns.bookingroom.service;

import com.javainterns.bookingroom.model.Room;
import com.javainterns.bookingroom.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.persistence.EntityNotFoundException;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RoomServiceImpl implements RoomService{
    @Autowired
    RoomRepository roomRepository;
    @Override
    public void createRoom(Room room) {
        roomRepository.save(room);
    }

    @Override
    public Optional<Room> getByIdRoom(long id) {
        Optional<Room> findUserOptional = roomRepository.findById(id);
        Room findUser = findUserOptional.orElseThrow(EntityNotFoundException::new);
        return Optional.ofNullable(findUser);

    }

    @Override
    public List<Room> getRooms() {

        List<Room> room = new ArrayList();
        room = roomRepository.findAll();
        return room;
    }

    @Override
    public Room updateRoom(Room room) {
        roomRepository.save(room);
        return room;
    }
    @Override
    public void deleteRoom(Long id) {
        roomRepository.deleteById(id);
    }

}