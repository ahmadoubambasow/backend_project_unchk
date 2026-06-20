package com.unchk.backend.communications.service;

import com.unchk.backend.communications.dto.CommunicationRequestDTO;
import com.unchk.backend.communications.dto.CommunicationResponseDTO;
import com.unchk.backend.communications.entity.Communication;
import com.unchk.backend.communications.entity.CommunicationAccessRole;
import com.unchk.backend.communications.repository.CommunicationRepository;

import com.unchk.backend.notifications.entity.Notification;
import com.unchk.backend.notifications.entity.UserNotification;
import com.unchk.backend.notifications.repository.NotificationRepository;
import com.unchk.backend.notifications.repository.UserNotificationRepository;
import com.unchk.backend.users.entity.User;
import com.unchk.backend.users.entity.UserRole;
import com.unchk.backend.users.repository.UserRepository;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommunicationService {

    private final CommunicationRepository communicationRepository;

    private final NotificationRepository notificationRepository;

    private final UserNotificationRepository userNotificationRepository;

    private final UserRepository userRepository;

    /**
     * Création
     */
    public CommunicationResponseDTO createCommunication(
            CommunicationRequestDTO request
    ) {

        Authentication authentication =
                SecurityContextHolder
                        .getContext()
                        .getAuthentication();

        String email = authentication.getName();

        User currentUser =
                userRepository
                        .findByEmail(email)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Utilisateur introuvable"
                                )
                        );

        Communication communication =

                Communication.builder()

                        .title(
                                request.getTitle()
                        )

                        .type(
                                request.getType()
                        )

                        .description(
                                request.getDescription()
                        )

                        .report(
                                request.getReport()
                        )

                        .eventDate(
                                LocalDateTime.parse(
                                        request.getEventDate()
                                )
                        )

                        .createdAt(
                                LocalDateTime.now()
                        )

                        .documentName(
                                request.getDocumentName()
                        )

                        .documentUrl(
                                request.getDocumentUrl()
                        )

                        .documentType(
                                request.getDocumentType()
                        )

                        .accessRole(
                                request.getAccessRole()
                        )

                        .build();

        Communication savedCommunication =

                communicationRepository.save(
                        communication
                );

        Notification savedNotification =

                notificationRepository.save(

                        Notification.builder()

                                .title(
                                        "Nouvelle communication"
                                )

                                .message(
                                        savedCommunication.getTitle()
                                )

                                .communication(
                                        savedCommunication
                                )

                                .createdAt(
                                        LocalDateTime.now()
                                )

                                .build()
                );

        List<User> users;

        if (

                savedCommunication.getAccessRole()
                        == UserRole.ALL

        ) {

            users = userRepository.findAll();

        } else {

            users = userRepository.findByRole_Name(

                    savedCommunication.getAccessRole()
            );
        }

        System.out.println(
                "Role : "
                        + savedCommunication.getAccessRole()
        );

        System.out.println(
                "Users trouvés : "
                        + users.size()
        );

        users.stream()

                .filter(user ->

                        !user.getId().equals(
                                currentUser.getId()
                        )
                )

                .forEach(user -> {

                    UserNotification userNotification =

                            UserNotification.builder()

                                    .user(user)

                                    .notification(
                                            savedNotification
                                    )

                                    .isRead(false)

                                    .build();

                    userNotificationRepository.save(
                            userNotification
                    );
                });
        return mapToResponse(
                savedCommunication
        );
    }

    /**
     * Liste
     */
    public List<CommunicationResponseDTO>
    getAllCommunications() {

        return communicationRepository.findAll()

                .stream()

                .map(this::mapToResponse)

                .toList();
    }

    /**
     * Modification
     */
    public CommunicationResponseDTO updateCommunication(

            Long id,

            CommunicationRequestDTO request
    ) {

        Communication communication =

                communicationRepository.findById(id)

                        .orElseThrow(() ->

                                new RuntimeException(
                                        "Communication introuvable"
                                )
                        );

        communication.setTitle(
                request.getTitle()
        );

        communication.setType(
                request.getType()
        );

        communication.setDescription(
                request.getDescription()
        );

        communication.setReport(
                request.getReport()
        );

        communication.setEventDate(
                LocalDateTime.parse(
                        request.getEventDate()
                )
        );

        communication.setDocumentName(
                request.getDocumentName()
        );

        communication.setDocumentUrl(
                request.getDocumentUrl()
        );

        communication.setDocumentType(
                request.getDocumentType()
        );

        communication.setAccessRole(
                request.getAccessRole()
        );

        Communication updatedCommunication =

                communicationRepository.save(
                        communication
                );

        return mapToResponse(
                updatedCommunication
        );
    }

    /**
     * Suppression
     */
    public void deleteCommunication(
            Long id
    ) {

        Communication communication =

                communicationRepository.findById(id)

                        .orElseThrow(() ->

                                new RuntimeException(
                                        "Communication introuvable"
                                )
                        );

        communicationRepository.delete(
                communication
        );
    }

    /**
     * Archives
     */
    public List<CommunicationResponseDTO> getArchives() {

        Authentication authentication =
                SecurityContextHolder
                        .getContext()
                        .getAuthentication();

        String email = authentication.getName();

        User user = userRepository.findByEmail(email)

                .orElseThrow(() ->

                        new RuntimeException(
                                "Utilisateur introuvable"
                        )
                );

        UserRole role =

                user.getRole()
                        .getName();

        List<Communication> communications;

        if (

                role == UserRole.ADMIN

        ) {

            communications =

                    communicationRepository.findAll();

        } else {

            communications =

                    communicationRepository.findByAccessRoleIn(

                            Arrays.asList(

                                    role,

                                    UserRole.ALL
                            )
                    );
        }

        return communications

                .stream()

                .filter(c ->

                        c.getDocumentUrl() != null

                                &&

                                !c.getDocumentUrl().isBlank()
                )

                .map(this::mapToResponse)

                .toList();
    }

    /**
     * Mapping
     */
    private CommunicationResponseDTO mapToResponse(
            Communication communication
    ) {

        return CommunicationResponseDTO

                .builder()

                .id(
                        communication.getId()
                )

                .title(
                        communication.getTitle()
                )

                .type(
                        communication.getType()
                )

                .description(
                        communication.getDescription()
                )

                .report(
                        communication.getReport()
                )

                .eventDate(
                        communication
                                .getEventDate()
                                .toString()
                )

                .documentName(
                        communication.getDocumentName()
                )

                .documentUrl(
                        communication.getDocumentUrl()
                )

                .documentType(
                        communication.getDocumentType()
                )

                .accessRole(
                        communication.getAccessRole()
                )

                .createdAt(
                        communication
                                .getCreatedAt()
                                .toString()
                )

                .build();
    }
}

