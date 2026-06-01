package com.unchk.backend.subjects.controller;

import com.unchk.backend.subjects.dto.SubjectRequestDTO;
import com.unchk.backend.subjects.dto.SubjectResponseDTO;
import com.unchk.backend.subjects.service.SubjectService;

import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/subjects")
@RequiredArgsConstructor
public class SubjectController {

    private final SubjectService subjectService;

    /**
     * Création matière
     */
    @PreAuthorize(
            "hasAnyRole('ADMIN','SUPER_ADMIN')"
    )
    @PostMapping
    public SubjectResponseDTO createSubject(

            @Valid
            @RequestBody
            SubjectRequestDTO request
    ) {

        return subjectService.createSubject(
                request
        );
    }

    /**
     * Liste matières
     */
    @GetMapping
    public List<SubjectResponseDTO>
    getAllSubjects() {

        return subjectService.getAllSubjects();
    }

    /**
     * Matières d'une formation
     */
    @GetMapping("/formation/{formationId}")
    public List<SubjectResponseDTO>
    getSubjectsByFormation(

            @PathVariable
            Long formationId
    ) {

        return subjectService
                .getSubjectsByFormation(
                        formationId
                );
    }

    /**
     * Modification matière
     */
    @PreAuthorize(
            "hasAnyRole('ADMIN','SUPER_ADMIN')"
    )
    @PutMapping("/{id}")
    public SubjectResponseDTO updateSubject(

            @PathVariable
            Long id,

            @Valid
            @RequestBody
            SubjectRequestDTO request
    ) {

        return subjectService.updateSubject(
                id,
                request
        );
    }

    /**
     * Suppression matière
     */
    @PreAuthorize(
            "hasAnyRole('ADMIN','SUPER_ADMIN')"
    )
    @DeleteMapping("/{id}")
    public void deleteSubject(

            @PathVariable
            Long id
    ) {

        subjectService.deleteSubject(
                id
        );
    }
}