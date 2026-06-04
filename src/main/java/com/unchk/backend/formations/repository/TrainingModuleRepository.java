package com.unchk.backend.formations.repository;

import com.unchk.backend.formations.entity.TrainingModule;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TrainingModuleRepository
        extends JpaRepository<TrainingModule, Long> {

    List<TrainingModule>
    findByFormationId(
            Long formationId
    );
}