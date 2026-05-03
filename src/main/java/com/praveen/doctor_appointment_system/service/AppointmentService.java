package com.praveen.doctor_appointment_system.service;

import com.praveen.doctor_appointment_system.dto.AppointmentRequestDTO;
import com.praveen.doctor_appointment_system.dto.AppointmentResponseDTO;
import com.praveen.doctor_appointment_system.dto.AvailabilityResponseDTO;
import com.praveen.doctor_appointment_system.entity.Appointment;
import com.praveen.doctor_appointment_system.entity.AppointmentStatus;
import com.praveen.doctor_appointment_system.entity.Doctor;
import com.praveen.doctor_appointment_system.entity.Patient;
import com.praveen.doctor_appointment_system.entity.Role;
import com.praveen.doctor_appointment_system.entity.User;
import com.praveen.doctor_appointment_system.exception.DoctorNotAvailableException;
import com.praveen.doctor_appointment_system.exception.DoctorNotFoundException;
import com.praveen.doctor_appointment_system.exception.InvalidAppointmentSlotException;
import com.praveen.doctor_appointment_system.exception.NoAvailableSlotException;
import com.praveen.doctor_appointment_system.exception.PatientNotFoundException;
import com.praveen.doctor_appointment_system.exception.SlotAlreadyBookedException;
import com.praveen.doctor_appointment_system.exception.UnauthorizedRoleException;
import com.praveen.doctor_appointment_system.mapper.AppointmentMapper;
import com.praveen.doctor_appointment_system.repository.AppointmentRepository;
import com.praveen.doctor_appointment_system.repository.DoctorRepository;

import com.praveen.doctor_appointment_system.repository.PatientRepository;
import com.praveen.doctor_appointment_system.security.SecurityUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;

    public AppointmentService(AppointmentRepository appointmentRepository,
                              DoctorRepository doctorRepository,
                              PatientRepository patientRepository) {
        this.appointmentRepository = appointmentRepository;
        this.doctorRepository = doctorRepository;
        this.patientRepository = patientRepository;
    }

    public AvailabilityResponseDTO checkAvailableSlot(UUID doctorId, LocalDate date) {

        if (date == null) {
            date = LocalDate.now();
        }

        Doctor doctor = doctorRepository.findById(doctorId).orElseThrow(
                () -> new DoctorNotFoundException("Doctor Not Found With Id: " + doctorId));

        AvailabilityResponseDTO response = new AvailabilityResponseDTO();

        response.setDoctorId(doctor.getId());
        response.setDoctorName(doctor.getName());
        response.setDate(date);
        response.setStartTime(doctor.getStartTime());
        response.setEndTime(doctor.getEndTime());
        response.setSlotDurationMinutes(doctor.getSlotDurationMinutes());

        if(!doctor.getAvailableDays().contains(date.getDayOfWeek())) {
            response.setAvailableSlotsCount(0);
            response.setAvailableSlots(List.of());
            response.setMessage("Doctor not available on this day");
            return response;
        }

        List<LocalTime> allSlots = generateAllSlots(
                doctor.getStartTime(),
                doctor.getEndTime(),
                doctor.getSlotDurationMinutes()
        );

        if (Boolean.TRUE.equals(doctor.getUseManualSlotLimit())
                && doctor.getManualSlotLimit() != null) {
            int limit = Math.min(doctor.getManualSlotLimit(), allSlots.size());
            allSlots = allSlots.subList(0, limit);
        }

        List<Appointment> appointments =
                appointmentRepository.findAppointmentByDoctorAndAppointmentDateAndStatus(
                        doctor, date, AppointmentStatus.BOOKED);

        List<LocalTime> bookedTimes = appointments.stream()
                .map(Appointment::getConsultingTime)
                .toList();

        List<LocalTime> availableSlotsTimes = allSlots.stream()
                .filter(slot -> !bookedTimes.contains(slot))
                .toList();

        response.setAvailableSlotsCount(availableSlotsTimes.size());
        response.setAvailableSlots(availableSlotsTimes);
        response.setMessage("Slot Available");

        return response;
    }

    private static List<LocalTime> generateAllSlots(LocalTime startTime,
                                                    LocalTime endTime,
                                                    int slotDurationMinutes) {

        List<LocalTime> slots = new ArrayList<>();
        LocalTime current = startTime;

        while (current.isBefore(endTime)) {
            slots.add(current);
            current = current.plusMinutes(slotDurationMinutes);
        }

        return slots;
    }

    public AppointmentResponseDTO bookAppointment(AppointmentRequestDTO request) {

        User user = SecurityUtils.getCurrentUserFromSecurityContextHolder();

        if (user.getRole() != Role.PATIENT) {
            throw new UnauthorizedRoleException("Only patients can book appointment");
        }

        Patient patient = patientRepository.findByUser(user).orElseThrow(
                () -> new PatientNotFoundException("Patient profile not found")
        );

        Doctor doctor = doctorRepository.findById(request.getDoctorId()).orElseThrow(
                () -> new DoctorNotFoundException("Doctor Not Found With Id: " + request.getDoctorId()));

        LocalDate appointmentDate = LocalDate.now();
        if (request.getAppointmentDate() != null) {
            appointmentDate = request.getAppointmentDate();
        }

        LocalTime consultingTime = request.getConsultingTime();

        if (!doctor.getAvailableDays().contains(appointmentDate.getDayOfWeek())) {
            throw new DoctorNotAvailableException("Doctor is not available on this date: " + appointmentDate);
        }

        List<LocalTime> allSlots = generateAllSlots(
                doctor.getStartTime(),
                doctor.getEndTime(),
                doctor.getSlotDurationMinutes()
        );

        if (Boolean.TRUE.equals(doctor.getUseManualSlotLimit())
                && doctor.getManualSlotLimit() != null) {
            int limit = Math.min(doctor.getManualSlotLimit(), allSlots.size());
            allSlots = allSlots.subList(0, limit);
        }

        if (!allSlots.contains(consultingTime)) {
            throw new InvalidAppointmentSlotException("Selected time is not a valid slot");
        }

        boolean alreadyBooked =
                appointmentRepository.existsByDoctorAndAppointmentDateAndConsultingTimeAndStatus(
                        doctor,
                        appointmentDate,
                        consultingTime,
                        AppointmentStatus.BOOKED
                );

        if (alreadyBooked) {
            throw new SlotAlreadyBookedException("This slot is already booked");
        }

        long bookedCount = appointmentRepository.countByDoctorAndAppointmentDateAndStatus(
                doctor,
                appointmentDate,
                AppointmentStatus.BOOKED
        );

        if (bookedCount >= allSlots.size()) {
            throw new NoAvailableSlotException("No slots available on this date");
        }

        int tokenNumber = (int) bookedCount + 1;

        Appointment appointment = new Appointment();
        appointment.setDoctor(doctor);
        appointment.setPatient(patient);
        appointment.setAppointmentDate(appointmentDate);
        appointment.setConsultingTime(consultingTime);
        appointment.setReportingTime(consultingTime);
        appointment.setTokenNumber(tokenNumber);
        appointment.setReasonForVisit(request.getReasonForVisit());

        Appointment savedAppointment = appointmentRepository.save(appointment);

        return AppointmentMapper.toDto(savedAppointment);
    }
}
