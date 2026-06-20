package com.unchk.backend.notifications.service;

import com.unchk.backend.notifications.dto.NotificationResponseDTO;
import com.unchk.backend.notifications.entity.UserNotification;
import com.unchk.backend.notifications.repository.UserNotificationRepository;
import com.unchk.backend.users.entity.User;
import com.unchk.backend.users.repository.UserRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final UserNotificationRepository
            userNotificationRepository;

    private final UserRepository
            userRepository;

    /**
     * Notifications de l'utilisateur connecté
     */
    public List<NotificationResponseDTO>
    getNotificationsForUser(
            String email
    ) {

        User user =

                userRepository

                        .findByEmail(email)

                        .orElseThrow(() ->

                                new RuntimeException(
                                        "Utilisateur introuvable"
                                )
                        );

        return userNotificationRepository

                .findByUserId(
                        user.getId()
                )

                .stream()

                .map(this::mapToResponse)

                .toList();
    }

    /**
     * Marquer comme lu
     */
    public void markAsRead(

            Long notificationId,

            String email
    ) {

        User user =

                userRepository

                        .findByEmail(email)

                        .orElseThrow(() ->

                                new RuntimeException(
                                        "Utilisateur introuvable"
                                )
                        );

        UserNotification notification =

                userNotificationRepository

                        .findByIdAndUserId(

                                notificationId,

                                user.getId()
                        )

                        .orElseThrow(() ->

                                new RuntimeException(
                                        "Notification introuvable"
                                )
                        );

        notification.setIsRead(true);

        userNotificationRepository.save(
                notification
        );
    }

    /**
     * Nombre de notifications non lues
     */
    public Long countUnreadNotifications(
            String email
    ) {

        User user =

                userRepository

                        .findByEmail(email)

                        .orElseThrow(() ->

                                new RuntimeException(
                                        "Utilisateur introuvable"
                                )
                        );

        return userNotificationRepository

                .countByUserIdAndIsReadFalse(
                        user.getId()
                );
    }

    /**
     * Mapping
     */
    private NotificationResponseDTO mapToResponse(
            UserNotification notification
    ) {

        var communication =
                notification
                        .getNotification()
                        .getCommunication();

        return NotificationResponseDTO

                .builder()

                .id(
                        notification.getId()
                )

                .title(
                        notification
                                .getNotification()
                                .getTitle()
                )

                .message(
                        notification
                                .getNotification()
                                .getMessage()
                )

                .isRead(
                        notification.getIsRead()
                )

                .createdAt(
                        notification
                                .getNotification()
                                .getCreatedAt()
                )

                .communicationId(
                        communication != null
                                ? communication.getId()
                                : null
                )

                .communicationTitle(
                        communication != null
                                ? communication.getTitle()
                                : null
                )

                .communicationType(
                        communication != null
                                ? communication.getType().name()
                                : null
                )

                .communicationDescription(
                        communication != null
                                ? communication.getDescription()
                                : null
                )

                .communicationReport(
                        communication != null
                                ? communication.getReport()
                                : null
                )

                .eventDate(
                        communication != null
                                ? communication.getEventDate()
                                : null
                )

                .documentName(
                        communication != null
                                ? communication.getDocumentName()
                                : null
                )

                .documentUrl(
                        communication != null
                                ? communication.getDocumentUrl()
                                : null
                )

                .build();
    }
}