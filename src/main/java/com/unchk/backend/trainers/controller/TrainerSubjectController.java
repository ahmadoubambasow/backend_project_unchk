package com.unchk.backend.trainers.controller;

import com.unchk.backend.trainers.dto.TrainerSubjectRequestDTO;
import com.unchk.backend.trainers.dto.TrainerSubjectResponseDTO;
import com.unchk.backend.trainers.service.TrainerSubjectService;

import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/trainer-subjects")
@RequiredArgsConstructor
public class TrainerSubjectController {

    private final TrainerSubjectService trainerSubjectService;

    /**
     * Création affectation
     */
    @PreAuthorize(
            "hasAnyRole('ADMIN','SUPER_ADMIN')"
    )
    @PostMapping
    public TrainerSubjectResponseDTO createAssignment(

            @Valid
            @RequestBody
            TrainerSubjectRequestDTO request
    ) {

        return trainerSubjectService
                .createAssignment(
                        request
                );
    }

    /**
     * Liste affectations
     */
    @GetMapping
    public List<TrainerSubjectResponseDTO>
    getAllAssignments() {

        return trainerSubjectService
                .getAllAssignments();
    }

    /**
     * Par enseignant
     */
    @GetMapping("/trainer/{trainerId}")
    public List<TrainerSubjectResponseDTO>
    getAssignmentsByTrainer(

            @PathVariable
            Long trainerId
    ) {

        return trainerSubjectService
                .getAssignmentsByTrainer(
                        trainerId
                );
    }

    /**
     * Par matière
     */
    @GetMapping("/subject/{subjectId}")
    public List<TrainerSubjectResponseDTO>
    getAssignmentsBySubject(

            @PathVariable
            Long subjectId
    ) {

        return trainerSubjectService
                .getAssignmentsBySubject(
                        subjectId
                );
    }

    /**
     * Suppression
     */
    @PreAuthorize(
            "hasAnyRole('ADMIN','SUPER_ADMIN')"
    )
    @DeleteMapping("/{id}")
    public void deleteAssignment(

            @PathVariable
            Long id
    ) {

        trainerSubjectService.deleteAssignment(
                id
        );
    }
}