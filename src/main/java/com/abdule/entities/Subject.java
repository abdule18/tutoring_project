package com.abdule.entities;


import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "subjects",
        uniqueConstraints = {
                @UniqueConstraint(name = "uk_subject_title", columnNames = "title")
        })
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "subject_id", updatable = false, nullable = false)
    private UUID subjectId;

    @Column(name = "code", nullable = false)
    private String code;

    @Column(name = "title", nullable = false)
    private String title;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Subject subject = (Subject) o;
        return Objects.equals(
                getSubjectId(), subject.getSubjectId()
        ) && Objects.equals(
                getCode(), subject.getCode()
        ) && Objects.equals(
                getTitle(), subject.getTitle()
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(getSubjectId(), getCode(), getTitle());
    }
}
