package com.alekya.application.hc.endpoints;

import com.alekya.application.hc.beans.Dependent;
import com.alekya.application.hc.beans.Enrolled;
import com.alekya.application.hc.endpoints.tos.DependentRequest;
import com.alekya.application.hc.endpoints.tos.EnrollmentRequest;
import com.alekya.application.hc.services.IEnrollmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/enrollment")
@RequiredArgsConstructor
public class EnrollmentEndpoint {

    private final IEnrollmentService enrollmentService = null;

    @PostMapping("/enroll")
    public Enrolled enroll(@RequestBody EnrollmentRequest enrollmentRequest) {
        return enrollmentService.enroll(enrollmentRequest.toBean());
    }

    @PostMapping("/modifyEnrollment")
    public Enrolled updateEnrolled(@RequestBody EnrollmentRequest enrollmentRequest) {
        return enrollmentService.update(enrollmentRequest.toBean());
    }

    @DeleteMapping("/clearEnrollmentBySSN/{ssn}")
    public void deleteEnrolled(@PathVariable String ssn) {
        enrollmentService.deleteBySsn(ssn);
    }

    @PostMapping("/modifyEnrollment/add-dependent")
    public Dependent addDependent(@RequestBody DependentRequest dependentRequest) {
        return enrollmentService.addDependent(dependentRequest.toBean());
    }

    @DeleteMapping("/modifyEnrollment/delete-dependent/{ssn}")
    public void deleteDependent(@PathVariable String ssn) {
        enrollmentService.removeDependent(ssn);
    }

    @PostMapping("/modifyEnrollment/update-dependent")
    public Dependent updateDependent(@RequestBody DependentRequest dependentRequest) {
        return enrollmentService.updateDependent(dependentRequest.toBean());
    }
}
