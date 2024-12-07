package com.test.technicaltest.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Getter
@Setter
@EqualsAndHashCode(of = "officeId")
@Table(name = "OFFICE")
public class Office {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "uuid2")
    @Column(name = "OFFICE_ID")
    private String officeId;

    @Column(name = "OFFICE_NUMBER", nullable = false, unique = true)
    private int officeNumber;

    @Column(name = "FLOOR", nullable = false)
    private int floor;

}
