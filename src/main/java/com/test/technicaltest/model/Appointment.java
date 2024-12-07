package com.test.technicaltest.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import javax.persistence.Column;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Getter
@Setter
@EqualsAndHashCode(of = "appointmentId")
@Table(name = "APPOINTMENTS")
public class Appointment {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "uuid2")
    @Column(name = "APPOINTMENT_ID")
    private String appointmentId;

    @ManyToOne
    @JoinColumn(name = "OFFICE_ID", referencedColumnName = "OFFICE_ID", nullable = false)
    private Office office;

    @ManyToOne
    @JoinColumn(name = "DOCTOR_ID", referencedColumnName = "DOCTOR_ID", nullable = false)
    private Doctor doctor;

    @Column(name = "APPOINTMENT_TIME", nullable = false)
    private String appointmentTime;

    @Column(name = "PATIENT_NAME", length = 100, nullable = false)
    private String patientName;
}