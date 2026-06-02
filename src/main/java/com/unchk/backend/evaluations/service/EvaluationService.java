package com.unchk.backend.evaluations.service;

import com.unchk.backend.evaluations.dto.EvaluationRequestDTO;
import com.unchk.backend.evaluations.dto.EvaluationResponseDTO;
import com.unchk.backend.evaluations.entity.Evaluation;
import com.unchk.backend.evaluations.repository.EvaluationRepository;
import com.unchk.backend.subjects.entity.Subject;
import com.unchk.backend.subjects.repository.SubjectRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EvaluationService {

    private final EvaluationRepository evaluationRepository;

    private final SubjectRepository subjectRepository;

    /**
     * Création
     */
    public EvaluationResponseDTO createEvaluation(
            EvaluationRequestDTO request
    ) {

        Subject subject =

                subjectRepository.findById(
                                request.getSubjectId()
                        )

                        .orElseThrow(() ->

                                new RuntimeException(
                                        "Matière introuvable"
                                )
                        );

        Evaluation evaluation =

                Evaluation.builder()

                        .title(
                                request.getTitle()
                        )

                        .evaluationDate(
                                LocalDate.parse(
                                        request.getEvaluationDate()
                                )
                        )

                        .maxScore(
                                request.getMaxScore()
                        )

                        .coefficient(
                                request.getCoefficient()
                        )

                        .subject(
                                subject
                        )

                        .build();

        Evaluation savedEvaluation =

                evaluationRepository.save(
                        evaluation
                );

        return mapToResponse(
                savedEvaluation
        );
    }

    /**
     * Liste
     */
    public List<EvaluationResponseDTO>
    getAllEvaluations() {

        return evaluationRepository.findAll()

                .stream()

                .map(this::mapToResponse)

                .toList();
    }

    /**
     * Par matière
     */
    public List<EvaluationResponseDTO>
    getEvaluationsBySubject(
            Long subjectId
    ) {

        return evaluationRepository

                .findBySubjectId(
                        subjectId
                )

                .stream()

                .map(this::mapToResponse)

                .toList();
    }

    /**
     * Modification
     */
    public EvaluationResponseDTO updateEvaluation(

            Long id,

            EvaluationRequestDTO request
    ) {

        Evaluation evaluation =

                evaluationRepository.findById(id)

                        .orElseThrow(() ->

                                new RuntimeException(
                                        "Evaluation introuvable"
                                )
                        );

        Subject subject =

                subjectRepository.findById(
                                request.getSubjectId()
                        )

                        .orElseThrow(() ->

                                new RuntimeException(
                                        "Matière introuvable"
                                )
                        );

        evaluation.setTitle(
                request.getTitle()
        );

        evaluation.setEvaluationDate(
                LocalDate.parse(
                        request.getEvaluationDate()
                )
        );

        evaluation.setMaxScore(
                request.getMaxScore()
        );

        evaluation.setCoefficient(
                request.getCoefficient()
        );

        evaluation.setSubject(
                subject
        );

        Evaluation updatedEvaluation =

                evaluationRepository.save(
                        evaluation
                );

        return mapToResponse(
                updatedEvaluation
        );
    }

    /**
     * Suppression
     */
    public void deleteEvaluation(
            Long id
    ) {

        Evaluation evaluation =

                evaluationRepository.findById(id)

                        .orElseThrow(() ->

                                new RuntimeException(
                                        "Evaluation introuvable"
                                )
                        );

        evaluationRepository.delete(
                evaluation
        );
    }

    /**
     * Mapping
     */
    private EvaluationResponseDTO mapToResponse(
            Evaluation evaluation
    ) {

        return EvaluationResponseDTO

                .builder()

                .id(
                        evaluation.getId()
                )

                .title(
                        evaluation.getTitle()
                )

                .evaluationDate(
                        evaluation
                                .getEvaluationDate()
                                .toString()
                )

                .maxScore(
                        evaluation.getMaxScore()
                )

                .coefficient(
                        evaluation.getCoefficient()
                )

                .subjectId(
                        (long) evaluation.getSubject()
                                .getId()
                )

                .subjectName(
                        evaluation.getSubject()
                                .getName()
                )

                .formationId(
                        evaluation.getSubject()
                                .getFormation()
                                .getId()
                )

                .formationName(
                        evaluation.getSubject()
                                .getFormation()
                                .getName()
                )

                .filiereId(
                        evaluation.getSubject()
                                .getFormation()
                                .getFiliere()
                                .getId()
                )

                .filiereName(
                        evaluation.getSubject()
                                .getFormation()
                                .getFiliere()
                                .getName()
                )

                .build();
    }
}