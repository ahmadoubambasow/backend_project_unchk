package com.unchk.backend.students.repository;

import com.unchk.backend.students.entity.StudentGroup;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentGroupRepository
        extends JpaRepository<StudentGroup, Long> {

    List<StudentGroup>
    findByFormationId(
            Long formationId
    );
}
