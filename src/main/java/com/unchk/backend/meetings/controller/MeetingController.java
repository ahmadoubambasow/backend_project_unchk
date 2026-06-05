package com.unchk.backend.meetings.controller;

import com.unchk.backend.meetings.dto.MeetingRequestDTO;
import com.unchk.backend.meetings.dto.MeetingResponseDTO;
import com.unchk.backend.meetings.service.MeetingService;

import lombok.RequiredArgsConstructor;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/meetings")
@RequiredArgsConstructor
public class MeetingController {

    private final MeetingService
            meetingService;

    /**
     * Création réunion
     */
    @PostMapping
    @PreAuthorize(
            "hasAnyRole('ADMIN','RESPONSABLE_FORMATION')"
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
     * Liste complète
     */
    @GetMapping
    @PreAuthorize(
            "hasAnyRole('ADMIN','RESPONSABLE_FORMATION')"
    )
    public List<MeetingResponseDTO>
    getAllMeetings() {

        return meetingService
                .getAllMeetings();
    }

    /**
     * Réunions utilisateur connecté
     */
    @GetMapping("/my-meetings")
    public List<MeetingResponseDTO>
    getMyMeetings() {

        return meetingService
                .getMyMeetings();
    }

    /**
     * Détail réunion
     */
    @GetMapping("/{id}")
    public MeetingResponseDTO
    getMeetingById(

            @PathVariable
            Long id

    ) {

        return meetingService
                .getMeetingById(id);
    }

    /**
     * Modification
     */
    @PutMapping("/{id}")
    @PreAuthorize(
            "hasAnyRole('ADMIN','RESPONSABLE_FORMATION')"
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
     * Suppression
     */
    @DeleteMapping("/{id}")
    @PreAuthorize(
            "hasAnyRole('ADMIN','RESPONSABLE_FORMATION')"
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