package com.unchk.backend.enrollments.repository;

import com.unchk.backend.enrollments.entity.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnrollmentRepository
        extends JpaRepository<Enrollment, Long> {

    /**
     * Vérifie qu'un étudiant
     * n'est pas déjà inscrit
     * dans la même formation
     * pour la même promotion.
     */
    boolean existsByStudentIdAndPromotionIdAndFormationId(

            Long studentId,

            Long promotionId,

            Long formationId
    );

    /**
     * Nombre d'étudiants
     * dans un groupe.
     */
    long countByGroupId(
            Long groupId
    );
}