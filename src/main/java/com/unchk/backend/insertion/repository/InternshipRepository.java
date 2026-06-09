package com.unchk.backend.insertion.repository;

import com.unchk.backend.insertion.entity.Internship;

import com.unchk.backend.insertion.entity.InternshipStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InternshipRepository
        extends JpaRepository<Internship, Long> {

    long countByStatus(
            InternshipStatus status
    );
}