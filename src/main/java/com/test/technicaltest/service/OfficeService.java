package com.test.technicaltest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.technicaltest.model.Office;
import com.test.technicaltest.repository.OfficeRepository;

@Service
public class OfficeService {

    @Autowired
    private OfficeRepository officesRepository;

    public List<Office> getListOffices(){
        return officesRepository.findAll();
    }

    public Office getOfficeById(String officeId) {
        return officesRepository.findByOfficeId(officeId);
    }
    
}
