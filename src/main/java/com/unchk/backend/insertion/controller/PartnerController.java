package com.unchk.backend.insertion.controller;

import com.unchk.backend.insertion.dto.PartnerRequestDTO;
import com.unchk.backend.insertion.dto.PartnerResponseDTO;
import com.unchk.backend.insertion.service.PartnerService;

import lombok.RequiredArgsConstructor;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller REST de gestion des partenaires.
 */
@RestController
@RequestMapping("/api/partners")
@RequiredArgsConstructor
public class PartnerController {

    private final PartnerService service;

    /**
     * Création d'un partenaire.
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
    public PartnerResponseDTO create(

            @RequestBody
            PartnerRequestDTO request

    ) {

        return service.create(
                request
        );
    }

    /**
     * Liste des partenaires.
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
    public List<PartnerResponseDTO> getAll() {

        return service.getAll();
    }

    /**
     * Consultation du détail d'un partenaire.
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
    public PartnerResponseDTO getById(

            @PathVariable
            Long id

    ) {

        return service.getById(
                id
        );
    }

    /**
     * Modification d'un partenaire.
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
    public PartnerResponseDTO update(

            @PathVariable
            Long id,

            @RequestBody
            PartnerRequestDTO request

    ) {

        return service.update(
                id,
                request
        );
    }

    /**
     * Suppression d'un partenaire.
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