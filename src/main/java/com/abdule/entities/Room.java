package com.abdule.model;


import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "rooms")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "room_id", updatable = false, nullable = false)
    private UUID roomId;

    @Column(name = "building_name", nullable = false)
    private String buildingName;

    @Column(name = "room_number", nullable = false)
    private Integer roomNumber;

    @Column(name = "capacity", nullable = false)
    private Long capacity;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Room room = (Room) o;
        return Objects.equals(
                getRoomId(), room.getRoomId()
        ) && Objects.equals(
                getBuildingName(), room.getBuildingName()
        ) && Objects.equals(
                getRoomNumber(), room.getRoomNumber()
        ) && Objects.equals(
                getCapacity(), room.getCapacity()
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(getRoomId(), getBuildingName(), getRoomNumber(), getCapacity());
    }
}
