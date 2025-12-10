package com.abdule.dto;

import com.abdule.enums.AppointmentStatusEnum;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentDTO {

    @NotNull(message = "Student ID is required")
    private UUID studentId;

    @NotNull(message = "Tutor ID is required")
    private UUID tutorId;

    @NotNull(message = "Subject ID is required")
    private UUID subjectId;

    @NotNull(message = "Room ID is required")
    private UUID roomId;

    @NotNull(message = "Start time is required")
    @Future(message = "Start time must be in the future")
    private LocalDateTime startTime;

    @NotNull(message = "End time is required")
    @Future(message = "End time must be in the future")
    private LocalDateTime endTime;

    private AppointmentStatusEnum status;
}
