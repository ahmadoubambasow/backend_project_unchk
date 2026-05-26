package com.unchk.backend.students.repository;

import com.unchk.backend.students.entity.Student;
import com.unchk.backend.students.entity.StudentStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Repository étudiants.
 */
public interface StudentRepository extends JpaRepository<Student, Long> {

    /**
     * Recherche étudiant par email
     */
    Optional<Student> findByEmail(String email);

    /**
     * Recherche étudiant par matricule
     */
    Optional<Student> findByMatricule(String matricule);

    /**
     * Nombre étudiants par status
     */
    long countByStatus(StudentStatus status);
}
