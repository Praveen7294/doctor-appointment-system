package com.praveen.doctor_appointment_system.mapper;

import com.praveen.doctor_appointment_system.dto.PatientRequestDTO;
import com.praveen.doctor_appointment_system.dto.PatientResponseDTO;
import com.praveen.doctor_appointment_system.entity.Patient;

public class PatientMapper {

    public static Patient toEntity(PatientRequestDTO patientRequestDTO) {
        Patient patient = new Patient();

        patient.setPhoneNumber(patientRequestDTO.getPhoneNumber());
        patient.setName(patientRequestDTO.getName());

        return patient;
    }

    public static PatientResponseDTO toDTO(Patient patient) {

        PatientResponseDTO patientResponseDTO = new PatientResponseDTO();

        patientResponseDTO.setId(patient.getId());
        patientResponseDTO.setPhoneNumber(patient.getPhoneNumber());
        patientResponseDTO.setName(patient.getName());

        return patientResponseDTO;
    }
}
