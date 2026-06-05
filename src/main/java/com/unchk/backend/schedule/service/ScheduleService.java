package com.unchk.backend.schedule.service;

import com.unchk.backend.students.entity.StudentGroup;
import com.unchk.backend.students.repository.StudentGroupRepository;
import com.unchk.backend.formations.entity.TrainingModule;
import com.unchk.backend.formations.repository.TrainingModuleRepository;
import com.unchk.backend.schedule.dto.ScheduleRequestDTO;
import com.unchk.backend.schedule.dto.ScheduleResponseDTO;
import com.unchk.backend.schedule.entity.Schedule;
import com.unchk.backend.schedule.repository.ScheduleRepository;
import com.unchk.backend.users.entity.User;
import com.unchk.backend.users.repository.UserRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleRepository
            scheduleRepository;

    private final StudentGroupRepository
            studentGroupRepository;

    private final TrainingModuleRepository
            trainingModuleRepository;

    private final UserRepository
            userRepository;

    /**
     * Création créneau
     */
    public ScheduleResponseDTO
    createSchedule(

            ScheduleRequestDTO request

    ) {

        validateConflicts(request);

        StudentGroup group =

                studentGroupRepository

                        .findById(
                                request.getGroupId()
                        )

                        .orElseThrow(() ->

                                new RuntimeException(
                                        "Groupe introuvable"
                                )
                        );

        TrainingModule module =

                trainingModuleRepository

                        .findById(
                                request.getTrainingModuleId()
                        )

                        .orElseThrow(() ->

                                new RuntimeException(
                                        "Module introuvable"
                                )
                        );

        User trainer =

                userRepository

                        .findById(
                                request.getTrainerId()
                        )

                        .orElseThrow(() ->

                                new RuntimeException(
                                        "Formateur introuvable"
                                )
                        );

        Schedule schedule =

                Schedule.builder()

                        .group(group)

                        .trainingModule(module)

                        .trainer(trainer)

                        .dayOfWeek(
                                request.getDayOfWeek()
                        )

                        .startTime(
                                request.getStartTime()
                        )

                        .endTime(
                                request.getEndTime()
                        )

                        .room(
                                request.getRoom()
                        )

                        .color(
                                request.getColor()
                        )

                        .build();

        schedule = scheduleRepository.save(
                schedule
        );

        return mapToResponse(
                schedule
        );
    }

    public ScheduleResponseDTO
    updateSchedule(

            Long id,

            ScheduleRequestDTO request

    ) {

        Schedule schedule =

                scheduleRepository

                        .findById(id)

                        .orElseThrow(() ->

                                new RuntimeException(
                                        "Créneau introuvable"
                                )
                        );

        validateUpdateConflicts(
                id,
                request
        );

        StudentGroup group =

                studentGroupRepository

                        .findById(
                                request.getGroupId()
                        )

                        .orElseThrow(() ->

                                new RuntimeException(
                                        "Groupe introuvable"
                                )
                        );

        TrainingModule module =

                trainingModuleRepository

                        .findById(
                                request.getTrainingModuleId()
                        )

                        .orElseThrow(() ->

                                new RuntimeException(
                                        "Module introuvable"
                                )
                        );

        User trainer =

                userRepository

                        .findById(
                                request.getTrainerId()
                        )

                        .orElseThrow(() ->

                                new RuntimeException(
                                        "Formateur introuvable"
                                )
                        );

        schedule.setGroup(
                group
        );

        schedule.setTrainingModule(
                module
        );

        schedule.setTrainer(
                trainer
        );

        schedule.setDayOfWeek(
                request.getDayOfWeek()
        );

        schedule.setStartTime(
                request.getStartTime()
        );

        schedule.setEndTime(
                request.getEndTime()
        );

        schedule.setRoom(
                request.getRoom()
        );

        schedule.setColor(
                request.getColor()
        );

        schedule = scheduleRepository.save(
                schedule
        );

        return mapToResponse(
                schedule
        );
    }

    /**
     * Liste complète
     */
    public List<ScheduleResponseDTO>
    getAllSchedules() {

        return scheduleRepository

                .findAll()

                .stream()

                .map(
                        this::mapToResponse
                )

                .toList();
    }

    /**
     * EDT Groupe
     */
    public List<ScheduleResponseDTO>
    getGroupSchedules(

            Long groupId

    ) {

        return scheduleRepository

                .findByGroupId(
                        groupId
                )

                .stream()

                .map(
                        this::mapToResponse
                )

                .toList();
    }

    /**
     * EDT Enseignant
     */
    public List<ScheduleResponseDTO>
    getTrainerSchedules(

            Long trainerId

    ) {

        return scheduleRepository

                .findByTrainerId(
                        trainerId
                )

                .stream()

                .map(
                        this::mapToResponse
                )

                .toList();
    }

    /**
     * Détail
     */
    public ScheduleResponseDTO
    getScheduleById(

            Long id

    ) {

        Schedule schedule =

                scheduleRepository

                        .findById(id)

                        .orElseThrow(() ->

                                new RuntimeException(
                                        "Créneau introuvable"
                                )
                        );

        return mapToResponse(
                schedule
        );
    }

    /**
     * Suppression
     */
    public void deleteSchedule(

            Long id

    ) {

        scheduleRepository.deleteById(
                id
        );
    }

    /**
     * Validation conflits
     */
    private void validateConflicts(

            ScheduleRequestDTO request

    ) {

        boolean groupConflict =

                scheduleRepository

                        .existsByGroupIdAndDayOfWeekAndStartTimeLessThanAndEndTimeGreaterThan(

                                request.getGroupId(),

                                request.getDayOfWeek(),

                                request.getEndTime(),

                                request.getStartTime()
                        );

        if (groupConflict) {

            throw new RuntimeException(
                    "Conflit sur le groupe"
            );
        }

        boolean trainerConflict =

                scheduleRepository

                        .existsByTrainerIdAndDayOfWeekAndStartTimeLessThanAndEndTimeGreaterThan(

                                request.getTrainerId(),

                                request.getDayOfWeek(),

                                request.getEndTime(),

                                request.getStartTime()
                        );

        if (trainerConflict) {

            throw new RuntimeException(
                    "Conflit sur le formateur"
            );
        }

        boolean roomConflict =

                scheduleRepository

                        .existsByRoomAndDayOfWeekAndStartTimeLessThanAndEndTimeGreaterThan(

                                request.getRoom(),

                                request.getDayOfWeek(),

                                request.getEndTime(),

                                request.getStartTime()
                        );

        if (roomConflict) {

            throw new RuntimeException(
                    "Salle déjà occupée"
            );
        }
    }

    private void validateUpdateConflicts(

            Long scheduleId,

            ScheduleRequestDTO request

    ) {

        List<Schedule> groupSchedules =

                scheduleRepository

                        .findByGroupIdAndDayOfWeek(

                                request.getGroupId(),

                                request.getDayOfWeek()
                        );

        for (Schedule schedule : groupSchedules) {

            if (

                    !schedule.getId()
                            .equals(scheduleId)

                            &&

                            request.getStartTime()
                                    .isBefore(
                                            schedule.getEndTime()
                                    )

                            &&

                            request.getEndTime()
                                    .isAfter(
                                            schedule.getStartTime()
                                    )
            ) {

                throw new RuntimeException(
                        "Conflit sur le groupe"
                );
            }
        }

        List<Schedule> trainerSchedules =

                scheduleRepository

                        .findByTrainerIdAndDayOfWeek(

                                request.getTrainerId(),

                                request.getDayOfWeek()
                        );

        for (Schedule schedule : trainerSchedules) {

            if (

                    !schedule.getId()
                            .equals(scheduleId)

                            &&

                            request.getStartTime()
                                    .isBefore(
                                            schedule.getEndTime()
                                    )

                            &&

                            request.getEndTime()
                                    .isAfter(
                                            schedule.getStartTime()
                                    )
            ) {

                throw new RuntimeException(
                        "Conflit sur le formateur"
                );
            }
        }

        List<Schedule> roomSchedules =

                scheduleRepository

                        .findByRoomAndDayOfWeek(

                                request.getRoom(),

                                request.getDayOfWeek()
                        );

        for (Schedule schedule : roomSchedules) {

            if (

                    !schedule.getId()
                            .equals(scheduleId)

                            &&

                            request.getStartTime()
                                    .isBefore(
                                            schedule.getEndTime()
                                    )

                            &&

                            request.getEndTime()
                                    .isAfter(
                                            schedule.getStartTime()
                                    )
            ) {

                throw new RuntimeException(
                        "Salle déjà occupée"
                );
            }
        }
    }

    public List<ScheduleResponseDTO>
    getMySchedules() {

        String email =

                SecurityContextHolder

                        .getContext()

                        .getAuthentication()

                        .getName();

        System.out.println(
                "\n========== MY SCHEDULE =========="
        );

        System.out.println(
                "EMAIL CONNECTE = " + email
        );

        User user =

                userRepository

                        .findByEmail(email)

                        .orElseThrow(() ->

                                new RuntimeException(
                                        "Utilisateur introuvable"
                                )
                        );

        String role =

                user.getRole()
                        .getName();

        System.out.println(
                "USER ID = " + user.getId()
        );

        System.out.println(
                "ROLE = " + role
        );

        if (

                role.equals("ADMIN")

                        ||

                        role.equals("RESPONSABLE_FORMATION")

        ) {

            System.out.println(
                    "ADMIN/RESPONSABLE -> TOUS LES CRENEAUX"
            );

            return getAllSchedules();
        }

        List<Schedule> schedules =

                scheduleRepository

                        .findByTrainerId(
                                user.getId()
                        );

        System.out.println(
                "NB SCHEDULES TROUVES = "
                        + schedules.size()
        );

        for (Schedule schedule : schedules) {

            System.out.println(
                    "SCHEDULE ID = "
                            + schedule.getId()
            );

            System.out.println(
                    "TRAINER ID = "
                            + schedule.getTrainer().getId()
            );

            System.out.println(
                    "TRAINER NAME = "
                            + schedule.getTrainer().getFullName()
            );

            System.out.println(
                    "MODULE = "
                            + schedule.getTrainingModule().getTitle()
            );

            System.out.println(
                    "--------------------"
            );
        }

        return schedules

                .stream()

                .map(
                        this::mapToResponse
                )

                .toList();
    }

    /**
     * Mapping DTO
     */
    private ScheduleResponseDTO
    mapToResponse(

            Schedule schedule

    ) {

        return ScheduleResponseDTO

                .builder()

                .id(
                        schedule.getId()
                )

                .groupId(
                        schedule.getGroup().getId()
                )

                .groupName(
                        schedule.getGroup().getName()
                )

                .moduleId(
                        schedule.getTrainingModule().getId()
                )

                .moduleName(
                        schedule.getTrainingModule().getTitle()
                )

                .trainerId(
                        schedule.getTrainer().getId()
                )

                .trainerName(
                        schedule.getTrainer().getFullName()
                )

                .dayOfWeek(
                        schedule.getDayOfWeek()
                )

                .startTime(
                        schedule.getStartTime()
                )

                .endTime(
                        schedule.getEndTime()
                )

                .room(
                        schedule.getRoom()
                )

                .color(
                        schedule.getColor()
                )

                .build();
    }
}