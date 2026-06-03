package com.unchk.backend.formations.service;

import com.unchk.backend.formations.dto.FormationRequestDTO;
import com.unchk.backend.formations.dto.FormationResponseDTO;
import com.unchk.backend.formations.dto.TrainerResponseDTO;
import com.unchk.backend.formations.entity.Formation;
import com.unchk.backend.formations.repository.FormationRepository;

import com.unchk.backend.formations.repository.FormationTrainerRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FormationService {

    private final FormationRepository
            formationRepository;

    private final FormationTrainerRepository  formationTrainerRepository;

    /**
     * Création
     */
    public FormationResponseDTO createFormation(

            FormationRequestDTO request

    ) {

        Formation formation =

                Formation.builder()

                        .name(request.getName())

                        .startDate(request.getStartDate())

                        .endDate(request.getEndDate())

                        .formationType(
                                request.getFormationType()
                        )

                        .level(
                                request.getLevel()
                        )

                        .fundingAmount(
                                request.getFundingAmount()
                        )

                        .fundingType(
                                request.getFundingType()
                        )

                        .maleCount(
                                request.getMaleCount()
                        )

                        .femaleCount(
                                request.getFemaleCount()
                        )

                        .description(
                                request.getDescription()
                        )

                        .build();

        return mapToResponse(

                formationRepository.save(
                        formation
                )
        );
    }

    /**
     * Liste
     */
    public List<FormationResponseDTO>
    getAllFormations() {

        return formationRepository

                .findAll()

                .stream()

                .map(this::mapToResponse)

                .toList();
    }

    /**
     * Détail
     */
    public FormationResponseDTO
    getFormationById(
            Long id
    ) {

        Formation formation =

                formationRepository.findById(id)

                        .orElseThrow(() ->

                                new RuntimeException(
                                        "Formation introuvable"
                                )
                        );

        return mapToResponse(
                formation
        );
    }

    /**
     * Modification
     */
    public FormationResponseDTO
    updateFormation(

            Long id,

            FormationRequestDTO request

    ) {

        Formation formation =

                formationRepository.findById(id)

                        .orElseThrow(() ->

                                new RuntimeException(
                                        "Formation introuvable"
                                )
                        );

        formation.setName(
                request.getName()
        );

        formation.setStartDate(
                request.getStartDate()
        );

        formation.setEndDate(
                request.getEndDate()
        );

        formation.setFormationType(
                request.getFormationType()
        );

        formation.setLevel(
                request.getLevel()
        );

        formation.setFundingAmount(
                request.getFundingAmount()
        );

        formation.setFundingType(
                request.getFundingType()
        );

        formation.setMaleCount(
                request.getMaleCount()
        );

        formation.setFemaleCount(
                request.getFemaleCount()
        );

        formation.setDescription(
                request.getDescription()
        );

        return mapToResponse(

                formationRepository.save(
                        formation
                )
        );
    }

    /**
     * Suppression
     */
    public void deleteFormation(
            Long id
    ) {

        Formation formation =

                formationRepository.findById(id)

                        .orElseThrow(() ->

                                new RuntimeException(
                                        "Formation introuvable"
                                )
                        );

        formationRepository.delete(
                formation
        );
    }



    /**
     * Mapping
     */
    private FormationResponseDTO
    mapToResponse(
            Formation formation
    ) {

        List<TrainerResponseDTO> trainers =

                formationTrainerRepository

                        .findByFormationId(
                                formation.getId()
                        )

                        .stream()

                        .map(link ->

                                TrainerResponseDTO

                                        .builder()

                                        .id(
                                                link.getTrainer().getId()
                                        )

                                        .fullName(
                                                link.getTrainer().getFullName()
                                        )

                                        .email(
                                                link.getTrainer().getEmail()
                                        )

                                        .role(
                                                link.getTrainer()
                                                        .getRole()
                                                        .getName()
                                        )

                                        .build()
                        )

                        .toList();

        return FormationResponseDTO

                .builder()

                .id(
                        formation.getId()
                )

                .name(
                        formation.getName()
                )

                .startDate(
                        formation.getStartDate()
                )

                .endDate(
                        formation.getEndDate()
                )

                .formationType(
                        formation.getFormationType()
                )

                .level(
                        formation.getLevel()
                )

                .fundingAmount(
                        formation.getFundingAmount()
                )

                .fundingType(
                        formation.getFundingType()
                )

                .maleCount(
                        formation.getMaleCount()
                )

                .femaleCount(
                        formation.getFemaleCount()
                )

                .description(
                        formation.getDescription()
                )

                .trainers(
                        trainers
                )

                .build();
    }
}