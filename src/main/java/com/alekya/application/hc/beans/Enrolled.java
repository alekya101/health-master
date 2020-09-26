package com.alekya.application.hc.beans;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Transient;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
public class Enrolled extends Person {

    @Column(nullable = false)
    private Boolean activationStatus;

    private String phoneNumber;

    @Transient
    private Set<Dependent> dependents;

    @Builder
    public Enrolled(
            Boolean activationStatus,
            String phoneNumber,
            String ssn,
            String name,
            LocalDate dateOfBirth,
            Set<Dependent> dependents) {

        super(ssn, name, dateOfBirth);
        this.activationStatus = activationStatus;
        this.phoneNumber = phoneNumber;
        this.dependents = dependents == null ? new HashSet<>() : dependents;
    }
}
