package com.abdule.dto.response;

import com.abdule.repositories.StudentRepository;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SubjectResponseDTO {

    private UUID subjectId;

    private String code;

    private String title;
}
