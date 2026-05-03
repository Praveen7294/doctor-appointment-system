package com.praveen.doctor_appointment_system.mapper;

import com.praveen.doctor_appointment_system.dto.PatientRequestDTO;
import com.praveen.doctor_appointment_system.dto.PatientResponseDTO;
import com.praveen.doctor_appointment_system.entity.Patient;
import com.praveen.doctor_appointment_system.entity.User;

public class PatientMapper {

    public static Patient toEntity(PatientRequestDTO patientRequestDTO, User user) {
        Patient patient = new Patient();

        patient.setUser(user);
        patient.setPhoneNumber(patientRequestDTO.getPhoneNumber());
        patient.setName(patientRequestDTO.getName());
        patient.setDateOfBirth(patientRequestDTO.getDateOfBirth());
        patient.setGender(patientRequestDTO.getGender());
        patient.setAddress(patientRequestDTO.getAddress());

        return patient;
    }

    public static PatientResponseDTO toDTO(Patient patient) {

        PatientResponseDTO patientResponseDTO = new PatientResponseDTO();

        patientResponseDTO.setId(patient.getId());
        patientResponseDTO.setPhoneNumber(patient.getPhoneNumber());
        patientResponseDTO.setName(patient.getName());
        patientResponseDTO.setDateOfBirth(patient.getDateOfBirth());
        patientResponseDTO.setGender(patient.getGender());
        patientResponseDTO.setAddress(patient.getAddress());

        return patientResponseDTO;
    }
}
