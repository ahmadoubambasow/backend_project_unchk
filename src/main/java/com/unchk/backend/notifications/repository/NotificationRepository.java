package com.unchk.backend.notifications.repository;

import com.unchk.backend.notifications.entity.Notification;

import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository
        extends JpaRepository<Notification, Long> {
}