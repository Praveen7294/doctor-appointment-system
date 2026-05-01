package com.praveen.doctor_appointment_system.mapper;


import com.praveen.doctor_appointment_system.dto.AppointmentResponseDTO;
import com.praveen.doctor_appointment_system.dto.AvailabilityResponseDTO;
import com.praveen.doctor_appointment_system.entity.Appointment;

public class AppointmentMapper {

     public static AppointmentResponseDTO toDto(Appointment appointment) {
         AppointmentResponseDTO response = new AppointmentResponseDTO();

         response.setAppointmentId(appointment.getId());

         response.setDoctorId(appointment.getDoctor().getId());
         response.setDoctorName(appointment.getDoctor().getName());

         response.setPatientId(appointment.getPatient().getId());
         response.setPatientName(appointment.getPatient().getName());
         response.setPatientPhoneNumber(appointment.getPatient().getPhoneNumber());
         response.setPatientDateOfBirth(appointment.getPatient().getDateOfBirth());
         response.setPatientGender(appointment.getPatient().getGender());
         response.setPatientAddress(appointment.getPatient().getAddress());

         response.setAppointmentDate(appointment.getAppointmentDate());
         response.setConsultingTime(appointment.getConsultingTime());
         response.setReportingTime(appointment.getReportingTime());
         response.setTokenNumber(appointment.getTokenNumber());
         response.setReasonForVisit(appointment.getReasonForVisit());
         response.setAppointmentStatus(appointment.getStatus());
         response.setCreatedAt(appointment.getCreatedAt());

         return response;
     }
}
