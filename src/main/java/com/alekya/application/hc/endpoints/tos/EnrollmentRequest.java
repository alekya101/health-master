package com.alekya.application.hc.endpoints.tos;

import com.alekya.application.hc.beans.Dependent;
import com.alekya.application.hc.beans.Enrolled;
import com.alekya.application.hc.endpoints.utils.ICanMapToBean;
import com.alekya.application.hc.endpoints.utils.IValidate;
import com.alekya.application.hc.endpoints.utils.Valid;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Setter
@Getter
public class EnrollmentRequest extends AddUpdatePersonRequest implements IValidate, ICanMapToBean<Enrolled> {

    private String phoneNumber;

    private Set<Dependent> dependents;

    @Override
    public Valid validate() {
        return super.validate();
    }

    @Override
    public Enrolled toBean() {
        return Enrolled.builder()
                .ssn(ssn)
                .name(name)
                .dateOfBirth(dateOfBirth)
                .phoneNumber(phoneNumber)
                .activationStatus(Boolean.TRUE)
                .dependents(dependents)
                .build();
    }
}
