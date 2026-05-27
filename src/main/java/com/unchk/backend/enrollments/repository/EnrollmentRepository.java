package com.unchk.backend.enrollments.repository;

import com.unchk.backend.enrollments.entity.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnrollmentRepository extends JpaRepository <Enrollment, Long> {
}
