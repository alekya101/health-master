package com.alekya.application.hc.services;

import com.alekya.application.hc.beans.Dependent;
import com.alekya.application.hc.beans.Enrolled;
import com.alekya.application.hc.exceptions.HealthcareException;
import org.springframework.lang.NonNull;

public interface IEnrollmentService {

    /**
     * Enroll a new person for health care program
     * @param enrolled
     * @return persisted {@link Enrolled}
     * @throws HealthcareException
     */
    Enrolled enroll(@NonNull final Enrolled enrolled) throws HealthcareException;

    /**
     * Update details of an enrolled person
     * @param enrolled
     * @return updated details of {@link Enrolled}
     * @throws HealthcareException
     */
    Enrolled update(@NonNull final Enrolled enrolled) throws HealthcareException;

    /**
     * Delete an enrollment by SSN
     * @param ssn
     * @return Boolean.TRUE if deleted successfully, false otherwise
     * @throws HealthcareException
     */
    void deleteBySsn(@NonNull String ssn) throws HealthcareException;

    /**
     * Add single {@link Dependent} to the person
     * @param dependent
     * @return Enrolled
     */
    Dependent addDependent(@NonNull final Dependent dependent);

    /**
     * Removes a {@link Dependent} from {@link Enrolled}
     * @param dependentSsn
     * @return Enrolled
     */
    void removeDependent(@NonNull String dependentSsn);

    /**
     * Update {@link Dependent}
     * @param dependent
     * @return modified {@link Dependent}
     * @throws HealthcareException
     */
    Dependent updateDependent(@NonNull final Dependent dependent) throws HealthcareException;

}
