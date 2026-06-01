package com.unchk.backend.filieres.repository;

import com.unchk.backend.filieres.entity.Filiere;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FiliereRepository
        extends JpaRepository<Filiere, Long> {

    Optional<Filiere> findByCode(
            String code
    );
}