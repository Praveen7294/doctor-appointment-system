package com.praveen.doctor_appointment_system.controller;

import com.praveen.doctor_appointment_system.dto.LoginRequestDTO;
import com.praveen.doctor_appointment_system.dto.LoginResponseDTO;
import com.praveen.doctor_appointment_system.dto.RegisterRequestDTO;
import com.praveen.doctor_appointment_system.dto.RegisterResponseDTO;
import com.praveen.doctor_appointment_system.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<RegisterResponseDTO> register(@Valid @RequestBody RegisterRequestDTO request) {

        RegisterResponseDTO response = authService.registerUser(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login (@Valid @RequestBody LoginRequestDTO request) {

        LoginResponseDTO response = authService.loginUser(request);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
