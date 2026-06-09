package com.unchk.backend.dashboard.service;

import com.unchk.backend.dashboard.dto.DashboardDTO;

import com.unchk.backend.users.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
public interface DashboardService {

    DashboardDTO getDashboard();

}
