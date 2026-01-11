package com.abdule.controller;

import com.abdule.dto.request.AppointmentRequestDTO;
import com.abdule.dto.response.AppointmentResponseDTO;
import com.abdule.services.AppointmentService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/appointment")
public class AppointmentController {

    private final AppointmentService appointmentService;

    @PostMapping
    public ResponseEntity<AppointmentResponseDTO> createNewAppointment(
            @Valid @RequestBody AppointmentRequestDTO request
            ) {
        AppointmentResponseDTO created = appointmentService.createNewAppointment(request);

        return ResponseEntity.status(201).body(created);
    }

    @GetMapping
    public ResponseEntity<List<AppointmentResponseDTO>> getAllAppointment() {
        return ResponseEntity.ok(appointmentService.findAllAppointments());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AppointmentResponseDTO> getAppointmentById(@PathVariable UUID id) {
        return ResponseEntity.ok(appointmentService.findAppointmentById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AppointmentResponseDTO> updateAppointment(
            @PathVariable UUID id,
            @Valid @RequestBody AppointmentRequestDTO request
    ) {
        AppointmentResponseDTO updated = appointmentService.updateAppointment(id, request);
        return ResponseEntity.ok(updated);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<AppointmentResponseDTO> cancelAppointment(@PathVariable UUID id) {
        AppointmentResponseDTO  cancelled = appointmentService.cancelAppointment(id);
        return ResponseEntity.ok(cancelled);
    }

        @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAppointment(@PathVariable UUID id) {
        appointmentService.deleteAppointment(id);
        return  ResponseEntity.noContent().build();
    }
}
