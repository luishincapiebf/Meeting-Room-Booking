package com.javainterns.bookingroom.controller;


import com.javainterns.bookingroom.model.Room;
import com.javainterns.bookingroom.model.dto.RoomRequest;
import com.javainterns.bookingroom.service.RoomService;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/rooms")
public class RoomController {
    @Autowired
    RoomService roomService;

    @GetMapping("/")
    public ResponseEntity<List<RoomRequest>> findAll() {
        return ResponseEntity.ok(roomService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoomRequest> findById(@PathVariable @NotNull Long id) {
        return ResponseEntity.ok(roomService.findById(id));
    }

    @PostMapping("/")
    public RoomRequest create(@RequestBody RoomRequest roomRequest) {
        return roomService.create(roomRequest);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RoomRequest> update(@RequestBody Room room, @PathVariable @NotNull Long id) {
        return ResponseEntity.ok(roomService.update(room));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        roomService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
