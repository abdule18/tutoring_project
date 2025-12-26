package com.abdule.controller;

import com.abdule.dto.request.EnrollmentRequestDTO;
import com.abdule.dto.response.EnrollmentResponseDTO;
import com.abdule.services.EnrollmentService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/enrollment")
public class EnrollmentController {

    private final EnrollmentService enrollmentService;

    @PostMapping
    public ResponseEntity<EnrollmentResponseDTO> createNewEnrollment(@Valid @RequestBody EnrollmentRequestDTO requestDTO) {
        EnrollmentResponseDTO saved = enrollmentService.createNewEnrollment(requestDTO);
        return ResponseEntity.status(201).body(saved);
    }

    @GetMapping
    public ResponseEntity<List<EnrollmentResponseDTO>> getAllEnrollment() {
        return ResponseEntity.ok(enrollmentService.findAllEnrollments());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EnrollmentResponseDTO> getEnrollmentById(@PathVariable UUID id) {
        return ResponseEntity.ok(enrollmentService.findEnrollmentById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EnrollmentResponseDTO> updateEnrollment(
            @PathVariable UUID id,
            @Valid @RequestBody EnrollmentRequestDTO request) {
        EnrollmentResponseDTO updated = enrollmentService.updateEnrollment(id, request);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEnrollment(@PathVariable UUID id) {
        enrollmentService.deleteEnrollment(id);
        return ResponseEntity.noContent().build();
    }

}
