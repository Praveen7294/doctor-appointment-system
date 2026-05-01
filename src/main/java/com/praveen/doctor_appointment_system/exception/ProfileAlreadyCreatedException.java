package com.praveen.doctor_appointment_system.exception;

public class ProfileAlreadyCreatedException extends RuntimeException {

    public ProfileAlreadyCreatedException(String message) {
        super(message);
    }
}
