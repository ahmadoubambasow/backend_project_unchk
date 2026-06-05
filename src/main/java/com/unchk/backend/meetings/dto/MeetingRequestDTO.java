package com.unchk.backend.meetings.dto;

import com.unchk.backend.meetings.entity.MeetingStatus;
import com.unchk.backend.meetings.entity.MeetingType;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MeetingRequestDTO {

    private String title;

    private MeetingType type;

    private MeetingStatus status;

    private LocalDate meetingDate;

    private LocalTime startTime;

    private LocalTime endTime;

    private String description;

    private String report;

    private Long organizerId;

    private Long groupId;

    private List<Long> participantIds;
}