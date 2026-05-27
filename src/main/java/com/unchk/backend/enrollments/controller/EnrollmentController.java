package com.unchk.backend.enrollments.controller;

import com.unchk.backend.enrollments.dto.EnrollmentRequestDTO;
import com.unchk.backend.enrollments.dto.EnrollmentResponseDTO;
import com.unchk.backend.enrollments.service.EnrollmentService;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/enrollments")
@RequiredArgsConstructor
public class EnrollmentController {

    private final EnrollmentService enrollmentService;

    /**
     * Création inscription
     */
    @PreAuthorize(
            "hasAnyRole('ADMIN', 'SUPER_ADMIN')"
    )
    @PostMapping
    public EnrollmentResponseDTO createEnrollment(

            @Valid
            @RequestBody
            EnrollmentRequestDTO request
    ) {

        return enrollmentService.createEnrollment(request);
    }

    /**
     * Liste inscription
     */
    @GetMapping
    public List<EnrollmentResponseDTO> getAllEnrollments() {

        return enrollmentService.getAllEnrollments();
    }

    /**
     * Mise à jour inscription.
     */
    @PreAuthorize(
            "hasAnyRole('ADMIN', 'SUPER_ADMIN')"
    )
    @PutMapping("/{id}")
    public EnrollmentResponseDTO updateEnrollment(

            @PathVariable
            Long id,

            @Valid
            @RequestBody
            EnrollmentRequestDTO request
    ) {

        return enrollmentService.updateEnrollment(
                id,
                request
        );
    }

    /**
     * Suppression inscription.
     */
    @PreAuthorize(
            "hasAnyRole('ADMIN', 'SUPER_ADMIN')"
    )
    @DeleteMapping("/{id}")
    public void deleteEnrollment(

            @PathVariable
            Long id
    ) {

        enrollmentService.deleteEnrollment(id);
    }
}
