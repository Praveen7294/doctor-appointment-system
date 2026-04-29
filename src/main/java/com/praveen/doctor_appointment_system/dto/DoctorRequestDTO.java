package com.praveen.doctor_appointment_system.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.Set;

public class DoctorRequestDTO {

    @NotBlank(message = "Name is required")
    private String name;

    private String specialization;

    @NotEmpty(message = "Available Days are required")
    private Set<DayOfWeek> availableDays;

    @NotNull(message = "Weekly Off day is required")
    private DayOfWeek weeklyOff;

    @NotNull(message = "Start time is required")
    private LocalTime startTime;

    @NotNull(message = "End time is required")
    private LocalTime endTime;

    @NotNull(message = "SlotDurationMinutes is required")
    private Integer slotDurationMinutes;

    @NotNull(message = "Manual slot limit is required")
    private Boolean useManualSlotLimit;

    private Integer manualSlotLimit;

    public @NotBlank(message = "Name is required") String getName() {
        return name;
    }

    public void setName(@NotBlank(message = "Name is required") String name) {
        this.name = name;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public @NotEmpty(message = "Available Days are required") Set<DayOfWeek> getAvailableDays() {
        return availableDays;
    }

    public void setAvailableDays(@NotEmpty(message = "Available Days are required") Set<DayOfWeek> availableDays) {
        this.availableDays = availableDays;
    }

    public @NotNull(message = "Weekly Off day is required") DayOfWeek getWeeklyOff() {
        return weeklyOff;
    }

    public void setWeeklyOff(@NotNull(message = "Weekly Off day is required") DayOfWeek weeklyOff) {
        this.weeklyOff = weeklyOff;
    }

    public @NotNull(message = "Start time is required") LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(@NotNull(message = "Start time is required") LocalTime startTime) {
        this.startTime = startTime;
    }

    public @NotNull(message = "End time is required") LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(@NotNull(message = "End time is required") LocalTime endTime) {
        this.endTime = endTime;
    }

    public @NotNull(message = "SlotDurationMinutes is required") Integer getSlotDurationMinutes() {
        return slotDurationMinutes;
    }

    public void setSlotDurationMinutes(@NotNull(message = "SlotDurationMinutes is required") Integer slotDurationMinutes) {
        this.slotDurationMinutes = slotDurationMinutes;
    }

    public @NotNull(message = "Manual slot limit is required") Boolean getUseManualSlotLimit() {
        return useManualSlotLimit;
    }

    public void setUseManualSlotLimit(@NotNull(message = "Manual slot limit is required") Boolean useManualSlotLimit) {
        this.useManualSlotLimit = useManualSlotLimit;
    }

    public Integer getManualSlotLimit() {
        return manualSlotLimit;
    }

    public void setManualSlotLimit(Integer manualSlotLimit) {
        this.manualSlotLimit = manualSlotLimit;
    }
}
