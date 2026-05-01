package com.praveen.doctor_appointment_system.controller;

import com.praveen.doctor_appointment_system.dto.PatientRequestDTO;
import com.praveen.doctor_appointment_system.dto.PatientResponseDTO;
import com.praveen.doctor_appointment_system.dto.PatientUpdateRequestDTO;
import com.praveen.doctor_appointment_system.service.PatientService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/patient")
public class PatientController {

    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @PostMapping("/profile")
    public ResponseEntity<PatientResponseDTO> createProfile(@Valid @RequestBody PatientRequestDTO request) {

        PatientResponseDTO response = patientService.createPatientProfile(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PatchMapping("/update")
    public ResponseEntity<PatientResponseDTO> updateProfile(@RequestBody PatientUpdateRequestDTO request) {

        PatientResponseDTO response = patientService.updatePatientProfile(request);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
