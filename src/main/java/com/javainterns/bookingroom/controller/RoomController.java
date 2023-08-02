package com.javainterns.bookingroom.controller;


import com.javainterns.bookingroom.model.Room;
import com.javainterns.bookingroom.model.dto.RoomRequest;
import com.javainterns.bookingroom.service.RoomService;
import jakarta.validation.Valid;
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
    public ResponseEntity<RoomRequest> create(@Valid @RequestBody RoomRequest roomRequest) {
        return ResponseEntity.ok(roomService.create(roomRequest));
    }

    @PutMapping("/{id}")
    public ResponseEntity<RoomRequest> update(@Valid @RequestBody RoomRequest roomRequest, @PathVariable @NotNull Long id) {
        return ResponseEntity.ok(roomService.update(roomRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        roomService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
