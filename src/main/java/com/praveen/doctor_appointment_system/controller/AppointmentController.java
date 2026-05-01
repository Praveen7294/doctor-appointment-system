package com.praveen.doctor_appointment_system.controller;

import com.praveen.doctor_appointment_system.dto.AppointmentRequestDTO;
import com.praveen.doctor_appointment_system.dto.AppointmentResponseDTO;
import com.praveen.doctor_appointment_system.dto.AvailabilityResponseDTO;
import com.praveen.doctor_appointment_system.service.AppointmentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.UUID;

@RestController
@RequestMapping("/api/appointment")
public class AppointmentController {

    private final AppointmentService appointmentService;

    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @GetMapping("/check-availability")
    public ResponseEntity<AvailabilityResponseDTO> checkAvailability(@RequestParam UUID doctorId,
                                                                     @RequestParam(required = false) LocalDate date) {

        AvailabilityResponseDTO response = appointmentService.checkAvailableSlot(doctorId, date);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping("/book")
    public ResponseEntity<AppointmentResponseDTO> bookAppointment(
            @Valid @RequestBody AppointmentRequestDTO request) {

        AppointmentResponseDTO response = appointmentService.bookAppointment(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
