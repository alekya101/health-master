package com.alekya.application.hc.aspects;

import com.alekya.application.hc.annotations.ValidateRequest;
import com.alekya.application.hc.endpoints.utils.IValidate;
import com.alekya.application.hc.endpoints.utils.Valid;
import com.alekya.application.hc.exceptions.HealthcareException;
import org.aspectj.lang.JoinPoint;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class RequestValidationAspectTest {

    @Test
    void validateRequest() {
        final RequestValidationAspect sut = new RequestValidationAspect();
        final JoinPoint joinPoint = mock(JoinPoint.class);
        when(joinPoint.getArgs()).thenReturn(new TestInvalid[] { new TestInvalid() });
        final ValidateRequest validateRequest = mock(ValidateRequest.class);
        assertThrows(HealthcareException.class, () -> sut.validateRequest(joinPoint, validateRequest));

        when(joinPoint.getArgs()).thenReturn(new TestValid[]{new TestValid()});
        assertDoesNotThrow(() -> sut.validateRequest(joinPoint, validateRequest));
    }

    private class TestValid implements IValidate {
        @Override
        public Valid validate() {
            return Valid.valid();
        }
    }

    private class TestInvalid implements IValidate {
        @Override
        public Valid validate() {
            return Valid.invalid("Error");
        }
    }

}