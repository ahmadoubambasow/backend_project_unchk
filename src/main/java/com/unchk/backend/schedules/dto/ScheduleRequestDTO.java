package com.unchk.backend.schedules.dto;

import com.unchk.backend.schedules.enums.SessionType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ScheduleRequestDTO {

    @NotBlank
    private String title;

    @NotNull
    private SessionType  sessionType;

    @NotNull
    private LocalDate date;

    @NotNull
    private LocalTime startTime;

    @NotNull
    private LocalTime endTime;

    private String room;

    @NotNull
    private Long formationId;

    @NotNull
    private Long trainerId;
}
