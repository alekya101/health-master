package com.alekya.application.hc.beans;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
@AllArgsConstructor
@Getter
@Setter
public class Person {

	@Id
    protected String ssn;

    @Column(nullable = false)
    protected String name;

    @Column(nullable = false)
    protected LocalDate dateOfBirth;

}
