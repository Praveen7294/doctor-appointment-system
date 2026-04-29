package com.praveen.doctor_appointment_system.repository;

import com.praveen.doctor_appointment_system.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface PatientRepository extends JpaRepository<Patient, UUID> {

    Optional<Patient> findByPhoneNumber(String phoneNumber);
}
