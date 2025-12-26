package com.abdule.dto.response;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.UUID;
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EnrollmentResponseDTO {

    private UUID enrollmentId;

    private UUID studentId;

    private UUID subjectId;

    private Instant createdAt;
}
