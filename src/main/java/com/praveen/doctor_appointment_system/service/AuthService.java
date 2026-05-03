package com.praveen.doctor_appointment_system.service;

import com.praveen.doctor_appointment_system.dto.LoginRequestDTO;
import com.praveen.doctor_appointment_system.dto.LoginResponseDTO;
import com.praveen.doctor_appointment_system.dto.RegisterRequestDTO;
import com.praveen.doctor_appointment_system.dto.RegisterResponseDTO;
import com.praveen.doctor_appointment_system.entity.User;
import com.praveen.doctor_appointment_system.exception.EmailAlreadyExistsException;
import com.praveen.doctor_appointment_system.exception.EmailNotFoundException;
import com.praveen.doctor_appointment_system.exception.PasswordMismatchException;
import com.praveen.doctor_appointment_system.repository.UserRepository;
import com.praveen.doctor_appointment_system.security.JwtUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public AuthService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder,
                       JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    public RegisterResponseDTO registerUser(RegisterRequestDTO request) {

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new EmailAlreadyExistsException("Email already exists with email: "
                    + request.getEmail());
        }

        User user = new User();
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(request.getRole());

        User savedUser = userRepository.save(user);

        RegisterResponseDTO response = new RegisterResponseDTO();
        response.setUserId(savedUser.getId());
        response.setEmail(savedUser.getEmail());
        response.setRole(savedUser.getRole());
        response.setProfileCompleted(savedUser.getProfileCompleted());
        return response;
    }

    public LoginResponseDTO loginUser(LoginRequestDTO request) {
        User user = userRepository.findByEmail(request.getEmail()).orElseThrow(
                () -> new EmailNotFoundException("Invalid email or password"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new PasswordMismatchException("Invalid email or password");
        }

        String token = jwtUtil.generateToken(user);

        LoginResponseDTO response = new LoginResponseDTO();
        response.setToken(token);
        response.setEmail(user.getEmail());
        response.setRole(user.getRole());
        response.setProfileCompleted(user.getProfileCompleted());

        return response;
    }
}
