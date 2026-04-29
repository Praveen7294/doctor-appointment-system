package com.praveen.doctor_appointment_system.mapper;

import com.praveen.doctor_appointment_system.dto.DoctorRequestDTO;
import com.praveen.doctor_appointment_system.dto.DoctorResponseDTO;
import com.praveen.doctor_appointment_system.entity.Doctor;

public class DoctorMapper {

    public static Doctor toEntity(DoctorRequestDTO doctorRequestDTO) {
        Doctor doctor = new Doctor();

        doctor.setName(doctorRequestDTO.getName());
        doctor.setSpecialization(doctorRequestDTO.getSpecialization());
        doctor.setAvailableDays(doctorRequestDTO.getAvailableDays());
        doctor.setWeeklyOff(doctorRequestDTO.getWeeklyOff());
        doctor.setStartTime(doctorRequestDTO.getStartTime());
        doctor.setEndTime(doctorRequestDTO.getEndTime());
        doctor.setSlotDurationMinutes(doctorRequestDTO.getSlotDurationMinutes());
        doctor.setUseManualSlotLimit(doctorRequestDTO.getUseManualSlotLimit());
        doctor.setManualSlotLimit(doctorRequestDTO.getManualSlotLimit());

        return doctor;
    }

    public static DoctorResponseDTO toDTO(Doctor doctor) {

        DoctorResponseDTO doctorResponseDTO = new DoctorResponseDTO();

        doctorResponseDTO.setId(doctor.getId());
        doctorResponseDTO.setName(doctor.getName());
        doctorResponseDTO.setSpecialization(doctor.getSpecialization());
        doctorResponseDTO.setAvailableDays(doctor.getAvailableDays());
        doctorResponseDTO.setWeeklyOff(doctor.getWeeklyOff());
        doctorResponseDTO.setStartTime(doctor.getStartTime());
        doctorResponseDTO.setEndTime(doctor.getEndTime());
        doctorResponseDTO.setSlotDurationMinutes(doctor.getSlotDurationMinutes());
        doctorResponseDTO.setUseManualSlotLimit(doctor.getUseManualSlotLimit());
        doctorResponseDTO.setManualSlotLimit(doctor.getManualSlotLimit());

        return doctorResponseDTO;
    }
}
