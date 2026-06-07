package com.unchk.backend.hr.repository;

import com.unchk.backend.hr.entity.PersonnelFile;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PersonnelFileRepository
        extends JpaRepository<
        PersonnelFile,
        Long
        > {

    Optional<PersonnelFile>
    findByUserId(
            Long userId
    );
}