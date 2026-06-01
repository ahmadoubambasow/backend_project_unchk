package com.unchk.backend.subjects.repository;

import com.unchk.backend.subjects.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubjectRepository extends JpaRepository<Subject, Long> {

    List<Subject> findByFormationId(Long formationId);
}
