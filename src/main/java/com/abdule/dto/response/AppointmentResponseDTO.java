package com.abdule.dto.response;

import com.abdule.enums.AppointmentStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentResponseDTO {

    private UUID apptId;

    private UUID studentId;

    private UUID tutorId;

    private UUID subjectId;

    private UUID roomId;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private AppointmentStatusEnum status;
}
