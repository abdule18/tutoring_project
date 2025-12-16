package com.abdule.controller;

import com.abdule.dto.request.TutorRequestDTO;
import com.abdule.dto.response.StudentResponseDTO;
import com.abdule.dto.response.TutorResponseDTO;
import com.abdule.services.TutorService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/tutor")
public class TutorController {

    private final TutorService tutorService;

    public ResponseEntity<TutorResponseDTO> createNewTutor(@Valid @RequestBody TutorRequestDTO tutor){
        TutorResponseDTO tutorSaved = tutorService.createNewTutor(tutor);
        return ResponseEntity.status(201).body(tutorSaved);
    }
}
