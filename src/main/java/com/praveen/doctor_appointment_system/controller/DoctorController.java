package com.praveen.doctor_appointment_system.controller;

import com.praveen.doctor_appointment_system.dto.DoctorRequestDTO;
import com.praveen.doctor_appointment_system.dto.DoctorResponseDTO;
import com.praveen.doctor_appointment_system.dto.DoctorSummaryResponseDTO;
import com.praveen.doctor_appointment_system.dto.DoctorUpdateRequestDTO;
import com.praveen.doctor_appointment_system.service.DoctorService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/doctor")
public class DoctorController {

    private final DoctorService doctorService;

    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @PostMapping("/profile")
    public ResponseEntity<DoctorResponseDTO> createProfile(@Valid @RequestBody DoctorRequestDTO request) {

        DoctorResponseDTO response = doctorService.createDoctorProfile(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PatchMapping("/update")
    public ResponseEntity<DoctorResponseDTO> updateProfile(@RequestBody DoctorUpdateRequestDTO request) {

        DoctorResponseDTO response = doctorService.updateDoctorProfile(request);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/all")
    public ResponseEntity<List<DoctorSummaryResponseDTO>> getAllDoctors() {

        List<DoctorSummaryResponseDTO> response = doctorService.getAllDoctors();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
