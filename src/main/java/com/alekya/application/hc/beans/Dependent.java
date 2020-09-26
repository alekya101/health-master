package com.alekya.application.hc.beans;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import java.time.LocalDate;

@Entity
@Getter
@Setter
public class Dependent extends Person {

    /*
     * I could have very well used @OneToMany on the parent table but when the child table really has many records,
     * @OneToMany is not the approach you want to take. All data is stored in memory and thus subject to such
     * constraints. Queries are any ways used to fetch LAZY objects.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    private Enrolled enrolled;

    @Builder
    public Dependent(String ssn, String name, LocalDate dateOfBirth, Enrolled enrolled) {
        super(ssn, name, dateOfBirth);
        this.enrolled = enrolled;
    }
}
