package com.unchk.backend.trainers.repository;

import com.unchk.backend.trainers.entity.TrainerSubject;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TrainerSubjectRepository
        extends JpaRepository<TrainerSubject, Long> {

    List<TrainerSubject>
    findByTrainerId(
            Long trainerId
    );

    List<TrainerSubject>
    findBySubjectId(
            Long subjectId
    );

    boolean existsByTrainerIdAndSubjectId(
            Long trainerId,
            Long subjectId
    );
}