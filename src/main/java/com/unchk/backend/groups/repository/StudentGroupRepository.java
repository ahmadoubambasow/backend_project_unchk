package com.unchk.backend.groups.repository;

import com.unchk.backend.groups.entity.StudentGroup;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentGroupRepository
        extends JpaRepository<StudentGroup, Long> {

    /**
     * Groupes d'une formation.
     */
    List<StudentGroup> findByFormationId(
            Long formationId
    );
}