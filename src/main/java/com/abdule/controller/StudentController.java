package com.abdule.controller;

import com.abdule.dto.response.StudentResponseDTO;
import com.abdule.dto.request.StudentRequestDTO;
import com.abdule.services.StudentService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/student")
public class StudentController {

    private final StudentService studentService;


    @PostMapping()
    public ResponseEntity<StudentResponseDTO> createNewStudent(@Valid @RequestBody StudentRequestDTO student){
        StudentResponseDTO saved = studentService.createNewStudent(student);
        return ResponseEntity.status(201).body(saved);
    }

    @GetMapping()
    public ResponseEntity<List<StudentResponseDTO>> getStudent(){

        return ResponseEntity.ok(studentService.findAllStudent());
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentResponseDTO> getStudentById(@PathVariable UUID id){

        return ResponseEntity.ok(studentService.findStudentById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudentResponseDTO> updateStudent(
            @PathVariable UUID id,
            @Valid @RequestBody StudentResponseDTO request
    ) {
        StudentResponseDTO updated = studentService.updateStudent(id, request);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<StudentResponseDTO> deleteStudent(@PathVariable UUID id) {
        studentService.deleteStudent(id);
        return ResponseEntity.noContent().build();
    }
}
