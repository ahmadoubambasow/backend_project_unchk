package com.unchk.backend.evaluations.repository;

import com.unchk.backend.evaluations.entity.Evaluation;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EvaluationRepository
        extends JpaRepository<Evaluation, Long> {

    List<Evaluation>
    findBySubjectId(
            Long subjectId
    );
}