package com.unchk.backend.meetings.controller;

import com.unchk.backend.meetings.dto.MeetingRequestDTO;
import com.unchk.backend.meetings.dto.MeetingResponseDTO;
import com.unchk.backend.meetings.service.MeetingService;

import lombok.RequiredArgsConstructor;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller REST de gestion des réunions.
 */
@RestController
@RequestMapping("/api/meetings")
@RequiredArgsConstructor
public class MeetingController {

    private final MeetingService meetingService;

    /**
     * Création d'une réunion.
     *
     * Autorisés :
     * - ADMIN
     * - DIRECTION
     * - RESPONSABLE_FORMATION
     * - SECRETAIRE
     */
    @PostMapping
    @PreAuthorize(
            "hasAnyRole(" +
                    "'ADMIN'," +
                    "'DIRECTION'," +
                    "'RESPONSABLE_FORMATION'," +
                    "'SECRETAIRE'" +
                    ")"
    )
    public MeetingResponseDTO createMeeting(

            @RequestBody
            MeetingRequestDTO request

    ) {

        return meetingService.createMeeting(
                request
        );
    }

    /**
     * Liste complète des réunions.
     *
     * Autorisés :
     * - ADMIN
     * - DIRECTION
     * - RESPONSABLE_FORMATION
     * - SECRETAIRE
     */
    @GetMapping
    @PreAuthorize(
            "hasAnyRole(" +
                    "'ADMIN'," +
                    "'DIRECTION'," +
                    "'RESPONSABLE_FORMATION'," +
                    "'SECRETAIRE'" +
                    ")"
    )
    public List<MeetingResponseDTO>
    getAllMeetings() {

        return meetingService.getAllMeetings();
    }

    /**
     * Réunions de l'utilisateur connecté.
     *
     * Autorisés :
     * - Tous les utilisateurs authentifiés.
     */
    @GetMapping("/my-meetings")
    @PreAuthorize("isAuthenticated()")
    public List<MeetingResponseDTO>
    getMyMeetings() {

        return meetingService.getMyMeetings();
    }

    /**
     * Détail d'une réunion.
     *
     * Autorisés :
     * - Tous les utilisateurs authentifiés.
     *
     * Remarque :
     * Le service doit vérifier que l'utilisateur
     * connecté est effectivement participant à
     * la réunion ou dispose d'un rôle de gestion.
     */
    @GetMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    public MeetingResponseDTO
    getMeetingById(

            @PathVariable
            Long id

    ) {

        return meetingService.getMeetingById(
                id
        );
    }

    /**
     * Modification d'une réunion.
     *
     * Autorisés :
     * - ADMIN
     * - DIRECTION
     * - RESPONSABLE_FORMATION
     * - SECRETAIRE
     */
    @PutMapping("/{id}")
    @PreAuthorize(
            "hasAnyRole(" +
                    "'ADMIN'," +
                    "'DIRECTION'," +
                    "'RESPONSABLE_FORMATION'," +
                    "'SECRETAIRE'" +
                    ")"
    )
    public MeetingResponseDTO
    updateMeeting(

            @PathVariable
            Long id,

            @RequestBody
            MeetingRequestDTO request

    ) {

        return meetingService.updateMeeting(

                id,

                request
        );
    }

    /**
     * Suppression d'une réunion.
     *
     * Autorisés :
     * - ADMIN
     * - DIRECTION
     */
    @DeleteMapping("/{id}")
    @PreAuthorize(
            "hasAnyRole(" +
                    "'ADMIN'," +
                    "'DIRECTION'" +
                    ")"
    )
    public void deleteMeeting(

            @PathVariable
            Long id

    ) {

        meetingService.deleteMeeting(
                id
        );
    }
}