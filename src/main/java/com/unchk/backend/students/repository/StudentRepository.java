package com.unchk.backend.students.repository;

import com.unchk.backend.students.entity.Student;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository
        extends JpaRepository<Student, Long> {

    boolean existsByIne(
            String ine
    );

    boolean existsByUserId(Long userId);

    List<Student>
    findByFormationId(
            Long formationId
    );

    List<Student>
    findByPromotion(
            String promotion
    );

    long countByStartYear(
            Integer startYear
    );

    List<Student>
    findByGroupId(
            Long groupId
    );
}