package com.abdule.services;

import com.abdule.dto.request.TutorRequestDTO;
import com.abdule.dto.response.TutorResponseDTO;
import com.abdule.entities.Subject;
import com.abdule.entities.Tutor;
import com.abdule.exceptions.StudentExistsException;
import com.abdule.exceptions.TutorExistsException;
import com.abdule.repositories.SubjectRepository;
import com.abdule.repositories.TutorRepository;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;


@Service
@AllArgsConstructor
public class TutorService {

    private final TutorRepository tutorRepository;
    private final SubjectRepository subjectRepository;




    public TutorResponseDTO createNewTutor(TutorRequestDTO tutor) {

        // Check for duplicate email
        if (tutorRepository.existsByEmail(tutor.getEmail())) {
            throw new TutorExistsException("This email already exists!");
        }

        // Get subject IDs from the request
        List<UUID> subjectIds = tutor.getSubjectIds();

        // Convert subject IDs -> Subject entities
        List<Subject> subjects = subjectRepository.findAllById(subjectIds);

        // Validate that all subject IDs were valid
        if (subjects.size() != subjectIds.size()) {
            throw new RuntimeException("One or more subject IDs are invalid");
        }


        // Build Tutor entity using Subjects (NOT subjectIds)
        Tutor newTutor = Tutor.builder()
                .firstName(tutor.getFirstName())
                .lastName(tutor.getLastName())
                .email(tutor.getEmail())
                .subjects(subjects)
                .build();

        //Save Tutor to database
        Tutor saved = tutorRepository.save(newTutor);

        // Convert Subjects -> subjectIds for the response
        List<UUID> responseSubjectIds = saved.getSubjects()
                .stream()
                .map(Subject::getSubjectId)
                .toList();

        // Build and return ResponseDTO
        return TutorResponseDTO.builder()
                .id(saved.getId())
                .firstName(saved.getFirstName())
                .lastName(saved.getLastName())
                .email(saved.getEmail())
                .subjectIds(responseSubjectIds)
                .build();
    }

    public List<TutorResponseDTO> findAllTutors(){

        // Get all tutors from DB (entities)
        List<Tutor> tutors = tutorRepository.findAll();

        // Convert each Tutor entity -> TutorResponseDTO
        return tutors.stream()
                .map(t -> {
                    // Convert Subject entities -> UUID ids
                    List<UUID> subjectIds = t.getSubjects().stream()
                            .map(Subject::getSubjectId)
                            .toList();

                    return TutorResponseDTO.builder()
                            .id(t.getId())
                            .firstName(t.getFirstName())
                            .lastName(t.getLastName())
                            .email(t.getEmail())
                            .subjectIds(subjectIds)
                            .build();
                })
                .toList();
    }

    public TutorResponseDTO findTutorById(UUID id) {
        Tutor tutor = tutorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tutor not found"));

        List<UUID> subjectIds = tutor.getSubjects().stream()
                .map(Subject::getSubjectId)
                .toList();

        return TutorResponseDTO.builder()
                .id(tutor.getId())
                .firstName(tutor.getFirstName())
                .lastName(tutor.getLastName())
                .email(tutor.getEmail())
                .subjectIds(subjectIds)
                .build();
    }

    public TutorResponseDTO updateTutor(UUID id, TutorRequestDTO request) {
        Tutor existingTutor = tutorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tutor Not found"));

        String newEmail = request.getEmail();
        if (!newEmail.equalsIgnoreCase(existingTutor.getEmail())) {
            if (tutorRepository.existsByEmail(newEmail)) {
                throw new TutorExistsException("This email already exists!");
            }
            existingTutor.setEmail(newEmail);
        }

        existingTutor.setFirstName(request.getFirstName());
        existingTutor.setLastName(request.getLastName());


        Tutor saved = tutorRepository.save(existingTutor);

        List<UUID> subjectIds = existingTutor.getSubjects().stream()
                .map(Subject::getSubjectId)
                .toList();

        return TutorResponseDTO.builder()
                .id(saved.getId())
                .firstName(saved.getFirstName())
                .lastName(saved.getLastName())
                .email(saved.getEmail())
                .subjectIds(subjectIds)
                .build();
    }

    public void deleteTutor(UUID id) {
        Tutor tutor = tutorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tutor Not found"));
        tutorRepository.delete(tutor);
    }
}
