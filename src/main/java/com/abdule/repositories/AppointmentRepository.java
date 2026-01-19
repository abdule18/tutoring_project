package com.abdule.repositories;


import com.abdule.entities.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.UUID;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, UUID> {
    @Query("""
        select count(a) > 0
        from Appointment a
        where a.room.roomId = :roomId
          and a.status <> com.abdule.enums.AppointmentStatusEnum.CANCELLED
          and a.startTime < :endTime
          and a.endTime > :startTime
          and (:excludeApptId is null or a.apptId <> :excludeApptId)
    """)
    boolean existsRoomConflict(
            @Param("roomId") UUID roomId,
            @Param("startTime") LocalDateTime startTime,
            @Param("endTime") LocalDateTime endTime,
            @Param("excludeApptId") UUID excludeApptId
    );
}
