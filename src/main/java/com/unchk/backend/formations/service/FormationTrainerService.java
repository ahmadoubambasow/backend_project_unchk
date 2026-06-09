package com.unchk.backend.formations.service;

import com.unchk.backend.formations.dto.AssignTrainerRequestDTO;
import com.unchk.backend.formations.dto.TrainerResponseDTO;
import com.unchk.backend.formations.entity.Formation;
import com.unchk.backend.formations.entity.FormationTrainer;
import com.unchk.backend.formations.repository.FormationRepository;
import com.unchk.backend.formations.repository.FormationTrainerRepository;
import com.unchk.backend.users.entity.User;
import com.unchk.backend.users.repository.UserRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FormationTrainerService {

    private final FormationRepository
            formationRepository;

    private final UserRepository
            userRepository;

    private final FormationTrainerRepository
            formationTrainerRepository;

    /**
     * Affectation formateur
     */
    public void assignTrainer(

            AssignTrainerRequestDTO request

    ) {

        Formation formation =

                formationRepository.findById(
                                request.getFormationId()
                        )

                        .orElseThrow(() ->

                                new RuntimeException(
                                        "Formation introuvable"
                                )
                        );

        User trainer =

                userRepository.findById(
                                request.getTrainerId()
                        )

                        .orElseThrow(() ->

                                new RuntimeException(
                                        "Utilisateur introuvable"
                                )
                        );

        String role =

                trainer.getRole()
                        .getName()
                        .name();

        boolean allowed =

                role.equals("ENSEIGNANT")

                        ||

                        role.equals("ENSEIGNANT_ASSOCIE")

                        ||

                        role.equals("TUTEUR");

        if (!allowed) {

            throw new RuntimeException(
                    "Cet utilisateur n'est pas un formateur"
            );
        }

        FormationTrainer formationTrainer =

                FormationTrainer.builder()

                        .formation(
                                formation
                        )

                        .trainer(
                                trainer
                        )

                        .build();

        if (

                formationTrainerRepository

                        .existsByFormationIdAndTrainerId(

                                formation.getId(),

                                trainer.getId()
                        )

        ) {

            throw new RuntimeException(

                    "Ce formateur est déjà affecté à cette formation"
            );
        }

        formationTrainerRepository.save(
                formationTrainer
        );
    }

    /**
     * Liste formateurs
     */
    public List<TrainerResponseDTO>
    getFormationTrainers(

            Long formationId

    ) {

        return formationTrainerRepository

                .findByFormationId(
                        formationId
                )

                .stream()

                .map(link ->

                        TrainerResponseDTO.builder()

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
                                                .name()
                                )

                                .build()
                )

                .toList();
    }

    /**
     * Retirer formateur
     */
    @Transactional
    public void removeTrainer(

            Long formationId,

            Long trainerId

    ) {

        formationTrainerRepository

                .deleteByFormationIdAndTrainerId(

                        formationId,

                        trainerId
                );
    }
}