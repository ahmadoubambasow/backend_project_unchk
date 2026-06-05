package com.unchk.backend.schedule.controller;

import com.unchk.backend.schedule.dto.ScheduleRequestDTO;
import com.unchk.backend.schedule.dto.ScheduleResponseDTO;
import com.unchk.backend.schedule.service.ScheduleService;

import lombok.RequiredArgsConstructor;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/schedules")
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService
            scheduleService;

    /**
     * Création créneau
     */
    @PostMapping
    @PreAuthorize(
            "hasAnyRole('ADMIN','RESPONSABLE_FORMATION')"
    )
    public ScheduleResponseDTO createSchedule(

            @RequestBody
            ScheduleRequestDTO request

    ) {

        return scheduleService.createSchedule(
                request
        );
    }

    @GetMapping("/my-schedule")
    public List<ScheduleResponseDTO>
    getMySchedules() {

        return scheduleService
                .getMySchedules();
    }

    @PutMapping("/{id}")
    @PreAuthorize(
            "hasAnyRole('ADMIN','RESPONSABLE_FORMATION')"
    )
    public ScheduleResponseDTO
    updateSchedule(

            @PathVariable
            Long id,

            @RequestBody
            ScheduleRequestDTO request

    ) {

        return scheduleService.updateSchedule(
                id,
                request
        );
    }

    /**
     * Liste complète
     */
    @GetMapping
    public List<ScheduleResponseDTO>
    getAllSchedules() {

        return scheduleService
                .getAllSchedules();
    }

    /**
     * Détail créneau
     */
    @GetMapping("/{id}")
    public ScheduleResponseDTO
    getScheduleById(

            @PathVariable
            Long id

    ) {

        return scheduleService
                .getScheduleById(id);
    }

    /**
     * EDT Groupe
     */
    @GetMapping("/group/{groupId}")
    public List<ScheduleResponseDTO>
    getGroupSchedules(

            @PathVariable
            Long groupId

    ) {

        return scheduleService
                .getGroupSchedules(
                        groupId
                );
    }

    /**
     * EDT Formateur
     */
    @GetMapping("/trainer/{trainerId}")
    public List<ScheduleResponseDTO>
    getTrainerSchedules(

            @PathVariable
            Long trainerId

    ) {

        return scheduleService
                .getTrainerSchedules(
                        trainerId
                );
    }

    /**
     * Suppression
     */
    @DeleteMapping("/{id}")
    @PreAuthorize(
            "hasAnyRole('ADMIN','RESPONSABLE_FORMATION')"
    )
    public void deleteSchedule(

            @PathVariable
            Long id

    ) {

        scheduleService.deleteSchedule(
                id
        );
    }
}