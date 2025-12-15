package com.abdule.services;


import com.abdule.dto.response.StudentResponseDTO;
import com.abdule.exceptions.StudentExistsException;
import com.abdule.entities.Student;
import com.abdule.dto.request.StudentRequestDTO;
import com.abdule.repositories.StudentRepository;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentResponseDTO createNewStudent(StudentRequestDTO student) {

        // 1) Check if email already exists
        if(studentRepository.existsByEmail(student.getEmail())){
            throw new StudentExistsException("This email already exists!");
        }

        // 2) Convert RequestDTO -> Entity
        Student stu = Student.builder()
                .firstName(student.getFirstName())
                .lastName(student.getLastName())
                .email(student.getEmail())
                .password(student.getPassword())
                .build();

        // 3)  save Entity to DB
        Student saved = studentRepository.save(stu);

        // 4) Convert Entity -> ResponseDTO (what you return to UI)
        return StudentResponseDTO.builder()
                .id(saved.getId())
                .firstName(saved.getFirstName())
                .lastName(saved.getLastName())
                .email(saved.getEmail())
                .createdAt(saved.getCreatedAt())
                .updatedAt(saved.getUpdatedAt())
                .build();
    }

    public List<StudentResponseDTO> findAllStudent(){

        // 1) Get all students from DB (Entities)
        List<Student> students = studentRepository.findAll();

        // 2) Create an empty list to hold ResponseDTOs
        List<StudentResponseDTO> responses = students.stream()

        // 3 Loop through each entity and convert it to  responseDTO

                .map(s -> StudentResponseDTO.builder()
                        .id(s.getId())
                        .firstName(s.getFirstName())
                        .lastName(s.getLastName())
                        .email(s.getEmail())
                        .createdAt(s.getCreatedAt())
                        .updatedAt(s.getUpdatedAt())
                        .build())
                .toList();
        return responses;
    }

    public StudentResponseDTO findStudentById(UUID id) {


        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found"));
        return StudentResponseDTO.builder()
                .id(student.getId())
                .firstName(student.getFirstName())
                .lastName(student.getLastName())
                .email(student.getEmail())
                .createdAt(student.getCreatedAt())
                .updatedAt(student.getUpdatedAt())
                .build();
    }

    public StudentResponseDTO updateStudent(UUID id, @Valid StudentResponseDTO request) {

        // Step 1: Find existing student
        Student existing = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        // Step 2: Email uniqueness check (only if changed)
        String newEmail = request.getEmail();
        if (!newEmail.equalsIgnoreCase(existing.getEmail())) {
            if (studentRepository.existsByEmail(newEmail)) {
                throw new StudentExistsException("This email already exists!");
            }
            existing.setEmail(newEmail);
        }

        // Step 3: Update fields
        existing.setFirstName(request.getFirstName());
        existing.setLastName(request.getLastName());

        // Step 4: Save
        Student saved = studentRepository.save(existing);

        // Step 5: Return ResponseDTO
        return StudentResponseDTO.builder()
                .id(saved.getId())
                .firstName(saved.getFirstName())
                .lastName(saved.getLastName())
                .email(saved.getEmail())
                .createdAt(saved.getCreatedAt())
                .updatedAt(saved.getUpdatedAt())
                .build();
    }

    public void deleteStudent(UUID id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        studentRepository.delete(student);
    }
}
