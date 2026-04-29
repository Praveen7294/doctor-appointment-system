package com.praveen.doctor_appointment_system.dto;

import jakarta.validation.constraints.NotBlank;

public class PatientRequestDTO {

    @NotBlank(message = "Phone number is required")
    private String phoneNumber;

    private String name;

    public @NotBlank(message = "Phone number is required") String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(@NotBlank(message = "Phone number is required") String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
