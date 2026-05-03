package com.praveen.doctor_appointment_system.exception;

public class UnauthorizedRoleException extends RuntimeException {

    public UnauthorizedRoleException(String message) {
        super(message);
    }
}
