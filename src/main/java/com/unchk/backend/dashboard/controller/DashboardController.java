package com.unchk.backend.dashboard.controller;

import com.unchk.backend.dashboard.dto.DashboardDTO;
import com.unchk.backend.dashboard.service.DashboardService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/dashboard")
@RequiredArgsConstructor
public class DashboardController {

    private final DashboardService dashboardService;

    @PreAuthorize(
            "isAuthenticated()"
    )
    @GetMapping
    public ResponseEntity<DashboardDTO> getDashboard() {

        return ResponseEntity.ok(
                dashboardService.getDashboard()
        );
    }
}