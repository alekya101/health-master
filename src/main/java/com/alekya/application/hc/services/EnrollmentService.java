package com.alekya.application.hc.services;

import com.alekya.application.hc.beans.Dependent;
import com.alekya.application.hc.beans.Enrolled;
import com.alekya.application.hc.exceptions.HealthcareException;
import com.alekya.application.hc.repositories.DependentRepository;
import com.alekya.application.hc.repositories.EnrolledRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EnrollmentService implements IEnrollmentService {

    private final DependentRepository dependentRepository;

    private final EnrolledRepository enrolledRepository;

    @Override
    public Enrolled enroll(Enrolled enrolled) throws HealthcareException {
        if (enrolledRepository.findById(enrolled.getSsn()).isPresent()) {
            throw HealthcareException.alreadyEnrolled(enrolled.getSsn());
        }
        return saveOrUpdateEnrollment(enrolled);
    }

    @Override
    public Enrolled update(Enrolled enrolled) throws HealthcareException {
        if (enrolledRepository.findById(enrolled.getSsn()).isEmpty()) {
            throw HealthcareException.notEnrolled(enrolled.getSsn());
        }
        return saveOrUpdateEnrollment(enrolled);
    }

    @Override
    public void deleteBySsn(String ssn) throws HealthcareException {
        enrolledRepository.deleteById(ssn);
    }

    @Override
    public Dependent addDependent(Dependent dependent) {
        if (dependentRepository.findById(dependent.getSsn()).isPresent()) {
            throw HealthcareException.alreadyEnrolled(dependent.getSsn());
        }
        return dependentRepository.save(dependent);
    }

    @Override
    public void removeDependent(String dependentSsn) {
        if (dependentRepository.findById(dependentSsn).isEmpty()) {
            throw HealthcareException.notEnrolled(dependentSsn);
        }
        dependentRepository.deleteById(dependentSsn);
    }

    @Override
    public Dependent updateDependent(Dependent dependent) throws HealthcareException {
        return dependentRepository.save(dependent);
    }

    private Enrolled saveOrUpdateEnrollment(Enrolled enrolled) {
        final Enrolled newEnrollment = enrolledRepository.save(enrolled);

        final Set<Dependent> dependents = enrolled.getDependents().stream()
                .peek(dependent -> dependent.setEnrolled(enrolled))
                .collect(Collectors.toSet());

        dependentRepository.saveAll(dependents);

        return newEnrollment;
    }
}
