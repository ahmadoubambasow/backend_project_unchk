package com.unchk.backend.dashboard.service;

import com.unchk.backend.dashboard.dto.DashboardStatsDTO;
import com.unchk.backend.students.entity.StudentStatus;
import com.unchk.backend.students.repository.StudentRepository;
import com.unchk.backend.users.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DashboardService {

    private final StudentRepository studentRepository;

    private final UserRepository  userRepository;

    /**
     * Retourne statistiques dashboard
     */
    public DashboardStatsDTO getStats() {

        long students = studentRepository.count();

        long activeStudents = studentRepository.countByStatus(
                StudentStatus.ACTIVE
        );

        long users = userRepository.count();

        // Temporaire
        long formations = 0;

        return DashboardStatsDTO.builder()
                .students(students)
                .activeStudents(activeStudents)
                .users(users)
                .formations(formations)
                .build();
    }
}
