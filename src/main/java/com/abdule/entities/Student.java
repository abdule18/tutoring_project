package com.abdule.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "students",
        uniqueConstraints = {
                @UniqueConstraint(name = "uk_students_email", columnNames = "email")
        })
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private Instant updatedAt;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Objects.equals(
                getId(), student.getId()
        ) && Objects.equals(
                getFirstName(), student.getFirstName()
        ) && Objects.equals(
                getLastName(), student.getLastName()
        ) && Objects.equals(
                getEmail(), student.getEmail()
        ) && Objects.equals(
                getCreatedAt(), student.getCreatedAt()
        ) && Objects.equals(
                getUpdatedAt(), student.getUpdatedAt()
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                getId(), getFirstName(), getLastName(), getEmail(), getCreatedAt(), getUpdatedAt()
        );
    }
}
