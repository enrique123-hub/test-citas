package com.test.technicaltest.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.test.technicaltest.model.Appointment;
import com.test.technicaltest.model.Doctor;
import com.test.technicaltest.model.Office;

public interface AppointmentRepository extends JpaRepository<Appointment, String> {

        Appointment findByAppointmentId(String appointmentId);

        List<Appointment> findByAppointmentTimeContainingAndOffice_OfficeIdAndDoctor_DoctorId(
                        String appointmentTime, String officeId, String doctorId);

        List<Appointment> findByAppointmentTimeContainingAndOffice_OfficeId(
                        String appointmentTime, String officeId);

        List<Appointment> findByAppointmentTimeContainingAndDoctor_DoctorId(
                        String appointmentTime, String doctorId);

        List<Appointment> findByAppointmentTimeContaining(String appointmentTime);

        List<Appointment> findByOffice_OfficeId(String officeId);

        List<Appointment> findByDoctor_DoctorId(String doctorId);

        Optional<Appointment> findByOfficeAndAppointmentTime(Office office, String appointmentTime);

        Optional<Appointment> findByDoctorAndAppointmentTime(Doctor doctor, String appointmentTime);

        List<Appointment> findByPatientName(String patientName);

}
