package com.test.technicaltest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.test.technicaltest.model.Office;

public interface OfficeRepository extends JpaRepository<Office, String>{

    Office findByOfficeId(String officeId);

}
