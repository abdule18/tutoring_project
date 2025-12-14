package com.abdule.dto.response;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EnrollmentResponseDTO {

    private UUID studentId;

    private UUID subjectId;

    private UUID enrollmentId;
    
    private Instant createdAt;
}
