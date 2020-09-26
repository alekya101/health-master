package com.alekya.application.hc.aspects;

import com.alekya.application.hc.annotations.ValidateRequest;
import com.alekya.application.hc.endpoints.utils.IValidate;
import com.alekya.application.hc.exceptions.HealthcareException;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@Aspect
public class RequestValidationAspect {

    @Before("@annotation(validateRequest)")
    public void validateRequest(JoinPoint joinPoint, ValidateRequest validateRequest) {
        Arrays.stream(joinPoint.getArgs())
                .filter(arg -> arg instanceof IValidate)
                .map(arg -> (IValidate) arg)
                .map(IValidate::validate)
                .filter(valid -> !valid.isValid())
                .findFirst()
                .ifPresent(invalid -> { throw HealthcareException.exception(invalid.getMessage()); });
    }

}
