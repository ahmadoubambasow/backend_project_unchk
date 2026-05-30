package com.unchk.backend.schedules.dto;

import com.unchk.backend.schedules.enums.SessionType;
import  lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ScheduleResponseDTO {

    private Long id;

    private String title;

    private SessionType sessionType;

    private LocalDate date;

    private LocalTime startTime;

    private LocalTime endTime;

    private String room;

    private Long formationId;

    private String formationName;

    private Long trainerId;

    private String trainerName;
}
