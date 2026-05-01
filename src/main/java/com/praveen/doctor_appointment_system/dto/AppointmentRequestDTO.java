package com.praveen.doctor_appointment_system.dto;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

public class AppointmentRequestDTO {

    @NotNull(message = "Doctor user Id is required")
    private UUID doctorId;

    private LocalDate appointmentDate;

    @NotNull(message = "Consulting time is required")
    private LocalTime consultingTime;

    private String reasonForVisit;

    public UUID getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(UUID doctorUserId) {
        this.doctorId = doctorUserId;
    }

    public LocalDate getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(LocalDate appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public LocalTime getConsultingTime() {
        return consultingTime;
    }

    public void setConsultingTime(LocalTime consultingTime) {
        this.consultingTime = consultingTime;
    }

    public String getReasonForVisit() {
        return reasonForVisit;
    }

    public void setReasonForVisit(String reasonForVisit) {
        this.reasonForVisit = reasonForVisit;
    }
}
