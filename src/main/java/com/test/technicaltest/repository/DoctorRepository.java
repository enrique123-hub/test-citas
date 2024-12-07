package com.test.technicaltest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.test.technicaltest.model.Doctor;

public interface DoctorRepository extends JpaRepository<Doctor, String> {

    Doctor findByDoctorId(String doctorId);
    
}
