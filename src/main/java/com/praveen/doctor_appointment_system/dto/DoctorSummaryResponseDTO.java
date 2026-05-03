package com.praveen.doctor_appointment_system.dto;

import java.time.DayOfWeek;
import java.util.Set;
import java.util.UUID;

public class DoctorSummaryResponseDTO {

    private UUID doctorId;

    private String name;

    private String specialization;

    private Set<DayOfWeek> availableDays;

    public UUID getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(UUID doctorId) {
        this.doctorId = doctorId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public Set<DayOfWeek> getAvailableDays() {
        return availableDays;
    }

    public void setAvailableDays(Set<DayOfWeek> availableDays) {
        this.availableDays = availableDays;
    }
}
