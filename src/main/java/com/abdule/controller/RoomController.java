package com.abdule.controller;

import com.abdule.dto.request.RoomRequestDTO;
import com.abdule.dto.response.RoomResponseDTO;
import com.abdule.services.RoomService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/room")
public class RoomController {

    private final RoomService roomService;

    @PostMapping
    public ResponseEntity<RoomResponseDTO> createNewRoom(@Valid @RequestBody RoomRequestDTO room) {

        RoomResponseDTO saved = roomService.createNewRoom(room);
        return ResponseEntity.status(201).body(saved);
    }

    @GetMapping
    public ResponseEntity<List<RoomResponseDTO>> getAllRooms() {
        return ResponseEntity.ok(roomService.findAllRooms());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoomResponseDTO> getRoomById(@PathVariable UUID id) {
        return ResponseEntity.ok(roomService.findRoomById(id));
    }

    @PutMapping ("/{id}")

    public ResponseEntity<RoomResponseDTO> updateRoom(
            @PathVariable UUID id,
            @Valid @RequestBody RoomRequestDTO request
    ) {
        RoomResponseDTO updated = roomService.updateRoom(id, request);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRoom(@PathVariable UUID id) {
        roomService.deleteRoom(id);
        return ResponseEntity.noContent().build();
    }
}
