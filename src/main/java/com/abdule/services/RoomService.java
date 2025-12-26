package com.abdule.services;

import com.abdule.dto.request.RoomRequestDTO;
import com.abdule.dto.response.RoomResponseDTO;
import com.abdule.entities.Room;
import com.abdule.repositories.RoomRepository;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class RoomService {

    private final RoomRepository roomRepository;

    public RoomResponseDTO createNewRoom(RoomRequestDTO room) {

        Room newRoom = Room.builder()
                .buildingName(room.getBuildingName())
                .roomNumber(room.getRoomNumber())
                .capacity(room.getCapacity())
                .build();
        Room saved = roomRepository.save(newRoom);

        return  RoomResponseDTO.builder()
                .roomId(saved.getRoomId())
                .buildingName(saved.getBuildingName())
                .roomNumber(saved.getRoomNumber())
                .capacity(saved.getCapacity())
                .build();
    }

    public List<RoomResponseDTO> findAllRooms() {

        List<Room> rooms = roomRepository.findAll();

        List<RoomResponseDTO> responses = rooms.stream()
                .map(r -> RoomResponseDTO.builder()
                        .roomId(r.getRoomId())
                        .buildingName(r.getBuildingName())
                        .roomNumber(r.getRoomNumber())
                        .capacity(r.getCapacity())
                        .build())
                .toList();
        return responses;
    }

    public RoomResponseDTO findRoomById(@Valid UUID id) {
        Room room = roomRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Room not found"));

        return RoomResponseDTO.builder()
                .roomId(room.getRoomId())
                .buildingName(room.getBuildingName())
                .roomNumber(room.getRoomNumber())
                .capacity(room.getCapacity())
                .build();
    }

    public RoomResponseDTO updateRoom(UUID id, @Valid RoomRequestDTO request) {

        Room existingRoom = roomRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Room not found"));

        existingRoom.setBuildingName(request.getBuildingName());
        existingRoom.setRoomNumber(request.getRoomNumber());
        existingRoom.setCapacity(request.getCapacity());

        Room saved = roomRepository.save(existingRoom);

        return RoomResponseDTO.builder()
                .roomId(saved.getRoomId())
                .buildingName(saved.getBuildingName())
                .roomNumber(saved.getRoomNumber())
                .capacity(saved.getCapacity())
                .build();
    }

    public void deleteRoom(UUID id) {

        Room room = roomRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Room not found"));
        roomRepository.delete(room);
    }



}
