package com.unchk.backend.insertion.controller;

import com.unchk.backend.insertion.dto.GraduateInsertionRequestDTO;
import com.unchk.backend.insertion.dto.GraduateInsertionResponseDTO;
import com.unchk.backend.insertion.service.GraduateInsertionService;

import lombok.RequiredArgsConstructor;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller REST de gestion des insertions professionnelles
 * des diplômés.
 */
@RestController
@RequestMapping("/api/graduate-insertions")
@RequiredArgsConstructor
public class GraduateInsertionController {

    private final GraduateInsertionService service;

    /**
     * Création d'une insertion professionnelle.
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
    public GraduateInsertionResponseDTO create(

            @RequestBody
            GraduateInsertionRequestDTO request

    ) {

        return service.create(
                request
        );
    }

    /**
     * Liste des insertions professionnelles.
     *
     * Autorisés :
     * - ADMIN
     * - DIRECTION
     * - INSERTION
     * - APPUI_INSERTION
     */
    @PreAuthorize(
            "hasAnyRole(" +
                    "'ADMIN'," +
                    "'DIRECTION'," +
                    "'INSERTION'," +
                    "'APPUI_INSERTION'" +
                    ")"
    )
    @GetMapping
    public List<GraduateInsertionResponseDTO> getAll() {

        return service.getAll();
    }

    /**
     * Consultation du détail d'une insertion professionnelle.
     *
     * Autorisés :
     * - ADMIN
     * - DIRECTION
     * - INSERTION
     * - APPUI_INSERTION
     */
    @PreAuthorize(
            "hasAnyRole(" +
                    "'ADMIN'," +
                    "'DIRECTION'," +
                    "'INSERTION'," +
                    "'APPUI_INSERTION'" +
                    ")"
    )
    @GetMapping("/{id}")
    public GraduateInsertionResponseDTO getById(

            @PathVariable
            Long id

    ) {

        return service.getById(
                id
        );
    }

    /**
     * Modification d'une insertion professionnelle.
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
    public GraduateInsertionResponseDTO update(

            @PathVariable
            Long id,

            @RequestBody
            GraduateInsertionRequestDTO request

    ) {

        return service.update(
                id,
                request
        );
    }

    /**
     * Suppression d'une insertion professionnelle.
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