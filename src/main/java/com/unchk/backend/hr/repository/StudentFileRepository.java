package com.unchk.backend.hr.repository;

import com.unchk.backend.hr.entity.StudentFile;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudentFileRepository
        extends JpaRepository<StudentFile, Long> {

    Optional<StudentFile>
    findByStudent_Id(
            Long studentId
    );
}