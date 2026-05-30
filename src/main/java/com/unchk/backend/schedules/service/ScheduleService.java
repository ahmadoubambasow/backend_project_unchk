package com.unchk.backend.schedules.service;

import com.unchk.backend.formations.entity.Formation;
import com.unchk.backend.formations.repository.FormationRepository;
import com.unchk.backend.schedules.dto.ScheduleRequestDTO;
import com.unchk.backend.schedules.dto.ScheduleResponseDTO;
import com.unchk.backend.schedules.entity.Schedule;
import com.unchk.backend.schedules.enums.SessionType;
import com.unchk.backend.schedules.repository.ScheduleRepository;
import com.unchk.backend.trainers.entity.Trainer;
import com.unchk.backend.trainers.repository.TrainerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    private final FormationRepository formationRepository;

    private final TrainerRepository trainerRepository;

    /**
     * Création séance
     */
    public ScheduleResponseDTO createSchedule(ScheduleRequestDTO request) {

        Formation formation = formationRepository.findById(request.getFormationId())
                .orElseThrow(() -> new RuntimeException("Formation id not found"));

        Trainer trainer = trainerRepository.findById(request.getTrainerId())
                .orElseThrow(() -> new RuntimeException("Trainer id not found"));

        Schedule schedule = Schedule.builder()
                .title(request.getTitle())
                .sessionType(request.getSessionType())
                .date(request.getDate())
                .startTime(request.getStartTime())
                .endTime(request.getEndTime())
                .room(request.getRoom())
                .formation(formation)
                .trainer(trainer)

                .build();

        if(request.getStartTime().isAfter(request.getEndTime())) {

            throw new RuntimeException("L'heure de début doit etre inférieure à l'heure de fin");
        }

        Schedule savedSchedule = scheduleRepository.save(schedule);

        return mapToResponse(savedSchedule);
    }

    /**
     * Liste séances
     */
    public List<ScheduleResponseDTO> getAllSchedules() {

        return scheduleRepository.findAll()

                .stream()

                .map(this::mapToResponse)

                .toList();
    }

    /**
     * Mise à jour séance
     */
    public ScheduleResponseDTO updateSchedule(Long id, ScheduleRequestDTO request) {

        Schedule schedule = scheduleRepository.findById(id)
                .orElseThrow(() ->  new RuntimeException("Séance introuvable"));

        Formation formation = formationRepository.findById(request.getFormationId())

                .orElseThrow(() -> new RuntimeException("Formation id not found"));

        Trainer trainer = trainerRepository.findById(request.getTrainerId())
                .orElseThrow(() -> new RuntimeException("Trainer id not found"));

        schedule.setTitle(request.getTitle());
        schedule.setSessionType(request.getSessionType());
        schedule.setDate(request.getDate());
        schedule.setStartTime(request.getStartTime());
        schedule.setEndTime(request.getEndTime());
        schedule.setRoom(request.getRoom());
        schedule.setFormation(formation);
        schedule.setTrainer(trainer);

        if(request.getStartTime().isAfter(request.getEndTime())) {

            throw new RuntimeException("L'heure de début doit etre inférieure à l'heure de fin");
        }

        Schedule updatedSchedule = scheduleRepository.save(schedule);

        return mapToResponse(updatedSchedule);
    }

    /**
     * Suppression séance
     */
    public void deleteSchedule(Long id) {

        Schedule schedule = scheduleRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Séance introuvable")
                        );

        scheduleRepository.delete(schedule);
    }

    /**
     * Mapping Entity -> DTO
     */
    private ScheduleResponseDTO mapToResponse(Schedule schedule) {

        return ScheduleResponseDTO.builder()

                .id(schedule.getId())
                .title(schedule.getTitle())
                .sessionType(schedule.getSessionType())
                .date(schedule.getDate())
                .startTime(schedule.getStartTime())
                .endTime(schedule.getEndTime())
                .room(schedule.getRoom())
                .formationId(schedule.getFormation().getId())
                .formationName(schedule.getFormation().getName())
                .trainerId(schedule.getTrainer().getId())
                .trainerName(
                        schedule.getTrainer().getFirstName()
                        + " "
                        + schedule.getTrainer().getLastName()
                )

                .build();
    }
}
