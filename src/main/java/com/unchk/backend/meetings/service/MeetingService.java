package com.unchk.backend.meetings.service;

import com.unchk.backend.meetings.dto.MeetingRequestDTO;
import com.unchk.backend.meetings.dto.MeetingResponseDTO;
import com.unchk.backend.meetings.entity.Meeting;
import com.unchk.backend.meetings.repository.MeetingRepository;
import com.unchk.backend.students.entity.StudentGroup;
import com.unchk.backend.students.repository.StudentGroupRepository;
import com.unchk.backend.users.entity.User;
import com.unchk.backend.users.repository.UserRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MeetingService {

    private final MeetingRepository
            meetingRepository;

    private final UserRepository
            userRepository;

    private final StudentGroupRepository
            studentGroupRepository;

    /**
     * Création réunion
     */
    public MeetingResponseDTO createMeeting(
            MeetingRequestDTO request
    ) {

        User organizer =

                userRepository

                        .findById(
                                request.getOrganizerId()
                        )

                        .orElseThrow(() ->

                                new RuntimeException(
                                        "Organisateur introuvable"
                                )
                        );

        StudentGroup group = null;

        if (request.getGroupId() != null) {

            group =

                    studentGroupRepository

                            .findById(
                                    request.getGroupId()
                            )

                            .orElseThrow(() ->

                                    new RuntimeException(
                                            "Groupe introuvable"
                                    )
                            );
        }

        List<User> participants =

                request.getParticipantIds() != null

                        ? userRepository.findAllById(
                        request.getParticipantIds()
                )

                        : List.of();

        Meeting meeting =

                Meeting.builder()

                        .title(
                                request.getTitle()
                        )

                        .type(
                                request.getType()
                        )

                        .status(
                                request.getStatus()
                        )

                        .meetingDate(
                                request.getMeetingDate()
                        )

                        .startTime(
                                request.getStartTime()
                        )

                        .endTime(
                                request.getEndTime()
                        )

                        .description(
                                request.getDescription()
                        )

                        .report(
                                request.getReport()
                        )

                        .organizer(
                                organizer
                        )

                        .group(
                                group
                        )

                        .participants(
                                participants
                        )

                        .build();

        meeting = meetingRepository.save(
                meeting
        );

        return mapToResponse(
                meeting
        );
    }

    /**
     * Modification réunion
     */
    public MeetingResponseDTO updateMeeting(
            Long id,
            MeetingRequestDTO request
    ) {

        Meeting meeting =

                meetingRepository

                        .findById(id)

                        .orElseThrow(() ->

                                new RuntimeException(
                                        "Réunion introuvable"
                                )
                        );

        User organizer =

                userRepository

                        .findById(
                                request.getOrganizerId()
                        )

                        .orElseThrow(() ->

                                new RuntimeException(
                                        "Organisateur introuvable"
                                )
                        );

        StudentGroup group = null;

        if (request.getGroupId() != null) {

            group =

                    studentGroupRepository

                            .findById(
                                    request.getGroupId()
                            )

                            .orElseThrow(() ->

                                    new RuntimeException(
                                            "Groupe introuvable"
                                    )
                            );
        }

        List<User> participants =

                request.getParticipantIds() != null

                        ? userRepository.findAllById(
                        request.getParticipantIds()
                )

                        : List.of();

        meeting.setTitle(
                request.getTitle()
        );

        meeting.setType(
                request.getType()
        );

        meeting.setStatus(
                request.getStatus()
        );

        meeting.setMeetingDate(
                request.getMeetingDate()
        );

        meeting.setStartTime(
                request.getStartTime()
        );

        meeting.setEndTime(
                request.getEndTime()
        );

        meeting.setDescription(
                request.getDescription()
        );

        meeting.setReport(
                request.getReport()
        );

        meeting.setOrganizer(
                organizer
        );

        meeting.setGroup(
                group
        );

        meeting.setParticipants(
                participants
        );

        meeting = meetingRepository.save(
                meeting
        );

        return mapToResponse(
                meeting
        );
    }

    /**
     * Liste complète
     */
    public List<MeetingResponseDTO>
    getAllMeetings() {

        return meetingRepository

                .findAll()

                .stream()

                .map(
                        this::mapToResponse
                )

                .toList();
    }

    /**
     * Détail
     */
    public MeetingResponseDTO getMeetingById(
            Long id
    ) {

        Meeting meeting =

                meetingRepository

                        .findById(id)

                        .orElseThrow(() ->

                                new RuntimeException(
                                        "Réunion introuvable"
                                )
                        );

        return mapToResponse(
                meeting
        );
    }

    /**
     * Suppression
     */
    public void deleteMeeting(
            Long id
    ) {

        meetingRepository.deleteById(
                id
        );
    }

    /**
     * Mes réunions
     */
    public List<MeetingResponseDTO>
    getMyMeetings() {

        String email =

                SecurityContextHolder

                        .getContext()

                        .getAuthentication()

                        .getName();

        User user =

                userRepository

                        .findByEmail(email)

                        .orElseThrow(() ->

                                new RuntimeException(
                                        "Utilisateur introuvable"
                                )
                        );

        String role =

                user.getRole()
                        .getName();

        if (

                role.equals("ADMIN")

                        ||

                        role.equals("RESPONSABLE_FORMATION")

        ) {

            return getAllMeetings();
        }

        return meetingRepository

                .findAll()

                .stream()

                .filter(meeting ->

                        meeting.getOrganizer()

                                .getId()

                                .equals(
                                        user.getId()
                                )

                                ||

                                meeting.getParticipants()

                                        .stream()

                                        .anyMatch(

                                                participant ->

                                                        participant.getId()

                                                                .equals(
                                                                        user.getId()
                                                                )
                                        )
                )

                .map(
                        this::mapToResponse
                )

                .toList();
    }

    /**
     * Mapping DTO
     */
    private MeetingResponseDTO
    mapToResponse(
            Meeting meeting
    ) {

        return MeetingResponseDTO

                .builder()

                .id(
                        meeting.getId()
                )

                .title(
                        meeting.getTitle()
                )

                .type(
                        meeting.getType()
                )

                .status(
                        meeting.getStatus()
                )

                .meetingDate(
                        meeting.getMeetingDate()
                )

                .startTime(
                        meeting.getStartTime()
                )

                .endTime(
                        meeting.getEndTime()
                )

                .description(
                        meeting.getDescription()
                )

                .report(
                        meeting.getReport()
                )

                .organizerId(
                        meeting.getOrganizer().getId()
                )

                .organizerName(
                        meeting.getOrganizer().getFullName()
                )

                .groupId(
                        meeting.getGroup() != null
                                ? meeting.getGroup().getId()
                                : null
                )

                .groupName(
                        meeting.getGroup() != null
                                ? meeting.getGroup().getName()
                                : null
                )

                .participantIds(

                        meeting.getParticipants()

                                .stream()

                                .map(User::getId)

                                .toList()
                )

                .participantNames(

                        meeting.getParticipants()

                                .stream()

                                .map(User::getFullName)

                                .toList()
                )

                .build();
    }
}