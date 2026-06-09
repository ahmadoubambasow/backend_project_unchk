package com.unchk.backend.insertion.controller;

import com.unchk.backend.insertion.dto.InternshipRequestDTO;
import com.unchk.backend.insertion.dto.InternshipResponseDTO;
import com.unchk.backend.insertion.service.InternshipService;

import lombok.RequiredArgsConstructor;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller REST de gestion des stages.
 */
@RestController
@RequestMapping("/api/internships")
@RequiredArgsConstructor
public class InternshipController {

    private final InternshipService service;

    /**
     * Création d'un stage.
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
    public InternshipResponseDTO create(

            @RequestBody
            InternshipRequestDTO request

    ) {

        return service.create(
                request
        );
    }

    /**
     * Liste des stages.
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
    public List<InternshipResponseDTO> getAll() {

        return service.getAll();
    }

    /**
     * Consultation du détail d'un stage.
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
    public InternshipResponseDTO getById(

            @PathVariable
            Long id

    ) {

        return service.getById(
                id
        );
    }

    /**
     * Modification d'un stage.
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
    public InternshipResponseDTO update(

            @PathVariable
            Long id,

            @RequestBody
            InternshipRequestDTO request

    ) {

        return service.update(
                id,
                request
        );
    }

    /**
     * Suppression d'un stage.
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