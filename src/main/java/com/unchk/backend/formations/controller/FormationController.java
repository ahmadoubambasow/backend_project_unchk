package com.unchk.backend.formations.controller;

import com.unchk.backend.formations.dto.FormationRequestDTO;
import com.unchk.backend.formations.dto.FormationResponseDTO;
import com.unchk.backend.formations.service.FormationService;

import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller REST de gestion des formations
 */
@RestController
@RequestMapping("/api/formations")
@RequiredArgsConstructor
public class FormationController {

    private final FormationService formationService;

    /**
     * Création d'une formation
     *
     * Autorisés :
     * - ADMIN
     * - RESPONSABLE_FORMATION
     */
    @PreAuthorize(
            "hasAnyRole('ADMIN','RESPONSABLE_FORMATION')"
    )
    @PostMapping
    public FormationResponseDTO createFormation(

            @Valid
            @RequestBody
            FormationRequestDTO request

    ) {

        return formationService.createFormation(
                request
        );
    }

    /**
     * Liste des formations
     *
     * Autorisés :
     * - Tous les utilisateurs authentifiés
     */
    @PreAuthorize("isAuthenticated()")
    @GetMapping
    public List<FormationResponseDTO> getAllFormations() {

        return formationService.getAllFormations();
    }

    /**
     * Détail d'une formation
     *
     * Autorisés :
     * - Tous les utilisateurs authentifiés
     */
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/{id}")
    public FormationResponseDTO getFormationById(

            @PathVariable
            Long id

    ) {

        return formationService.getFormationById(
                id
        );
    }

    /**
     * Modification d'une formation
     *
     * Autorisés :
     * - ADMIN
     * - RESPONSABLE_FORMATION
     */
    @PreAuthorize(
            "hasAnyRole('ADMIN','RESPONSABLE_FORMATION')"
    )
    @PutMapping("/{id}")
    public FormationResponseDTO updateFormation(

            @PathVariable
            Long id,

            @Valid
            @RequestBody
            FormationRequestDTO request

    ) {

        return formationService.updateFormation(
                id,
                request
        );
    }

    /**
     * Suppression d'une formation
     *
     * Autorisés :
     * - ADMIN uniquement
     */
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public void deleteFormation(

            @PathVariable
            Long id

    ) {

        formationService.deleteFormation(
                id
        );
    }
}