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
public class MeetingResponseDTO {

    private Long id;

    private String title;

    private MeetingType type;

    private MeetingStatus status;

    private LocalDate meetingDate;

    private LocalTime startTime;

    private LocalTime endTime;

    private String description;

    private String report;

    private Long organizerId;

    private String organizerName;

    private Long groupId;

    private String groupName;

    private List<Long> participantIds;

    private List<String> participantNames;
}