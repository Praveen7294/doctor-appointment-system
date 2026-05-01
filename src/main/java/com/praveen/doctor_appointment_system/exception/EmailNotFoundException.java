package com.praveen.doctor_appointment_system.exception;

public class EmailNotFoundException extends RuntimeException {

    public EmailNotFoundException(String message) {
        super(message);
    }
}
