package com.praveen.doctor_appointment_system.exception;

import com.praveen.doctor_appointment_system.dto.ErrorResponseDTO;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import org.slf4j.Logger;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponseDTO> handleInvalidRoleException(HttpMessageNotReadableException ex) {

        logger.error("Invalid request body: {}", ex.getMessage());

        ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO();
        errorResponseDTO.setError("INVALID_ROLE");
        errorResponseDTO.setMessage(ex.getMessage());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponseDTO);
    }

    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<ErrorResponseDTO> handleEmailAlreadyExistsException(EmailAlreadyExistsException ex) {

        logger.error("Email already exists: {}", ex.getMessage());

        ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO();
        errorResponseDTO.setMessage("EMAIL_ALREADY_EXISTS");
        errorResponseDTO.setMessage(ex.getMessage());

        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponseDTO);
    }

    @ExceptionHandler(EmailNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> handleEmailNotFoundException(EmailNotFoundException ex) {

        logger.error("Email not found: {}", ex.getMessage());

        ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO();
        errorResponseDTO.setMessage("EMAIL_NOT_FOUND");
        errorResponseDTO.setMessage(ex.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponseDTO);
    }

    @ExceptionHandler(PasswordMismatchException.class)
    public ResponseEntity<ErrorResponseDTO> handlePasswordMismatchException(PasswordMismatchException ex) {

        logger.error("Password not match: {}", ex.getMessage());

        ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO();
        errorResponseDTO.setMessage("PASSWORD_NOT_MATCH");
        errorResponseDTO.setMessage(ex.getMessage());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponseDTO);
    }

    @ExceptionHandler(UnauthorizedRoleException.class)
    public ResponseEntity<ErrorResponseDTO> handleUnauthorizedRoleException(UnauthorizedRoleException ex) {

        logger.error("Role do not match: {}", ex.getMessage());

        ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO();
        errorResponseDTO.setMessage("UNAUTHORIZED_ROLE");
        errorResponseDTO.setMessage(ex.getMessage());

        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(errorResponseDTO);
    }

    @ExceptionHandler(ProfileAlreadyCreatedException.class)
    public ResponseEntity<ErrorResponseDTO> handleProfileAlreadyCreatedException(ProfileAlreadyCreatedException ex) {

        logger.error("Profile already created: {}", ex.getMessage());

        ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO();
        errorResponseDTO.setMessage("PROFILE_ALREADY_EXISTS");
        errorResponseDTO.setMessage(ex.getMessage());

        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponseDTO);
    }

    @ExceptionHandler(DoctorNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> handleDoctorNotFoundException(DoctorNotFoundException ex) {

        logger.error("Doctor not found: {}", ex.getMessage());

        ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO();
        errorResponseDTO.setMessage("DOCTOR_NOT_FOUND");
        errorResponseDTO.setMessage(ex.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponseDTO);
    }

    @ExceptionHandler(PatientNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> handlePatientNotFoundException(PatientNotFoundException ex) {

        logger.error("Patient not found: {}", ex.getMessage());

        ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO();
        errorResponseDTO.setMessage("PATIENT_NOT_FOUND");
        errorResponseDTO.setMessage(ex.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponseDTO);
    }

    @ExceptionHandler(DoctorNotAvailableException.class)
    public ResponseEntity<ErrorResponseDTO> handleDoctorNotAvailableException(DoctorNotAvailableException ex) {

        logger.error("Doctor not available: {}", ex.getMessage());

        ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO();
        errorResponseDTO.setMessage("DOCTOR_NOT_AVAILABLE");
        errorResponseDTO.setMessage(ex.getMessage());

        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponseDTO);
    }

    @ExceptionHandler(InvalidAppointmentSlotException.class)
    public ResponseEntity<ErrorResponseDTO> handleInvalidAppointmentSlotException(InvalidAppointmentSlotException ex) {

        logger.error("Invalid appointment slot: {}", ex.getMessage());

        ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO();
        errorResponseDTO.setMessage("INVALID_SLOT");
        errorResponseDTO.setMessage(ex.getMessage());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponseDTO);
    }

    @ExceptionHandler(SlotAlreadyBookedException.class)
    public ResponseEntity<ErrorResponseDTO> handleSlotAlreadyBookedException(SlotAlreadyBookedException ex) {

        logger.error("Slots are already booked: {}", ex.getMessage());

        ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO();
        errorResponseDTO.setMessage("ALREADY_BOOKED");
        errorResponseDTO.setMessage(ex.getMessage());

        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponseDTO);
    }

    @ExceptionHandler(NoAvailableSlotException.class)
    public ResponseEntity<ErrorResponseDTO> handleNoAvailableSlotException(NoAvailableSlotException ex) {

        logger.error("No Slots are available: {}", ex.getMessage());

        ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO();
        errorResponseDTO.setMessage("NO_SLOT");
        errorResponseDTO.setMessage(ex.getMessage());

        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponseDTO);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDTO> handleGenericException(Exception ex) {

        logger.error("Internal server error: {}", ex.getMessage());

        ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO();
        errorResponseDTO.setMessage("INTERNAL_SERVER_ERROR");
        errorResponseDTO.setMessage(ex.getMessage());

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponseDTO);
    }
}
