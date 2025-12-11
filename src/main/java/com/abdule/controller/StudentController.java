package com.abdule.controller;

import com.abdule.entities.Student;
import com.abdule.dto.request.StudentRequestDTO;
import com.abdule.services.StudentService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@RestController
@RequestMapping("/api/v1")
public class StudentController {

    @Autowired
    private StudentService studentService;


    @PostMapping("/student")
    public ResponseEntity<Student> createNewStudent(@Valid @RequestBody StudentRequestDTO student){
        Student saved = studentService.createNewStudent(student);
//        return studentService.createNewStudent(student);
        return ResponseEntity.status(201).body(saved);
    }

    @GetMapping("/student")
    public ResponseEntity<List<Student>> getStudent(){

        return ResponseEntity.status(201).body(studentService.findAllStudent());
    }

}
