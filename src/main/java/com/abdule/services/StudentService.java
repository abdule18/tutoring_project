package com.abdule.services;


import com.abdule.dto.response.StudentResponseDTO;
import com.abdule.exceptions.StudentExistsException;
import com.abdule.entities.Student;
import com.abdule.dto.request.StudentRequestDTO;
import com.abdule.repositories.StudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentResponseDTO createNewStudent(StudentRequestDTO student) {
        if(studentRepository.existsByEmail(student.getEmail())){
            throw new StudentExistsException("This email already exists!");
        }

        Student stu = Student.builder()
                .firstName(student.getFirstName())
                .lastName(student.getLastName())
                .email(student.getEmail())
//                .password(student.getPassword())
                .build();

        Student saved = studentRepository.save(stu);

        return StudentResponseDTO.builder()
                .id(saved.getId())
                .firstName(saved.getFirstName())
                .lastName(saved.getLastName())
                .email(saved.getEmail())
                .build();
    }

    public List<Student> findAllStudent(){
        return studentRepository.findAll();
    }

}
