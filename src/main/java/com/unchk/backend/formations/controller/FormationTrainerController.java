package com.unchk.backend.formations.controller;

import com.unchk.backend.formations.dto.AssignTrainerRequestDTO;
import com.unchk.backend.formations.dto.TrainerResponseDTO;
import com.unchk.backend.formations.service.FormationTrainerService;

import lombok.RequiredArgsConstructor;

import org.springframework.security.access.prepost.PreAuthorize;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/formation-trainers")
@RequiredArgsConstructor
public class FormationTrainerController {

    private final FormationTrainerService
            formationTrainerService;

    /**
     * Affectation
     */
    @PreAuthorize(
            "hasAnyRole('ADMIN','RESPONSABLE_FORMATION')"
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
     * Liste
     */
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
     * Retrait
     */
    @PreAuthorize(
            "hasAnyRole('ADMIN','RESPONSABLE_FORMATION')"
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