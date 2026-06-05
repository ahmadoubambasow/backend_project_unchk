package com.unchk.backend.meetings.repository;

import com.unchk.backend.meetings.entity.Meeting;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MeetingRepository
        extends JpaRepository<Meeting, Long> {

    List<Meeting>
    findByOrganizerId(Long organizerId);

    List<Meeting>
    findByGroupId(Long groupId);
}