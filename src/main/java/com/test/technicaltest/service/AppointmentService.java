package com.test.technicaltest.service;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.technicaltest.model.Appointment;
import com.test.technicaltest.model.Doctor;
import com.test.technicaltest.model.Office;
import com.test.technicaltest.repository.AppointmentRepository;

@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    public Appointment getAppointmentId(String appointmentId){
        return appointmentRepository.findByAppointmentId(appointmentId);
    }

    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }

    public Appointment createAppointment(Appointment appointment) {
        return appointmentRepository.save(appointment);
    }

    public List<Appointment> getFilteredAppointments(String date, String officeId, String doctorId) {

        if (date != null && officeId != null && doctorId != null) {
            return appointmentRepository.findByAppointmentTimeContainingAndOffice_OfficeIdAndDoctor_DoctorId(date,
                    officeId, doctorId);
        } else if (date != null && officeId != null) {
            return appointmentRepository.findByAppointmentTimeContainingAndOffice_OfficeId(date, officeId);
        } else if (date != null && doctorId != null) {
            return appointmentRepository.findByAppointmentTimeContainingAndDoctor_DoctorId(date, doctorId);
        } else if (date != null) {
            return appointmentRepository.findByAppointmentTimeContaining(date);
        } else if (officeId != null) {
            return appointmentRepository.findByOffice_OfficeId(officeId);
        } else if (doctorId != null) {
            return appointmentRepository.findByDoctor_DoctorId(doctorId);
        } else {
            return appointmentRepository.findAll();
        }
    }

    public boolean validateAppointment(Office office, Doctor doctor, String patientName, String appointmentTime) {

        Optional<Appointment> existingOfficeAppointment = appointmentRepository.findByOfficeAndAppointmentTime(office,
                appointmentTime);
        if (existingOfficeAppointment.isPresent()) {
            throw new IllegalArgumentException("Ya existe una cita en este consultorio a esta hora.");
        }

        Optional<Appointment> existingDoctorAppointment = appointmentRepository.findByDoctorAndAppointmentTime(doctor,
                appointmentTime);
        if (existingDoctorAppointment.isPresent()) {
            throw new IllegalArgumentException("Este doctor ya tiene una cita a esta hora.");
        }

        List<Appointment> patientAppointments = appointmentRepository.findByPatientName(patientName);
        for (Appointment existingAppointment : patientAppointments) {
            LocalTime existingTime = LocalTime.parse(existingAppointment.getAppointmentTime(),
                    DateTimeFormatter.ofPattern("HH:mm"));
            LocalTime newTime = LocalTime.parse(appointmentTime, DateTimeFormatter.ofPattern("HH:mm"));

            if (existingTime.equals(newTime)) {
                throw new IllegalArgumentException("El paciente ya tiene una cita a esta hora.");
            }
            if (Math.abs(existingTime.toSecondOfDay() - newTime.toSecondOfDay()) < 2 * 60 * 60) {
                throw new IllegalArgumentException("La cita debe tener al menos 2 horas de diferencia.");
            }
        }

        return true;
    }

    public void cancelAppointment(String appointmentId) {
        Appointment appointment = appointmentRepository.findById(appointmentId)
                .orElseThrow(() -> new IllegalArgumentException("Cita no encontrada"));
    
        appointmentRepository.delete(appointment);
    }

    public void updateAppointment(Appointment appointment) {
        appointmentRepository.save(appointment);
    }
    
    

}
