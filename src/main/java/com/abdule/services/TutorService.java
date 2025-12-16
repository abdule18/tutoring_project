package com.abdule.services;

import com.abdule.dto.request.TutorRequestDTO;
import com.abdule.dto.response.TutorResponseDTO;
import com.abdule.entities.Tutor;
import com.abdule.exceptions.TutorExistsException;
import com.abdule.repositories.TutorRepository;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;



@Service
@AllArgsConstructor
public class TutorService {

    private final TutorRepository tutorRepository;

    public TutorResponseDTO createNewTutor(TutorRequestDTO tutor) {
        if (tutorRepository.existsByEmail(tutor.getEmail())) {
            throw new TutorExistsException("This email already exists!");
        }

        Tutor newTutor = Tutor.builder()
                .firstName(tutor.getFirstName())
                .lastName(tutor.getLastName())
                .email(tutor.getEmail())
                .subjectIds(tutor.getSubjectIds())
                .build();
        Tutor saved = tutorRepository.save(newTutor);

        return TutorResponseDTO.builder()
                .id(saved.getId())
                .firstName(saved.getFirstName())
                .lastName(saved.getLastName())
                .email(saved.getEmail())
                .subjectIds(saved.getSubjectIds())
                .build();
    }
}
