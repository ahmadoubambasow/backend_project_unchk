package com.unchk.backend.schedules.controller;

import com.unchk.backend.schedules.dto.ScheduleRequestDTO;
import com.unchk.backend.schedules.dto.ScheduleResponseDTO;
import com.unchk.backend.schedules.service.ScheduleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/schedules")
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;

    /**
     * Création séance
     */
    @PreAuthorize(
            "hasAnyRole('ADMIN', 'SUPER_ADMIN')"
    )
    @PostMapping
    public ScheduleResponseDTO createSchedule(
            @Valid
            @RequestBody
            ScheduleRequestDTO request
    ) {
        return scheduleService.createSchedule(request);
    }

    /**
     * Liste séances
     */
    @GetMapping
    public List<ScheduleResponseDTO> getAllSchedules() {
        return scheduleService.getAllSchedules();
    }

    /**
     * Mise à jour séance
     */
    @PreAuthorize(
            "hasAnyRole('ADMIN', 'SUPER_ADMIN')"
    )
    @PutMapping("/{id}")
    public ScheduleResponseDTO updateSchedule(
            @PathVariable Long id,
            @Valid
            @RequestBody
            ScheduleRequestDTO request
    ) {
        return scheduleService.updateSchedule(id, request);
    }

    /**
     * Suppression séance
     */
    @PreAuthorize(
            "hasAnyRole('ADMIN', 'SUPER_ADMIN')"
    )
    @DeleteMapping("/{id}")
    public void deleteSchedule(
            @PathVariable Long id
    ) {
        scheduleService.deleteSchedule(id);
    }
}
