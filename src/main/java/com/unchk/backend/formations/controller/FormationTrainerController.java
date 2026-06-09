package com.unchk.backend.formations.controller;

import com.unchk.backend.formations.dto.AssignTrainerRequestDTO;
import com.unchk.backend.formations.dto.TrainerResponseDTO;
import com.unchk.backend.formations.service.FormationTrainerService;

import lombok.RequiredArgsConstructor;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller REST de gestion des affectations
 * des formateurs aux formations.
 */
@RestController
@RequestMapping("/api/formation-trainers")
@RequiredArgsConstructor
public class FormationTrainerController {

    private final FormationTrainerService
            formationTrainerService;

    /**
     * Affectation d'un formateur à une formation.
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
    public void assignTrainer(

            @RequestBody
            AssignTrainerRequestDTO request

    ) {

        formationTrainerService.assignTrainer(
                request
        );
    }

    /**
     * Liste des formateurs affectés à une formation.
     *
     * Autorisés :
     * - Tous les utilisateurs authentifiés
     *
     * Remarque :
     * Cette information peut être utile pour :
     * - les étudiants (identifier leurs enseignants),
     * - les enseignants (voir leurs affectations),
     * - les responsables de formation,
     * - l'administration.
     */
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/{formationId}")
    public List<TrainerResponseDTO>
    getFormationTrainers(

            @PathVariable
            Long formationId

    ) {

        return formationTrainerService
                .getFormationTrainers(
                        formationId
                );
    }

    /**
     * Retrait d'un formateur d'une formation.
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
    @DeleteMapping(
            "/{formationId}/{trainerId}"
    )
    public void removeTrainer(

            @PathVariable
            Long formationId,

            @PathVariable
            Long trainerId

    ) {

        formationTrainerService.removeTrainer(

                formationId,

                trainerId
        );
    }
}