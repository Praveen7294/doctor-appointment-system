package com.praveen.doctor_appointment_system.exception;

public class NoAvailableSlotException extends RuntimeException {

    public NoAvailableSlotException(String message) {
        super(message);
    }
}
