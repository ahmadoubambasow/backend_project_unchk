package com.unchk.backend.notifications.controller;

import com.unchk.backend.notifications.dto.NotificationResponseDTO;
import com.unchk.backend.notifications.service.NotificationService;

import lombok.RequiredArgsConstructor;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notifications")
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationService
            notificationService;

    /**
     * Mes notifications
     */
    @GetMapping("/me")
    public List<NotificationResponseDTO>
    getMyNotifications(
            Authentication authentication
    ) {

        return notificationService

                .getNotificationsForUser(

                        authentication.getName()
                );
    }

    /**
     * Nombre non lues
     */
    @GetMapping("/unread-count")
    public Long getUnreadCount(
            Authentication authentication
    ) {

        return notificationService

                .countUnreadNotifications(

                        authentication.getName()
                );
    }

    /**
     * Marquer comme lue
     */
    @PutMapping("/{id}/read")
    public void markAsRead(

            @PathVariable
            Long id,

            Authentication authentication
    ) {

        notificationService

                .markAsRead(

                        id,

                        authentication.getName()
                );
    }
}