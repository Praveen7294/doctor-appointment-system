package com.praveen.doctor_appointment_system.service;

import com.praveen.doctor_appointment_system.dto.PatientRequestDTO;
import com.praveen.doctor_appointment_system.dto.PatientResponseDTO;
import com.praveen.doctor_appointment_system.dto.PatientUpdateRequestDTO;
import com.praveen.doctor_appointment_system.entity.Patient;
import com.praveen.doctor_appointment_system.entity.Role;
import com.praveen.doctor_appointment_system.entity.User;
import com.praveen.doctor_appointment_system.exception.PatientNotFoundException;
import com.praveen.doctor_appointment_system.exception.ProfileAlreadyCreatedException;
import com.praveen.doctor_appointment_system.exception.UnauthorizedRoleException;
import com.praveen.doctor_appointment_system.mapper.PatientMapper;
import com.praveen.doctor_appointment_system.repository.PatientRepository;
import com.praveen.doctor_appointment_system.repository.UserRepository;
import com.praveen.doctor_appointment_system.security.SecurityUtils;
import org.springframework.stereotype.Service;

@Service
public class PatientService {

    private final PatientRepository patientRepository;
    private final UserRepository userRepository;

    public PatientService(PatientRepository patientRepository,
                          UserRepository userRepository) {
        this.patientRepository = patientRepository;
        this.userRepository = userRepository;
    }

    public PatientResponseDTO createPatientProfile(PatientRequestDTO request) {

        User user = SecurityUtils.getCurrentUserFromSecurityContextHolder();

        if (user.getRole() != Role.PATIENT) {
            throw new UnauthorizedRoleException("Only patients can create patient profile");
        }

        if (user.getProfileCompleted() || patientRepository.existsByUser(user)) {
            throw new ProfileAlreadyCreatedException("Patient profile is already completed");
        }

        Patient patient = PatientMapper.toEntity(request, user);
        patient = patientRepository.save(patient);

        user.setProfileCompleted(true);
        userRepository.save(user);

        return PatientMapper.toDTO(patient);
    }

    public PatientResponseDTO updatePatientProfile(PatientUpdateRequestDTO request) {

        User user = SecurityUtils.getCurrentUserFromSecurityContextHolder();

        if (user.getRole() != Role.PATIENT) {
            throw new UnauthorizedRoleException("Only patients can update their profile");
        }

        Patient patient = patientRepository.findByUser(user).orElseThrow(
                () -> new PatientNotFoundException("Patient not found with userId: " + user.getId()));

        if (request.getPhoneNumber() != null) {
            patient.setPhoneNumber(request.getPhoneNumber());
        }

        if (request.getName() != null) {
            patient.setName(request.getName());
        }

        if (request.getDateOfBirth() != null) {
            patient.setDateOfBirth(request.getDateOfBirth());
        }

        if (request.getGender() != null) {
            patient.setGender(request.getGender());
        }

        if (request.getAddress() != null) {
            patient.setAddress(request.getAddress());
        }

        Patient updatedPatient = patientRepository.save(patient);

        return PatientMapper.toDTO(updatedPatient);
    }
}
