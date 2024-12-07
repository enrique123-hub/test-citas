package com.test.technicaltest.model;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FiltersResponse {
    private List<Doctor> doctors;
    private List<Office> offices;

    public FiltersResponse(List<Doctor> doctors, List<Office> offices) {
        this.doctors = doctors;
        this.offices = offices;
    }
}
