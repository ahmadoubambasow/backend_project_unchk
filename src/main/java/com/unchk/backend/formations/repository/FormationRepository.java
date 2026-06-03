package com.unchk.backend.formations.repository;

import com.unchk.backend.formations.entity.Formation;

import org.springframework.data.jpa.repository.JpaRepository;

public interface FormationRepository
        extends JpaRepository<Formation, Long> {
}