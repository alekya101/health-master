package com.alekya.application.hc.exceptions;

public class HealthcareException extends RuntimeException {

    private static final String ALREADY_ENROLLED_MESSAGE = "New with SSN: %s is already enrolled for health care program";
    private static final String NOT_ENROLLED_MESSAGE = "User with SSN: %s is not already enrolled";

    private HealthcareException(String message) {
        super(message);
    }

    public static HealthcareException alreadyEnrolled(String ssn) {
        return new HealthcareException(String.format(ALREADY_ENROLLED_MESSAGE, ssn));
    }

    public static HealthcareException notEnrolled(String ssn) {
        return new HealthcareException(String.format(NOT_ENROLLED_MESSAGE, ssn));
    }

    public static HealthcareException exception(String message) {
        return new HealthcareException(message);
    }
}
