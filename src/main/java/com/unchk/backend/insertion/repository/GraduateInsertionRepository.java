package com.unchk.backend.insertion.repository;

import com.unchk.backend.insertion.entity.GraduateInsertion;
import com.unchk.backend.insertion.entity.GraduateInsertionStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GraduateInsertionRepository extends
        JpaRepository<GraduateInsertion, Long>
{
    long countByStatus(
            GraduateInsertionStatus status
    );
}
