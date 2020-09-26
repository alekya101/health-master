package com.alekya.application.hc.endpoints.tos;

import com.alekya.application.hc.beans.Dependent;
import com.alekya.application.hc.endpoints.utils.ICanMapToBean;
import com.alekya.application.hc.endpoints.utils.IValidate;
import com.alekya.application.hc.endpoints.utils.Valid;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DependentRequest extends AddUpdatePersonRequest implements IValidate, ICanMapToBean<Dependent> {

    private EnrollmentRequest enrolled;

    @Override
    public Dependent toBean() {
        return Dependent.builder()
                .ssn(ssn)
                .name(name)
                .dateOfBirth(dateOfBirth)
                .enrolled(enrolled.toBean())
                .build();
    }

    @Override
    public Valid validate() {

        // Check if enrollment is valid
        final Valid enrolledValidation = enrolled.validate();
        if (!enrolledValidation.isValid()) {
            return enrolledValidation;
        }

        // Check if the bean itself is valid
        final Valid superValid = super.validate();
        if (!superValid.isValid()) {
            return superValid;
        }

        return Valid.valid();
    }
}
