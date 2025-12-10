package com.abdule.services;


import com.abdule.exceptions.StudentExistsException;
import com.abdule.entities.Student;
import com.abdule.dto.StudentDTO;
import com.abdule.repositories.StudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentDTO createNewStudent(StudentDTO student) {
        if(studentRepository.existsByEmail(student.getEmail())){
            throw new StudentExistsException("This email already exists!");
        }

        Student stu = Student.builder()
                .firstName(student.getFirstName())
                .lastName(student.getLastName())
                .email(student.getEmail())
                .build();

        studentRepository.save(stu);
        return StudentDTO.builder()
                .firstName(student.getFirstName())
                .lastName(student.getLastName())
                .email(student.getEmail())
                .build();
    }

    public List<Student> findAllStudent(){
        return studentRepository.findAll();
    }

}
