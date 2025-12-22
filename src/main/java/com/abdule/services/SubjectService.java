package com.abdule.services;


import com.abdule.dto.request.SubjectRequestDTO;
import com.abdule.dto.response.SubjectResponseDTO;
import com.abdule.entities.Subject;
import com.abdule.repositories.SubjectRepository;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class SubjectService {

    private final SubjectRepository subjectRepository;

    public SubjectResponseDTO createNewSubject(SubjectRequestDTO subject) {

        Subject sub = Subject.builder()
                .title(subject.getTitle())
                .code(subject.getCode())
                .build();

        Subject saved = subjectRepository.save(sub);

        return SubjectResponseDTO.builder()
                .subjectId(saved.getSubjectId())
                .title(saved.getTitle())
                .code(saved.getCode())
                .build();
    }

    public List<SubjectResponseDTO> findAllSubject(){

        List<Subject> subjects = subjectRepository.findAll();

        List<SubjectResponseDTO> response = subjects.stream()
                .map(s -> SubjectResponseDTO.builder()
                        .subjectId(s.getSubjectId())
                        .code(s.getCode())
                        .title(s.getTitle())
                        .build())
                .toList();
        return response;

    }


    public SubjectResponseDTO findSubjectById(UUID id) {
        Subject  sub = subjectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Subject not found"));

        return SubjectResponseDTO.builder()
                .subjectId(sub.getSubjectId())
                .code(sub.getCode())
                .title(sub.getTitle())
                .build();
    }


    public SubjectResponseDTO updateSubject(UUID id, @Valid SubjectRequestDTO request) {

        Subject existingSub = subjectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Subject not found"));

        existingSub.setCode(request.getCode());
        existingSub.setTitle(request.getTitle());

        Subject saveUpdate = subjectRepository.save(existingSub);

        return SubjectResponseDTO.builder()
                .subjectId(saveUpdate.getSubjectId())
                .code(saveUpdate.getCode())
                .title(saveUpdate.getTitle())
                .build();
    }

    public void deleteSubject(UUID id) {
        Subject sub = subjectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Subject not  found"));

        subjectRepository.delete(sub);
    }

}

