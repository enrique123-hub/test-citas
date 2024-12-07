package com.test.technicaltest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.technicaltest.model.Doctor;
import com.test.technicaltest.repository.DoctorRepository;

@Service
public class DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;

    public List<Doctor> getListDoctors(){
        return doctorRepository.findAll();
    }

    public Doctor getDoctorById(String doctorId) {
        return doctorRepository.findByDoctorId(doctorId);
    }
    
}
