package com.praveen.doctor_appointment_system.repository;

import com.praveen.doctor_appointment_system.entity.Doctor;
import com.praveen.doctor_appointment_system.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, UUID> {

    Optional<Doctor> findByUser(User user);

    boolean existsByUser(User user);
}
