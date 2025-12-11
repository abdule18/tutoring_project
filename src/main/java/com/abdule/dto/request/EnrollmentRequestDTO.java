package com.abdule.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EnrollmentRequestDTO {

    @NotNull(message = "Student ID is required")
    private UUID studentId;

    @NotNull(message = "Subject ID is required")
    private UUID subjectId;
}
