package com.unchk.backend.formations.controller;

import com.unchk.backend.formations.dto.TrainingModuleRequestDTO;
import com.unchk.backend.formations.dto.TrainingModuleResponseDTO;
import com.unchk.backend.formations.service.TrainingModuleService;

import lombok.RequiredArgsConstructor;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller REST de gestion des modules de formation.
 */
@RestController
@RequestMapping("/api/training-modules")
@RequiredArgsConstructor
public class TrainingModuleController {

    private final TrainingModuleService service;

    /**
     * Liste de tous les modules.
     *
     * Autorisés :
     * - Tous les utilisateurs authentifiés
     */
    @PreAuthorize("isAuthenticated()")
    @GetMapping
    public List<TrainingModuleResponseDTO>
    getAllModules() {

        return service.getAllModules();
    }

    /**
     * Création d'un module.
     *
     * Autorisés :
     * - ADMIN
     * - RESPONSABLE_FORMATION
     */
    @PreAuthorize(
            "hasAnyRole(" +
                    "'ADMIN'," +
                    "'RESPONSABLE_FORMATION'" +
                    ")"
    )
    @PostMapping
    public TrainingModuleResponseDTO
    createModule(

            @RequestBody
            TrainingModuleRequestDTO request

    ) {

        return service.createModule(
                request
        );
    }

    /**
     * Liste des modules d'une formation.
     *
     * Autorisés :
     * - Tous les utilisateurs authentifiés
     *
     * Remarque :
     * Cette information est utile pour :
     * - les étudiants (consulter leur programme),
     * - les enseignants (connaître les enseignements),
     * - les responsables pédagogiques.
     */
    @PreAuthorize("isAuthenticated()")
    @GetMapping(
            "/formation/{formationId}"
    )
    public List<TrainingModuleResponseDTO>
    getFormationModules(

            @PathVariable
            Long formationId

    ) {

        return service
                .getFormationModules(
                        formationId
                );
    }

    /**
     * Modification d'un module.
     *
     * Autorisés :
     * - ADMIN
     * - RESPONSABLE_FORMATION
     */
    @PreAuthorize(
            "hasAnyRole(" +
                    "'ADMIN'," +
                    "'RESPONSABLE_FORMATION'" +
                    ")"
    )
    @PutMapping("/{id}")
    public TrainingModuleResponseDTO
    updateModule(

            @PathVariable
            Long id,

            @RequestBody
            TrainingModuleRequestDTO request

    ) {

        return service.updateModule(
                id,
                request
        );
    }

    /**
     * Consultation du détail d'un module.
     *
     * Autorisés :
     * - Tous les utilisateurs authentifiés
     */
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/{id}")
    public TrainingModuleResponseDTO
    getModuleById(

            @PathVariable
            Long id

    ) {

        return service.getModuleById(
                id
        );
    }

    /**
     * Suppression d'un module.
     *
     * Autorisés :
     * - ADMIN uniquement
     */
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public void deleteModule(

            @PathVariable
            Long id

    ) {

        service.deleteModule(
                id
        );
    }
}