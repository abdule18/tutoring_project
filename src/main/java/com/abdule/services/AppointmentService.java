package com.abdule.services;

import com.abdule.dto.request.AppointmentRequestDTO;
import com.abdule.dto.response.AppointmentResponseDTO;
import com.abdule.entities.*;
import com.abdule.repositories.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import com.abdule.enums.AppointmentStatusEnum;
import java.time.Instant;

@Service
@AllArgsConstructor
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final StudentRepository studentRepository;
    private final TutorRepository tutorRepository;
    private final SubjectRepository subjectRepository;
    private final RoomRepository roomRepository;

    public AppointmentResponseDTO createNewAppointment(AppointmentRequestDTO request) {

        Student stu = studentRepository.findById(request.getStudentId())
                .orElseThrow(() -> new RuntimeException("Student not found"));
        Tutor tutor = tutorRepository.findById(request.getTutorId())
                .orElseThrow(() -> new RuntimeException("Tutor not found"));
        Subject sub = subjectRepository.findById(request.getSubjectId())
                .orElseThrow(() -> new RuntimeException("Subject not found"));
        Room room = roomRepository.findById(request.getRoomId())
                .orElseThrow(() -> new RuntimeException("Room not found"));

        if (request.getStartTime().isAfter(request.getEndTime())) {
            throw new RuntimeException("Start time must be before end time");
        }

        Appointment appointment = Appointment.builder()
                .student(stu)
                .tutor(tutor)
                .subject(sub)
                .room(room)
                .startTime(request.getStartTime())
                .endTime(request.getEndTime())
                .status(request.getStatus())
                .build();

        Appointment saved = appointmentRepository.save(appointment);

        return AppointmentResponseDTO.builder()
                .apptId(saved.getApptId())
                .studentId(saved.getStudent().getId())
                .tutorId(saved.getTutor().getId())
                .subjectId(saved.getSubject().getSubjectId())
                .roomId(saved.getRoom().getRoomId())
                .startTime(saved.getStartTime())
                .endTime(saved.getEndTime())
                .status(saved.getStatus())
                .cancelledAt(saved.getCancelledAt())
                .build();
    }


    public List<AppointmentResponseDTO> findAllAppointments() {

        List<Appointment> appointments = appointmentRepository.findAll();

        List<AppointmentResponseDTO>  response = appointments.stream()
                .map(a -> AppointmentResponseDTO.builder()
                        .apptId(a.getApptId())
                        .studentId(a.getStudent().getId())
                        .tutorId(a.getTutor().getId())
                        .subjectId(a.getSubject().getSubjectId())
                        .roomId(a.getRoom().getRoomId())
                        .startTime(a.getStartTime())
                        .endTime(a.getEndTime())
                        .status(a.getStatus())
                        .cancelledAt(a.getCancelledAt())
                        .build())
                .toList();
        return response;
    }


    public AppointmentResponseDTO findAppointmentById(UUID id) {
        Appointment appt = appointmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Appointment not found"));

        return AppointmentResponseDTO.builder()
                .apptId(appt.getApptId())
                .studentId(appt.getStudent().getId())
                .tutorId(appt.getTutor().getId())
                .subjectId(appt.getSubject().getSubjectId())
                .roomId(appt.getRoom().getRoomId())
                .startTime(appt.getStartTime())
                .endTime(appt.getEndTime())
                .status(appt.getStatus())
                .cancelledAt(appt.getCancelledAt())
                .build();
    }

    public AppointmentResponseDTO updateAppointment(UUID id, AppointmentRequestDTO request) {

        Appointment existingAppointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Appointment not found"));

        Student stu = studentRepository.findById(request.getStudentId())
                .orElseThrow(() -> new RuntimeException("Student not found"));
        Tutor tutor = tutorRepository.findById(request.getTutorId())
                .orElseThrow(() -> new RuntimeException("Tutor not found"));
        Subject sub = subjectRepository.findById(request.getSubjectId())
                .orElseThrow(() -> new RuntimeException("Subject not found"));
        Room room = roomRepository.findById(request.getRoomId())
                .orElseThrow(() -> new RuntimeException("Room not found"));

        if (request.getStartTime().isAfter(request.getEndTime())) {
            throw new RuntimeException("Start time must be before end time");
        }

        existingAppointment.setStudent(stu);
        existingAppointment.setTutor(tutor);
        existingAppointment.setSubject(sub);
        existingAppointment.setRoom(room);
        existingAppointment.setStartTime(request.getStartTime());
        existingAppointment.setEndTime(request.getEndTime());
        existingAppointment.setStatus(request.getStatus());

        Appointment saved = appointmentRepository.save(existingAppointment);

        return AppointmentResponseDTO.builder()
                .apptId(saved.getApptId())
                .studentId(saved.getStudent().getId())
                .tutorId(saved.getTutor().getId())
                .subjectId(saved.getSubject().getSubjectId())
                .roomId(saved.getRoom().getRoomId())
                .startTime(saved.getStartTime())
                .endTime(saved.getEndTime())
                .status(saved.getStatus())
                .cancelledAt(saved.getCancelledAt())
                .build();

    }

    public void deleteAppointment(UUID id) {
        Appointment appt = appointmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Appointment not found"));
        appointmentRepository.delete(appt);
    }

    public AppointmentResponseDTO cancelAppointment(UUID id) {
        Appointment appt = appointmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Appointment not found"));

        // If already cancelled, return as-is (idempotent)
        if (appt.getStatus() == AppointmentStatusEnum.CANCELLED) {
            return AppointmentResponseDTO.builder()
                    .apptId(appt.getApptId())
                    .studentId(appt.getStudent().getId())
                    .tutorId(appt.getTutor().getId())
                    .subjectId(appt.getSubject().getSubjectId())
                    .roomId(appt.getRoom().getRoomId())
                    .startTime(appt.getStartTime())
                    .endTime(appt.getEndTime())
                    .status(appt.getStatus())
                    .cancelledAt(appt.getCancelledAt())
                    .build();
        }

        appt.setStatus(AppointmentStatusEnum.CANCELLED);
        appt.setCancelledAt(Instant.now());

        Appointment saved = appointmentRepository.save(appt);

        return AppointmentResponseDTO.builder()
                .apptId(saved.getApptId())
                .studentId(saved.getStudent().getId())
                .tutorId(saved.getTutor().getId())
                .subjectId(saved.getSubject().getSubjectId())
                .roomId(saved.getRoom().getRoomId())
                .startTime(saved.getStartTime())
                .endTime(saved.getEndTime())
                .status(saved.getStatus())
                .cancelledAt(saved.getCancelledAt())
                .build();
    }
}
