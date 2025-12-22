package com.abdule.controller;

import com.abdule.dto.request.SubjectRequestDTO;
import com.abdule.dto.response.SubjectResponseDTO;
import com.abdule.services.SubjectService;
import com.abdule.services.TutorService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/subject")
public class SubjectController {

    private final SubjectService subjectService;

    @PostMapping
    public ResponseEntity<SubjectResponseDTO> createNewSubject(@Valid @RequestBody SubjectRequestDTO subject) {
        SubjectResponseDTO saved = subjectService.createNewSubject(subject);
        return ResponseEntity.status(201).body(saved);
    }

    @GetMapping
    public ResponseEntity<List<SubjectResponseDTO>> getSubject() {
        return ResponseEntity.ok(subjectService.findAllSubject());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SubjectResponseDTO> getSubjectById(@PathVariable UUID id) {
        return ResponseEntity.ok(subjectService.findSubjectById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SubjectResponseDTO> updateSubject(
            @PathVariable UUID id,
            @Valid @RequestBody SubjectRequestDTO request
    ) {
        SubjectResponseDTO updated = subjectService.updateSubject(id, request);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSubject(@PathVariable UUID id) {
        subjectService.deleteSubject(id);
        return ResponseEntity.noContent().build();
    }
}
