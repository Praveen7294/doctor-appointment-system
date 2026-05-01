package com.praveen.doctor_appointment_system.repository;

import com.praveen.doctor_appointment_system.entity.Appointment;
import com.praveen.doctor_appointment_system.entity.AppointmentStatus;
import com.praveen.doctor_appointment_system.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, UUID> {

    List<Appointment> findAppointmentByDoctorAndAppointmentDateAndStatus(
            Doctor doctor,
            LocalDate appointmentDate,
            AppointmentStatus appointmentStatus
    );

    boolean existsByDoctorAndAppointmentDateAndConsultingTimeAndStatus(
            Doctor doctor,
            LocalDate appointmentDate,
            LocalTime consultingTime,
            AppointmentStatus appointmentStatus
    );

    long countByDoctorAndAppointmentDateAndStatus(
            Doctor doctor,
            LocalDate appointmentDate,
            AppointmentStatus appointmentStatus
    );
}
