package com.unchk.backend.formations.service;

import com.unchk.backend.formations.dto.TrainingModuleRequestDTO;
import com.unchk.backend.formations.dto.TrainingModuleResponseDTO;
import com.unchk.backend.formations.entity.Formation;
import com.unchk.backend.formations.entity.TrainingModule;
import com.unchk.backend.formations.repository.FormationRepository;
import com.unchk.backend.formations.repository.TrainingModuleRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TrainingModuleService {

    private final TrainingModuleRepository
            trainingModuleRepository;

    private final FormationRepository
            formationRepository;

    public List<TrainingModuleResponseDTO>
    getAllModules() {

        return trainingModuleRepository

                .findAll()

                .stream()

                .map(this::mapToResponse)

                .toList();
    }

    /**
     * Création module
     */
    public TrainingModuleResponseDTO
    createModule(

            TrainingModuleRequestDTO request

    ) {

        Formation formation =

                formationRepository

                        .findById(
                                request.getFormationId()
                        )

                        .orElseThrow(() ->

                                new RuntimeException(
                                        "Formation introuvable"
                                )
                        );

        TrainingModule module =

                TrainingModule.builder()

                        .title(
                                request.getTitle()
                        )

                        .description(
                                request.getDescription()
                        )

                        .hours(
                                request.getHours()
                        )

                        .formation(
                                formation
                        )

                        .build();

        module = trainingModuleRepository.save(
                module
        );

        return mapToResponse(
                module
        );
    }

    /**
     * Liste modules d'une formation
     */
    public List<TrainingModuleResponseDTO>
    getFormationModules(

            Long formationId

    ) {

        return trainingModuleRepository

                .findByFormationId(
                        formationId
                )

                .stream()

                .map(
                        this::mapToResponse
                )

                .toList();
    }

    /**
     * Détail module
     */
    public TrainingModuleResponseDTO
    getModuleById(

            Long id

    ) {

        TrainingModule module =

                trainingModuleRepository

                        .findById(id)

                        .orElseThrow(() ->

                                new RuntimeException(
                                        "Module introuvable"
                                )
                        );

        return mapToResponse(
                module
        );
    }

    /**
     * Modification module
     */
    public TrainingModuleResponseDTO
    updateModule(

            Long id,

            TrainingModuleRequestDTO request

    ) {

        TrainingModule module =

                trainingModuleRepository

                        .findById(id)

                        .orElseThrow(() ->

                                new RuntimeException(
                                        "Module introuvable"
                                )
                        );

        Formation formation =

                formationRepository

                        .findById(
                                request.getFormationId()
                        )

                        .orElseThrow(() ->

                                new RuntimeException(
                                        "Formation introuvable"
                                )
                        );

        module.setTitle(
                request.getTitle()
        );

        module.setDescription(
                request.getDescription()
        );

        module.setHours(
                request.getHours()
        );

        module.setFormation(
                formation
        );

        module = trainingModuleRepository.save(
                module
        );

        return mapToResponse(
                module
        );
    }

    /**
     * Suppression module
     */
    public void deleteModule(

            Long id

    ) {

        TrainingModule module =

                trainingModuleRepository

                        .findById(id)

                        .orElseThrow(() ->

                                new RuntimeException(
                                        "Module introuvable"
                                )
                        );

        trainingModuleRepository.delete(
                module
        );
    }

    /**
     * Mapping DTO
     */
    private TrainingModuleResponseDTO
    mapToResponse(

            TrainingModule module

    ) {

        return TrainingModuleResponseDTO

                .builder()

                .id(
                        module.getId()
                )

                .title(
                        module.getTitle()
                )

                .description(
                        module.getDescription()
                )

                .hours(
                        module.getHours()
                )

                .formationId(
                        module.getFormation().getId()
                )

                .formationName(
                        module.getFormation().getName()
                )

                .build();
    }
}