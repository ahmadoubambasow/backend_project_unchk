package com.unchk.backend.insertion.repository;

import com.unchk.backend.insertion.entity.StudentContact;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentContactRepository
        extends JpaRepository<StudentContact, Long> {

    List<StudentContact>
    findByStudentIdOrderByContactDateDesc(
            Long studentId
    );
}