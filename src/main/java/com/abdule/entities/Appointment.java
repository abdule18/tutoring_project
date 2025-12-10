package com.abdule.entities;

import com.abdule.enums.AppointmentStatusEnum;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "appointments")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "appt_id", updatable = false, nullable = false)
    private UUID apptId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tutor_id", nullable = false)
    private Tutor tutor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subject_id", nullable = false)
    private Subject subject;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id", nullable = false)
    private Room room;

    @Column(name = "start_time")
    private LocalDateTime startTime;

    @Column(name = "end_time")
    private LocalDateTime endTime;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private AppointmentStatusEnum status;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private Instant updatedAt;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Appointment that = (Appointment) o;
        return Objects.equals(
                getApptId(), that.getApptId()
        ) && Objects.equals(
                getStudent(), that.getStudent()
        ) && Objects.equals(
                getTutor(), that.getTutor()
        ) && Objects.equals(
                getSubject(), that.getSubject()
        ) && Objects.equals(
                getRoom(), that.getRoom()
        ) && Objects.equals(
                getStartTime(), that.getStartTime()
        ) && Objects.equals(
                getEndTime(), that.getEndTime()
        ) && getStatus() == that.getStatus() && Objects.equals(
                getCreatedAt(), that.getCreatedAt()
        ) && Objects.equals(
                getUpdatedAt(), that.getUpdatedAt()
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(

                getApptId(),
                getStudent(),
                getTutor(),
                getSubject(),
                getRoom(),
                getStartTime(),
                getEndTime(),
                getStatus(),
                getCreatedAt(),
                getUpdatedAt()
        );
    }
}
