package com.praveen.doctor_appointment_system.service;

import com.praveen.doctor_appointment_system.dto.DoctorRequestDTO;
import com.praveen.doctor_appointment_system.dto.DoctorResponseDTO;
import com.praveen.doctor_appointment_system.dto.DoctorSummaryResponseDTO;
import com.praveen.doctor_appointment_system.dto.DoctorUpdateRequestDTO;
import com.praveen.doctor_appointment_system.entity.Doctor;
import com.praveen.doctor_appointment_system.entity.Role;
import com.praveen.doctor_appointment_system.entity.User;
import com.praveen.doctor_appointment_system.exception.DoctorNotFoundException;
import com.praveen.doctor_appointment_system.exception.ProfileAlreadyCreatedException;
import com.praveen.doctor_appointment_system.exception.UnauthorizedRoleException;
import com.praveen.doctor_appointment_system.mapper.DoctorMapper;
import com.praveen.doctor_appointment_system.repository.DoctorRepository;
import com.praveen.doctor_appointment_system.repository.UserRepository;
import com.praveen.doctor_appointment_system.security.SecurityUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorService {

    private final DoctorRepository doctorRepository;
    private final UserRepository userRepository;

    public DoctorService(DoctorRepository doctorRepository,
                         UserRepository userRepository) {
        this.doctorRepository = doctorRepository;
        this.userRepository = userRepository;
    }

    public DoctorResponseDTO createDoctorProfile(DoctorRequestDTO request) {

        User user = SecurityUtils.getCurrentUserFromSecurityContextHolder();

        if (user.getRole() != Role.DOCTOR) {
            throw new UnauthorizedRoleException("Only doctors can create doctor profile");
        }

        if (user.getProfileCompleted() || doctorRepository.existsByUser(user)) {
            throw new ProfileAlreadyCreatedException("Doctor profile is already completed");
        }

        Doctor doctor = DoctorMapper.toEntity(request, user);
        doctor = doctorRepository.save(doctor);
        user.setProfileCompleted(true);

        userRepository.save(user);

        return DoctorMapper.toDTO(doctor);
    }

    public DoctorResponseDTO updateDoctorProfile(DoctorUpdateRequestDTO request) {

        User user = SecurityUtils.getCurrentUserFromSecurityContextHolder();

        if (user.getRole() != Role.DOCTOR) {
            throw new UnauthorizedRoleException("Only doctors can update their profile");
        }

        Doctor doctor = doctorRepository.findByUser(user).orElseThrow(
                () -> new DoctorNotFoundException("Doctor not found with userId: " + user.getId()));

        if (request.getName() != null) {
            doctor.setName(request.getName());
        }

        if (request.getSpecialization() != null) {
            doctor.setSpecialization(request.getSpecialization());
        }

        if (request.getAvailableDays() != null) {
            doctor.setAvailableDays(request.getAvailableDays());
        }

        if (request.getWeeklyOff() != null) {
            doctor.setWeeklyOff(request.getWeeklyOff());
        }

        if (request.getSlotDurationMinutes() != null) {
            doctor.setSlotDurationMinutes(request.getSlotDurationMinutes());
        }

        if (request.getUseManualSlotLimit() != null) {
            doctor.setUseManualSlotLimit(request.getUseManualSlotLimit());
        }

        if (request.getManualSlotLimit() != null) {
            doctor.setManualSlotLimit(request.getManualSlotLimit());
        }

        Doctor updatedDoctor = doctorRepository.save(doctor);

        return DoctorMapper.toDTO(updatedDoctor);
    }

    public List<DoctorSummaryResponseDTO> getAllDoctors() {

        return doctorRepository.findAll()
                .stream()
                .map(DoctorMapper::toSummaryDto)
                .toList();
    }
}
