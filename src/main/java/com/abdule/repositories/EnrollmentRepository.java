package com.abdule.repositories;


import com.abdule.entities.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface EnrollmentRepository extends JpaRepository<Enrollment, UUID> {
    boolean existsByStudent_IdAndSubject_SubjectId(UUID studentId, UUID subjectId);
}
