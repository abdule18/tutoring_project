package com.abdule.controller;

import com.abdule.dto.request.TutorRequestDTO;
import com.abdule.dto.response.StudentResponseDTO;
import com.abdule.dto.response.TutorResponseDTO;
import com.abdule.services.TutorService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/tutor")
public class TutorController {

    private final TutorService tutorService;

    @PostMapping
    public ResponseEntity<TutorResponseDTO> createNewTutor(@Valid @RequestBody TutorRequestDTO tutor){
        TutorResponseDTO tutorSaved = tutorService.createNewTutor(tutor);
        return ResponseEntity.status(201).body(tutorSaved);
    }

    @GetMapping
    public ResponseEntity<List<TutorResponseDTO>> getAllTutors() {
        return ResponseEntity.ok(tutorService.findAllTutors());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TutorResponseDTO> getTutorById(@PathVariable UUID id) {
        return ResponseEntity.ok(tutorService.findTutorById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TutorResponseDTO> updateTutor(
            @PathVariable UUID id,
            @Valid @RequestBody TutorResponseDTO request
    ) {
        TutorResponseDTO updated = tutorService.updateTutor(id, request);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTutor(@PathVariable UUID id) {
        tutorService.deleteTutor(id);
        return ResponseEntity.noContent().build();
    }

}
