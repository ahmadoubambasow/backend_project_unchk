package com.unchk.backend.insertion.controller;

import com.unchk.backend.insertion.dto.DashboardInsertionResponseDTO;
import com.unchk.backend.insertion.service.DashboardInsertionService;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/insertion-dashboard")
@RequiredArgsConstructor
public class DashboardInsertionController {

    private final DashboardInsertionService
            service;

    @GetMapping
    public DashboardInsertionResponseDTO
    getDashboard() {

        return service.getDashboard();
    }
}