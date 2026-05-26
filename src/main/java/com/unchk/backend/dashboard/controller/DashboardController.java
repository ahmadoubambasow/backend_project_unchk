package com.unchk.backend.dashboard.controller;

import com.unchk.backend.dashboard.dto.DashboardStatsDTO;
import com.unchk.backend.dashboard.service.DashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/dashboard")
@RequiredArgsConstructor
public class DashboardController {

    private final DashboardService dashboardService;

    /**
     * Statistiques dashboard
     */
    @PreAuthorize(
            "hasAnyRole('ROLE_ADMIN', 'ROLE_SUPER_ADMIN')"
    )
    @GetMapping("/stats")
    public DashboardStatsDTO getStats() {

        return dashboardService.getStats();
    }
}
