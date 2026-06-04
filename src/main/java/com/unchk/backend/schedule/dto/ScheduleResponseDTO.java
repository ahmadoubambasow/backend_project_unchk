package com.unchk.backend.schedule.dto;

import lombok.*;

import java.time.DayOfWeek;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ScheduleResponseDTO {

    private Long id;

    private Long groupId;

    private String groupName;

    private Long moduleId;

    private String moduleName;

    private Long trainerId;

    private String trainerName;

    private DayOfWeek dayOfWeek;

    private LocalTime startTime;

    private LocalTime endTime;

    private String room;

    private String color;
}