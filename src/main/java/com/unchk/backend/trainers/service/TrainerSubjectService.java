package com.unchk.backend.trainers.service;

import com.unchk.backend.subjects.entity.Subject;
import com.unchk.backend.subjects.repository.SubjectRepository;
import com.unchk.backend.trainers.dto.TrainerSubjectRequestDTO;
import com.unchk.backend.trainers.dto.TrainerSubjectResponseDTO;
import com.unchk.backend.trainers.entity.Trainer;
import com.unchk.backend.trainers.entity.TrainerSubject;
import com.unchk.backend.trainers.repository.TrainerRepository;
import com.unchk.backend.trainers.repository.TrainerSubjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TrainerSubjectService {

    private final TrainerRepository trainerRepository;

    private final SubjectRepository subjectRepository;

    private final TrainerSubjectRepository trainerSubjectRepository;

    /**
     * Affectation enseignat -> matière
     */
    public TrainerSubjectResponseDTO createAssignment(
            TrainerSubjectRequestDTO request
    ) {
        if (trainerSubjectRepository.existsByTrainerIdAndSubjectId(
                request.getTrainerId(), request.getSubjectId()
        )) {
            throw new RuntimeException("Cette affectation existe déjà");
        }

        Trainer trainer = trainerRepository.findById(request.getTrainerId())
                .orElseThrow(() -> new RuntimeException("Enseignant introuvable"));

        Subject subject = subjectRepository.findById(request.getSubjectId())
                .orElseThrow(() -> new RuntimeException("Matière introuvable"));

        TrainerSubject assignment = TrainerSubject.builder()

                .trainer(trainer)

                .subject(subject)

                .build();

        TrainerSubject savedAssignment = trainerSubjectRepository.save(assignment);

        return mapToResponse(savedAssignment);
    }

    /**
     * Liste affectations
     */
    public List<TrainerSubjectResponseDTO> getAllAssignments() {

        return trainerSubjectRepository.findAll()

                .stream()

                .map(this::mapToResponse)

                .toList();
    }

    /**
     * Affectations d'un enseignant
     */
    public List<TrainerSubjectResponseDTO> getAssignmentsByTrainer(Long trainerId) {

        return trainerSubjectRepository.findByTrainerId(
                trainerId
        )
                .stream()

                .map(this::mapToResponse)

                .toList();
    }

    /**
     * Affectations d'une matière
     */
    public List<TrainerSubjectResponseDTO> getAssignmentsBySubject(Long subjectId) {

        return trainerSubjectRepository.findBySubjectId(subjectId)

                .stream()

                .map(this::mapToResponse)

                .toList();
    }

    /**
     * Suppression affectation
     */
    public void deleteAssignment(Long id) {

        TrainerSubject assignment = trainerSubjectRepository.findById(id)

                .orElseThrow(() -> new RuntimeException("Affectation introuvable"));

        trainerSubjectRepository.delete(assignment);
    }

    /**
     * Mapping
     */
    private TrainerSubjectResponseDTO mapToResponse(TrainerSubject assignment) {

        return TrainerSubjectResponseDTO.builder()

                .id(
                        assignment.getId()
                )

                .trainerId(
                        assignment.getTrainer().getId()
                )

                .trainerName(
                        assignment.getTrainer().getFirstName()
                        + " "
                        + assignment.getTrainer()
                                .getLastName()
                )

                .subjectId(
                        (long) assignment.getSubject().getId()
                )

                .subjectName(
                        assignment.getSubject().getName()
                )

                .formationId(
                        assignment.getSubject().getFormation().getId()
                )

                .formationName(
                        assignment.getSubject().getFormation().getName()
                )

                .filiereId(
                        assignment.getSubject().getFormation().getFiliere().getId()
                )

                .filiereName(
                        assignment.getSubject()
                                .getFormation()
                                .getFiliere()
                                .getName()
                )

                .build();
    }

}
