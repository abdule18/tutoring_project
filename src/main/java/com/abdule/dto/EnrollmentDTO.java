package com.abdule.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EnrollmentDTO {

    @NotNull(message = "Student ID is required")
    private UUID studentId;

    @NotNull(message = "Subject ID is required")
    private UUID subjectId;
}
