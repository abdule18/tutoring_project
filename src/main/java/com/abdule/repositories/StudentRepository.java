package com.abdule.repositories;

import com.abdule.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface StudentRepository extends JpaRepository<Student, UUID> {
    public boolean existsByEmail(String email);

    List<Student> id(UUID id);
}
