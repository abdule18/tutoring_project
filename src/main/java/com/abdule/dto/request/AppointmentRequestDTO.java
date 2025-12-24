package com.abdule.dto.request;

import com.abdule.enums.AppointmentStatusEnum;
import jakarta.persistence.Column;
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
public class AppointmentRequestDTO {


    private UUID studentId;

    private UUID tutorId;

    private UUID subjectId;

    private UUID roomId;

    @NotNull(message = "status is required")
    private AppointmentStatusEnum status;

    private LocalDateTime startTime;

    private LocalDateTime endTime;
}
