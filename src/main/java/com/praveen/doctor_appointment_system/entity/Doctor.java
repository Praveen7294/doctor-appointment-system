package com.praveen.doctor_appointment_system.entity;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import org.hibernate.annotations.UuidGenerator;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "doctors")
public class Doctor {

    @Id
    @GeneratedValue
    @UuidGenerator
    private UUID id;

    @Column(nullable = false, length = 50)
    private String name;

    private String specialization;

    @ElementCollection
    @CollectionTable(
            name = "doctor_available_days",
            joinColumns = @JoinColumn(name = "doctor_id")
    )
    @Enumerated(EnumType.STRING)
    @Column(name = "available_days", nullable = false)
    private Set<DayOfWeek> availableDays;

    @Enumerated(EnumType.STRING)
    @Column(name = "weekly_off", nullable = false)
    private DayOfWeek weeklyOff;

    @Column(name = "start_time", nullable = false)
    private LocalTime startTime;

    @Column(name = "end_time", nullable = false)
    private LocalTime endTime;

    @Column(name = "slot_duration_minutes", nullable = false)
    private Integer slotDurationMinutes;

    @Column(name = "use_manual_slot_limit", nullable = false)
    private Boolean useManualSlotLimit;

    @Column(name = "manual_slot_limit")
    private Integer manualSlotLimit;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
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
