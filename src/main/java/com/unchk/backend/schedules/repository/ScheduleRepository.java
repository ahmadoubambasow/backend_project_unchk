package com.unchk.backend.schedules.repository;

import com.unchk.backend.schedules.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
}
