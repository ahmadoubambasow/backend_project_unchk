package com.unchk.backend.notifications.repository;

import com.unchk.backend.notifications.entity.UserNotification;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserNotificationRepository
        extends JpaRepository<UserNotification, Long> {

    List<UserNotification>
    findByUserId(
            Long userId
    );

    Long countByUserIdAndIsReadFalse(
            Long userId
    );

    Optional<UserNotification> findByIdAndUserId(

            Long id,

            Long userId
    );
}