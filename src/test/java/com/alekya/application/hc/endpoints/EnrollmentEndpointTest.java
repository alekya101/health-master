package com.alekya.application.hc.endpoints;

import com.alekya.application.hc.beans.Dependent;
import com.alekya.application.hc.beans.Enrolled;
import com.alekya.application.hc.endpoints.tos.DependentRequest;
import com.alekya.application.hc.endpoints.tos.EnrollmentRequest;
import com.alekya.application.hc.services.EnrollmentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class EnrollmentEndpointTest {

    @InjectMocks
    private EnrollmentEndpoint sut;

    @Mock
    private EnrollmentService enrollmentService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void enroll() {
        final Enrolled enrolled = Enrolled.builder()
                .ssn("1111")
                .name("abc")
                .dateOfBirth(LocalDate.now())
                .build();
        when(enrollmentService.enroll(any())).thenReturn(enrolled);

        final EnrollmentRequest enrollmentRequest = new EnrollmentRequest();

        enrollmentRequest.setSsn("1111");
        enrollmentRequest.setName("abc");
        final Enrolled result = sut.enroll(enrollmentRequest);

        assertThat(result).isNotNull();
        assertThat(result.getSsn()).isEqualTo("1111");
    }

    @Test
    void updateEnrolled() {
        final Enrolled enrolled = Enrolled.builder()
                .ssn("1111")
                .name("abc")
                .dateOfBirth(LocalDate.now())
                .build();
        when(enrollmentService.enroll(any())).thenReturn(enrolled);

        final EnrollmentRequest enrollmentRequest = new EnrollmentRequest();

        enrollmentRequest.setSsn("1111");
        enrollmentRequest.setName("abc");
        final Enrolled result = sut.enroll(enrollmentRequest);

        assertThat(result).isNotNull();
        assertThat(result.getSsn()).isEqualTo("1111");
    }

    @Test
    void deleteEnrolled() {
        sut.deleteEnrolled("1111");
        verify(enrollmentService, times(1)).deleteBySsn(eq("1111"));

    }

    @Test
    void addDependent() {
        final DependentRequest dependentRequest = new DependentRequest();
        dependentRequest.setSsn("1111");
        dependentRequest.setName("abc");
        dependentRequest.setDateOfBirth(LocalDate.now());
        dependentRequest.setEnrolled(new EnrollmentRequest());
        when(enrollmentService.addDependent(dependentRequest.toBean())).thenReturn(dependentRequest.toBean());
        assertThat(sut.addDependent(dependentRequest)).isNull();

    }

    @Test
    void deleteDependent() {

        sut.deleteDependent("1111");
        verify(enrollmentService, times(1)).removeDependent(eq("1111"));
    }

    @Test
    void updateDependent() {
        final DependentRequest dependentRequest = new DependentRequest();
        dependentRequest.setSsn("1111");
        dependentRequest.setName("abc");
        dependentRequest.setDateOfBirth(LocalDate.now());
        dependentRequest.setEnrolled(new EnrollmentRequest());
        when(enrollmentService.updateDependent(dependentRequest.toBean())).thenReturn(dependentRequest.toBean());
        assertThat(sut.updateDependent(dependentRequest)).isNull();
    }
}