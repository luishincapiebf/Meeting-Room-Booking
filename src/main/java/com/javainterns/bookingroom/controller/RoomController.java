package com.javainterns.bookingroom.controller;


import com.javainterns.bookingroom.model.Room;
import com.javainterns.bookingroom.model.dto.RoomRequest;
import com.javainterns.bookingroom.service.RoomService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(summary = "Get all rooms")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Users found"),
    })
    @GetMapping("/")
    public ResponseEntity<List<RoomRequest>> findAll() {
        return ResponseEntity.ok(roomService.findAll());
    }

    @Operation(summary = "Get a room by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User found"),
            @ApiResponse(responseCode = "404", description = "User not found",content = @Content(schema = @Schema(implementation = Void.class)))
    })
    @GetMapping("/{id}")
    public ResponseEntity<RoomRequest> findById(@PathVariable @NotNull Long id) {
        return ResponseEntity.ok(roomService.findById(id));
    }

    @Operation(summary = "Create a room")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Room created"),
            @ApiResponse(responseCode = "404", description = "Room not found", content = @Content(schema = @Schema(implementation = Void.class))),
            @ApiResponse(responseCode = "400", description = "Start time must not be greater than end time", content = @Content(schema = @Schema(implementation = Void.class)))
    })
    @PostMapping("/")
    public ResponseEntity<RoomRequest> create(@Valid @RequestBody RoomRequest roomRequest) {
        return ResponseEntity.ok(roomService.create(roomRequest));
    }

    @Operation(summary = "Update a room")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Room updated"),
            @ApiResponse(responseCode = "404", description = "Room not found", content = @Content(schema = @Schema(implementation = Void.class))),
            @ApiResponse(responseCode = "400", description = "Start time must not be greater than end time", content = @Content(schema = @Schema(implementation = Void.class)))
    })
    @PutMapping("/{id}")
    public ResponseEntity<RoomRequest> update(@Valid @RequestBody RoomRequest roomRequest, @PathVariable @NotNull Long id) {
        return ResponseEntity.ok(roomService.update(roomRequest));
    }

    @Operation(summary = "Delete a room by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User deleted"),
            @ApiResponse(responseCode = "404", description = "User not found", content = @Content(schema = @Schema(implementation = Void.class))),
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        roomService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
