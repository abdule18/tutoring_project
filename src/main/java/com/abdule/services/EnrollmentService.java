package com.abdule.services;

import com.abdule.dto.request.EnrollmentRequestDTO;
import com.abdule.dto.response.EnrollmentResponseDTO;
import com.abdule.entities.Enrollment;
import com.abdule.entities.Student;
import com.abdule.entities.Subject;
import com.abdule.repositories.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class EnrollmentService {

    private final EnrollmentRepository enrollmentRepository;
    private final StudentRepository studentRepository;
    private final SubjectRepository subjectRepository;
    public EnrollmentResponseDTO createNewEnrollment(@Valid EnrollmentRequestDTO requestDTO) {

        Student stu = studentRepository.findById(requestDTO.getStudentId())
                .orElseThrow(() -> new RuntimeException("Student not found"));
        Subject sub = subjectRepository.findById(requestDTO.getSubjectId())
                .orElseThrow(() -> new RuntimeException("Subject not found"));

        boolean alreadyEnrolled = enrollmentRepository.existsByStudent_IdAndSubject_SubjectId(
                requestDTO.getStudentId(),
                requestDTO.getSubjectId()
        );

        if (alreadyEnrolled) {
            throw new RuntimeException("Student is already enrolled in this subject");
        }

        Enrollment enrollment = Enrollment.builder()
                .student(stu)
                .subject(sub)
                .build();

        Enrollment saved = enrollmentRepository.save(enrollment);

        return EnrollmentResponseDTO.builder()
                .enrollmentId(saved.getEnrollmentId())
                .studentId(saved.getStudent().getId())
                .subjectId(saved.getSubject().getSubjectId())
                .createdAt(saved.getCreatedAt())
                .build();

    }

    public List<EnrollmentResponseDTO> findAllEnrollments() {

        List<Enrollment> enrollments = enrollmentRepository.findAll();

        return enrollments.stream()
                .map(e -> EnrollmentResponseDTO.builder()
                        .enrollmentId(e.getEnrollmentId())
                        .studentId(e.getStudent().getId())
                        .subjectId(e.getSubject().getSubjectId())
                        .createdAt(e.getCreatedAt())
                        .build())
                .toList();
    }

    public EnrollmentResponseDTO findEnrollmentById(UUID id) {

        // 1) Find existing enrollment
        Enrollment enrollment = enrollmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Enrollment not found"));
        return EnrollmentResponseDTO.builder()
                .enrollmentId(enrollment.getEnrollmentId())
                .studentId(enrollment.getStudent().getId())
                .subjectId(enrollment.getSubject().getSubjectId())
                .createdAt(enrollment.getCreatedAt())
                .build();
    }

    public EnrollmentResponseDTO updateEnrollment(UUID id, @Valid EnrollmentRequestDTO request) {
        // 1) Find existing enrollment
        Enrollment existingEnrollment = enrollmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Enrollment not found"));

        // 2) Fetch new Student + Subject (if you allow changing them)
        Student stu = studentRepository.findById(request.getStudentId())
                .orElseThrow(() -> new RuntimeException("Student not found"));

        Subject sub = subjectRepository.findById(request.getSubjectId())
                .orElseThrow(() -> new RuntimeException("Subject not found"));

        existingEnrollment.setStudent(stu);
        existingEnrollment.setSubject(sub);

        Enrollment saved = enrollmentRepository.save(existingEnrollment);

        return EnrollmentResponseDTO.builder()
                .enrollmentId(saved.getEnrollmentId())
                .studentId(saved.getStudent().getId())
                .subjectId(saved.getSubject().getSubjectId())
                .createdAt(saved.getCreatedAt())
                .build();
    }

    public void deleteEnrollment(UUID id) {
        Enrollment enrollment = enrollmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Enrollment not found"));

        enrollmentRepository.delete(enrollment);
    }
}
