package com.unchk.backend.insertion.controller;

import com.unchk.backend.insertion.dto.StudentContactRequestDTO;
import com.unchk.backend.insertion.dto.StudentContactResponseDTO;
import com.unchk.backend.insertion.service.StudentContactService;

import lombok.RequiredArgsConstructor;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller REST de gestion des contacts étudiants.
 */
@RestController
@RequestMapping("/api/student-contacts")
@RequiredArgsConstructor
public class StudentContactController {

    private final StudentContactService service;

    /**
     * Création d'un contact étudiant.
     *
     * Autorisés :
     * - ADMIN
     * - INSERTION
     * - APPUI_INSERTION
     */
    @PreAuthorize(
            "hasAnyRole(" +
                    "'ADMIN'," +
                    "'INSERTION'," +
                    "'APPUI_INSERTION'" +
                    ")"
    )
    @PostMapping
    public StudentContactResponseDTO create(

            @RequestBody
            StudentContactRequestDTO request

    ) {

        return service.create(
                request
        );
    }

    /**
     * Liste complète des contacts étudiants.
     *
     * Autorisés :
     * - ADMIN
     * - DIRECTION
     * - INSERTION
     * - APPUI_INSERTION
     * - RESPONSABLE_FORMATION
     */
    @PreAuthorize(
            "hasAnyRole(" +
                    "'ADMIN'," +
                    "'DIRECTION'," +
                    "'INSERTION'," +
                    "'APPUI_INSERTION'," +
                    "'RESPONSABLE_FORMATION'" +
                    ")"
    )
    @GetMapping
    public List<StudentContactResponseDTO> getAll() {

        return service.getAll();
    }

    /**
     * Consultation du détail d'un contact étudiant.
     *
     * Autorisés :
     * - ADMIN
     * - DIRECTION
     * - INSERTION
     * - APPUI_INSERTION
     * - RESPONSABLE_FORMATION
     */
    @PreAuthorize(
            "hasAnyRole(" +
                    "'ADMIN'," +
                    "'DIRECTION'," +
                    "'INSERTION'," +
                    "'APPUI_INSERTION'," +
                    "'RESPONSABLE_FORMATION'" +
                    ")"
    )
    @GetMapping("/{id}")
    public StudentContactResponseDTO getById(

            @PathVariable
            Long id

    ) {

        return service.getById(
                id
        );
    }

    /**
     * Consultation des contacts d'un étudiant.
     *
     * Autorisés :
     * - ADMIN
     * - DIRECTION
     * - INSERTION
     * - APPUI_INSERTION
     * - RESPONSABLE_FORMATION
     */
    @PreAuthorize(
            "hasAnyRole(" +
                    "'ADMIN'," +
                    "'DIRECTION'," +
                    "'INSERTION'," +
                    "'APPUI_INSERTION'," +
                    "'RESPONSABLE_FORMATION'" +
                    ")"
    )
    @GetMapping("/student/{studentId}")
    public List<StudentContactResponseDTO>
    getStudentContacts(

            @PathVariable
            Long studentId

    ) {

        return service.getStudentContacts(
                studentId
        );
    }

    /**
     * Modification d'un contact étudiant.
     *
     * Autorisés :
     * - ADMIN
     * - INSERTION
     * - APPUI_INSERTION
     */
    @PreAuthorize(
            "hasAnyRole(" +
                    "'ADMIN'," +
                    "'INSERTION'," +
                    "'APPUI_INSERTION'" +
                    ")"
    )
    @PutMapping("/{id}")
    public StudentContactResponseDTO update(

            @PathVariable
            Long id,

            @RequestBody
            StudentContactRequestDTO request

    ) {

        return service.update(
                id,
                request
        );
    }

    /**
     * Suppression d'un contact étudiant.
     *
     * Autorisés :
     * - ADMIN uniquement
     */
    @PreAuthorize(
            "hasRole('ADMIN')"
    )
    @DeleteMapping("/{id}")
    public void delete(

            @PathVariable
            Long id

    ) {

        service.delete(
                id
        );
    }
}