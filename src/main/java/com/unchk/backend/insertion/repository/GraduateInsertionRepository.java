package com.unchk.backend.insertion.repository;

import com.unchk.backend.insertion.entity.GraduateInsertion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GraduateInsertionRepository extends
        JpaRepository<GraduateInsertion, Long>
{
}
