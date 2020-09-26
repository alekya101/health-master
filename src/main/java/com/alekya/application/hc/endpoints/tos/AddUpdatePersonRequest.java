package com.alekya.application.hc.endpoints.tos;

import com.alekya.application.hc.endpoints.utils.IValidate;
import com.alekya.application.hc.endpoints.utils.Valid;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDate;

@Setter
@Getter
public class AddUpdatePersonRequest implements IValidate {

    protected String ssn;

    protected String name;

    protected LocalDate dateOfBirth;

    @Override
    public Valid validate() {
        if (StringUtils.isEmpty(ssn)) {
            return Valid.invalid("SSN must be provided");
        }
        if (StringUtils.isEmpty(name)) {
            Valid.invalid("Name must be provided");
        }
        if (dateOfBirth == null) {
            Valid.invalid("Date of birth must be provided");
        }
        return Valid.valid();
    }

}
