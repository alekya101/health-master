package com.alekya.application.hc.services;

import com.alekya.application.hc.beans.Dependent;
import com.alekya.application.hc.beans.Enrolled;
import com.alekya.application.hc.exceptions.HealthcareException;
import com.alekya.application.hc.repositories.DependentRepository;
import com.alekya.application.hc.repositories.EnrolledRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EnrollmentServiceTest {

    @InjectMocks
    private EnrollmentService sut;

    @Mock
    private EnrolledRepository enrolledRepository;

    @Mock
    private DependentRepository dependentRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void enroll() {
        final Enrolled enrolled = new Enrolled(false, "111-111", "A111", "name", LocalDate.now(), new HashSet<Dependent>());

        assertDoesNotThrow(() -> {
            return sut.enroll(enrolled);
        });

        when(enrolledRepository.findById("A111")).thenReturn(Optional.of(enrolled));
        assertThrows(HealthcareException.class, () -> sut.enroll(enrolled));
    }

    @Test
    void update() {
        final Enrolled enrolled = new Enrolled(false, "111-111", "A111", "name", LocalDate.now(), new HashSet<Dependent>());

        when(enrolledRepository.findById("A111")).thenReturn(Optional.of(enrolled));
        assertDoesNotThrow(() -> {
            return sut.update(enrolled);
        });

        when(enrolledRepository.findById("A111")).thenReturn(Optional.empty());
        assertThrows(HealthcareException.class, () -> sut.update(enrolled));
    }

    @Test
    void deleteBySsn() {
        sut.deleteBySsn("ssn");
        verify(enrolledRepository, times(1)).deleteById(eq("ssn"));
    }

    @Test
    void addDependent() {
        final Enrolled enrolled = new Enrolled(false, "111-111", "A111", "name", LocalDate.now(), new HashSet<Dependent>());
        final Dependent dependent = new Dependent("ssn", "name", LocalDate.now(), enrolled);
        sut.addDependent(dependent);

        verify(dependentRepository, times(1)).save(eq(dependent));
    }

    @Test
    void updateDependent() {
        final Enrolled enrolled = new Enrolled(false, "111-111", "A111", "name", LocalDate.now(), new HashSet<Dependent>());
        final Dependent dependent = new Dependent("ssn", "name", LocalDate.now(), enrolled);
        sut.updateDependent(dependent);

        verify(dependentRepository, times(1)).save(eq(dependent));
    }
}