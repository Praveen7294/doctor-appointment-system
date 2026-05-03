package com.praveen.doctor_appointment_system.dto;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.Set;

public class DoctorUpdateRequestDTO {
    private String name;

    private String specialization;

    private Set<DayOfWeek> availableDays;

    private DayOfWeek weeklyOff;

    private LocalTime startTime;

    private LocalTime endTime;

    private Integer slotDurationMinutes;

    private Boolean useManualSlotLimit;

    private Integer manualSlotLimit;

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

    public DayOfWeek getWeeklyOff() {
        return weeklyOff;
    }

    public void setWeeklyOff(DayOfWeek weeklyOff) {
        this.weeklyOff = weeklyOff;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public Integer getSlotDurationMinutes() {
        return slotDurationMinutes;
    }

    public void setSlotDurationMinutes(Integer slotDurationMinutes) {
        this.slotDurationMinutes = slotDurationMinutes;
    }

    public Boolean getUseManualSlotLimit() {
        return useManualSlotLimit;
    }

    public void setUseManualSlotLimit(Boolean useManualSlotLimit) {
        this.useManualSlotLimit = useManualSlotLimit;
    }

    public Integer getManualSlotLimit() {
        return manualSlotLimit;
    }

    public void setManualSlotLimit(Integer manualSlotLimit) {
        this.manualSlotLimit = manualSlotLimit;
    }
}
