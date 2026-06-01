package com.unchk.backend.formations.repository;

import com.unchk.backend.formations.entity.Formation;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FormationRepository
        extends JpaRepository<Formation, Long> {

    /**
     * Recherche code formation
     */
    Optional<Formation> findByCode(
            String code
    );

    /**
     * Formations d'une filière
     */
    List<Formation> findByFiliereId(
            Long filiereId
    );
}