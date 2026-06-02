package com.unchk.backend.evaluations.controller;

import com.unchk.backend.evaluations.dto.EvaluationRequestDTO;
import com.unchk.backend.evaluations.dto.EvaluationResponseDTO;
import com.unchk.backend.evaluations.service.EvaluationService;

import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/evaluations")
@RequiredArgsConstructor
public class EvaluationController {

    private final EvaluationService evaluationService;

    /**
     * Création
     */
    @PreAuthorize(
            "hasAnyRole('ADMIN','SUPER_ADMIN','TEACHER')"
    )
    @PostMapping
    public EvaluationResponseDTO createEvaluation(

            @Valid
            @RequestBody
            EvaluationRequestDTO request
    ) {

        return evaluationService
                .createEvaluation(
                        request
                );
    }

    /**
     * Liste
     */
    @GetMapping
    public List<EvaluationResponseDTO>
    getAllEvaluations() {

        return evaluationService
                .getAllEvaluations();
    }

    /**
     * Par matière
     */
    @GetMapping("/subject/{subjectId}")
    public List<EvaluationResponseDTO>
    getEvaluationsBySubject(

            @PathVariable
            Long subjectId
    ) {

        return evaluationService
                .getEvaluationsBySubject(
                        subjectId
                );
    }

    /**
     * Modification
     */
    @PreAuthorize(
            "hasAnyRole('ADMIN','SUPER_ADMIN','TEACHER')"
    )
    @PutMapping("/{id}")
    public EvaluationResponseDTO updateEvaluation(

            @PathVariable
            Long id,

            @Valid
            @RequestBody
            EvaluationRequestDTO request
    ) {

        return evaluationService
                .updateEvaluation(
                        id,
                        request
                );
    }

    /**
     * Suppression
     */
    @PreAuthorize(
            "hasAnyRole('ADMIN','SUPER_ADMIN')"
    )
    @DeleteMapping("/{id}")
    public void deleteEvaluation(

            @PathVariable
            Long id
    ) {

        evaluationService.deleteEvaluation(
                id
        );
    }
}