package com.unchk.backend.schedule.repository;

import com.unchk.backend.schedule.entity.Schedule;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;

public interface ScheduleRepository
        extends JpaRepository<Schedule, Long> {

    List<Schedule>
    findByGroupId(
            Long groupId
    );

    List<Schedule>
    findByTrainerId(
            Long trainerId
    );

    List<Schedule>
    findByDayOfWeek(
            DayOfWeek dayOfWeek
    );

    boolean existsByGroupIdAndDayOfWeekAndStartTimeLessThanAndEndTimeGreaterThan(
            Long groupId,
            DayOfWeek dayOfWeek,
            LocalTime endTime,
            LocalTime startTime
    );

    boolean existsByTrainerIdAndDayOfWeekAndStartTimeLessThanAndEndTimeGreaterThan(
            Long trainerId,
            DayOfWeek dayOfWeek,
            LocalTime endTime,
            LocalTime startTime
    );

    boolean existsByRoomAndDayOfWeekAndStartTimeLessThanAndEndTimeGreaterThan(
            String room,
            DayOfWeek dayOfWeek,
            LocalTime endTime,
            LocalTime startTime
    );

    List<Schedule>
    findByGroupIdAndDayOfWeek(
            Long groupId,
            DayOfWeek dayOfWeek
    );

    List<Schedule>
    findByTrainerIdAndDayOfWeek(
            Long trainerId,
            DayOfWeek dayOfWeek
    );

    List<Schedule>
    findByRoomAndDayOfWeek(
            String room,
            DayOfWeek dayOfWeek
    );

    
}