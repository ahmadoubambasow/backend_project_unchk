package com.unchk.backend.dashboard.service;

import com.unchk.backend.dashboard.dto.DashboardStatsDTO;

import com.unchk.backend.users.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DashboardService {



    private final UserRepository  userRepository;

    /**
     * Retourne statistiques dashboard
     */

}
