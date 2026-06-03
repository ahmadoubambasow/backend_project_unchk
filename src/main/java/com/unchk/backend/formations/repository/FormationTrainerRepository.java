package com.unchk.backend.formations.repository;

import com.unchk.backend.formations.entity.FormationTrainer;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FormationTrainerRepository
        extends JpaRepository<FormationTrainer, Long> {

    List<FormationTrainer>
    findByFormationId(Long formationId);

    void deleteByFormationIdAndTrainerId(
            Long formationId,
            Long trainerId
    );

    boolean existsByFormationIdAndTrainerId(

            Long formationId,

            Long trainerId
    );
}