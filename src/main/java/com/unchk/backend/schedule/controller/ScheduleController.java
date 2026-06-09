package com.unchk.backend.schedule.controller;

import com.unchk.backend.schedule.dto.ScheduleRequestDTO;
import com.unchk.backend.schedule.dto.ScheduleResponseDTO;
import com.unchk.backend.schedule.service.ScheduleService;

import lombok.RequiredArgsConstructor;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller REST de gestion des emplois du temps.
 */
@RestController
@RequestMapping("/api/schedules")
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;

    /**
     * Création d'un créneau d'emploi du temps.
     *
     * Autorisés :
     * - ADMIN
     * - RESPONSABLE_FORMATION
     */
    @PostMapping
    @PreAuthorize(
            "hasAnyRole(" +
                    "'ADMIN'," +
                    "'RESPONSABLE_FORMATION'" +
                    ")"
    )
    public ScheduleResponseDTO createSchedule(

            @RequestBody
            ScheduleRequestDTO request

    ) {

        return scheduleService.createSchedule(
                request
        );
    }

    /**
     * Emploi du temps de l'utilisateur connecté.
     *
     * Autorisés :
     * - Tous les utilisateurs authentifiés.
     */
    @GetMapping("/my-schedule")
    @PreAuthorize("isAuthenticated()")
    public List<ScheduleResponseDTO>
    getMySchedules() {

        return scheduleService.getMySchedules();
    }

    /**
     * Modification d'un créneau.
     *
     * Autorisés :
     * - ADMIN
     * - RESPONSABLE_FORMATION
     */
    @PutMapping("/{id}")
    @PreAuthorize(
            "hasAnyRole(" +
                    "'ADMIN'," +
                    "'RESPONSABLE_FORMATION'" +
                    ")"
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
     * Liste complète des emplois du temps.
     *
     * Autorisés :
     * - ADMIN
     * - DIRECTION
     * - RESPONSABLE_FORMATION
     */
    @GetMapping
    @PreAuthorize(
            "hasAnyRole(" +
                    "'ADMIN'," +
                    "'DIRECTION'," +
                    "'RESPONSABLE_FORMATION'" +
                    ")"
    )
    public List<ScheduleResponseDTO>
    getAllSchedules() {

        return scheduleService.getAllSchedules();
    }

    /**
     * Consultation d'un créneau.
     *
     * Autorisés :
     * - Tous les utilisateurs authentifiés.
     *
     * Remarque :
     * Le service doit vérifier que l'utilisateur
     * est concerné par le créneau (étudiant du groupe,
     * enseignant affecté, ou gestionnaire).
     */
    @GetMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    public ScheduleResponseDTO
    getScheduleById(

            @PathVariable
            Long id

    ) {

        return scheduleService.getScheduleById(
                id
        );
    }

    /**
     * Emploi du temps d'un groupe.
     *
     * Autorisés :
     * - Tous les utilisateurs authentifiés.
     *
     * Remarque :
     * Le service peut restreindre l'accès
     * aux étudiants appartenant au groupe
     * et aux responsables concernés.
     */
    @GetMapping("/group/{groupId}")
    @PreAuthorize("isAuthenticated()")
    public List<ScheduleResponseDTO>
    getGroupSchedules(

            @PathVariable
            Long groupId

    ) {

        return scheduleService.getGroupSchedules(
                groupId
        );
    }

    /**
     * Emploi du temps d'un formateur.
     *
     * Autorisés :
     * - ADMIN
     * - DIRECTION
     * - RESPONSABLE_FORMATION
     * - ENSEIGNANT concerné
     *
     * Remarque :
     * Le service doit empêcher un enseignant
     * d'accéder aux emplois du temps d'autres enseignants.
     */
    @GetMapping("/trainer/{trainerId}")
    @PreAuthorize("isAuthenticated()")
    public List<ScheduleResponseDTO>
    getTrainerSchedules(

            @PathVariable
            Long trainerId

    ) {

        return scheduleService.getTrainerSchedules(
                trainerId
        );
    }

    /**
     * Suppression d'un créneau.
     *
     * Autorisés :
     * - ADMIN uniquement
     */
    @DeleteMapping("/{id}")
    @PreAuthorize(
            "hasRole('ADMIN')"
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