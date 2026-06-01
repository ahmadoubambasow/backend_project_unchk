package com.unchk.backend.trainers.service;

import com.unchk.backend.trainers.dto.TrainerRequestDTO;
import com.unchk.backend.trainers.dto.TrainerResponseDTO;
import com.unchk.backend.trainers.entity.Trainer;
import com.unchk.backend.trainers.repository.TrainerRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TrainerService {

    private final TrainerRepository trainerRepository;

    /**
     * Création formateur
     */
    public TrainerResponseDTO createTrainer(
            TrainerRequestDTO request
    ) {

        trainerRepository.findByEmail(
                request.getEmail()
        ).ifPresent(trainer -> {

            throw new RuntimeException(
                    "Email déjà utilisé"
            );
        });

        Trainer trainer = Trainer.builder()

                .firstName(
                        request.getFirstName()
                )

                .lastName(
                        request.getLastName()
                )

                .email(
                        request.getEmail()
                )

                .phone(
                        request.getPhone()
                )

                .speciality(
                        request.getSpeciality()
                )

                .grade(
                        request.getGrade()
                )

                .type(
                        request.getType()
                )

                .build();

        Trainer savedTrainer =

                trainerRepository.save(
                        trainer
                );

        return mapToResponse(
                savedTrainer
        );
    }

    /**
     * Liste formateurs
     */
    public List<TrainerResponseDTO>
    getAllTrainers() {

        return trainerRepository.findAll()

                .stream()

                .map(this::mapToResponse)

                .toList();
    }

    /**
     * Mise à jour formateur
     */
    public TrainerResponseDTO updateTrainer(

            Long id,

            TrainerRequestDTO request
    ) {

        Trainer trainer =

                trainerRepository.findById(
                                id
                        )

                        .orElseThrow(() ->

                                new RuntimeException(
                                        "Formateur introuvable"
                                )
                        );

        trainer.setFirstName(
                request.getFirstName()
        );

        trainer.setLastName(
                request.getLastName()
        );

        trainer.setEmail(
                request.getEmail()
        );

        trainer.setPhone(
                request.getPhone()
        );

        trainer.setSpeciality(
                request.getSpeciality()
        );

        trainer.setGrade(
                request.getGrade()
        );

        trainer.setType(
                request.getType()
        );

        Trainer updatedTrainer =

                trainerRepository.save(
                        trainer
                );

        return mapToResponse(
                updatedTrainer
        );
    }

    /**
     * Suppression formateur
     */
    public void deleteTrainer(
            Long id
    ) {

        Trainer trainer =

                trainerRepository.findById(
                                id
                        )

                        .orElseThrow(() ->

                                new RuntimeException(
                                        "Formateur introuvable"
                                )
                        );

        trainerRepository.delete(
                trainer
        );
    }

    /**
     * Mapping Entity -> DTO
     */
    private TrainerResponseDTO mapToResponse(
            Trainer trainer
    ) {

        return TrainerResponseDTO

                .builder()

                .id(
                        trainer.getId()
                )

                .firstName(
                        trainer.getFirstName()
                )

                .lastName(
                        trainer.getLastName()
                )

                .email(
                        trainer.getEmail()
                )

                .phone(
                        trainer.getPhone()
                )

                .speciality(
                        trainer.getSpeciality()
                )

                .grade(
                        trainer.getGrade()
                )

                .type(
                        trainer.getType()
                )

                .build();
    }
}