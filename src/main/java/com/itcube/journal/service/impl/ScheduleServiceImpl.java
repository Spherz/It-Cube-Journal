package com.itcube.journal.service.impl;

import com.itcube.journal.dto.schedule.ScheduleExceptionRequestDTO;
import com.itcube.journal.dto.schedule.ScheduleRequestDTO;
import com.itcube.journal.dto.schedule.ScheduleResponseDTO;
import com.itcube.journal.exceptions.GroupNotFoundException;
import com.itcube.journal.exceptions.InvalidScheduleDateException;
import com.itcube.journal.exceptions.ScheduleExceptionNotFoundException;
import com.itcube.journal.exceptions.ScheduleNotFoundException;
import com.itcube.journal.mapper.schedule.ScheduleMapper;
import com.itcube.journal.model.Group;
import com.itcube.journal.model.Schedule;
import com.itcube.journal.model.ScheduleException;
import com.itcube.journal.repository.GroupRepository;
import com.itcube.journal.repository.ScheduleRepository;
import com.itcube.journal.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ScheduleServiceImpl implements ScheduleService {

    private final ScheduleMapper scheduleMapper;
    private final ScheduleRepository scheduleRepository;
    private final GroupRepository groupRepository;

    @Override
    public List<ScheduleResponseDTO> findAllSchedules() {
        return scheduleRepository.findAll().stream()
                .map(scheduleMapper::toResponseDTO)
                .toList();
    }

    @Override
    public ScheduleResponseDTO findScheduleById(Long id) {
        Schedule schedule = getScheduleOrThrow(id);

        return scheduleMapper.toResponseDTO(schedule);
    }

    @Override
    public List<ScheduleResponseDTO> findSchedulesByGroupId(Long groupId) {
        return scheduleRepository.findByGroupId(groupId).stream()
                .map(scheduleMapper::toResponseDTO)
                .toList();
    }

    @Override
    public ScheduleResponseDTO createSchedule(ScheduleRequestDTO scheduleRequestDTO) {
        Schedule schedule = scheduleMapper.toEntity(scheduleRequestDTO);
        schedule.setGroup(resolveGroup(scheduleRequestDTO.groupId()));

        Schedule savedSchedule = scheduleRepository.save(schedule);

        return scheduleMapper.toResponseDTO(savedSchedule);
    }

    @Override
    public ScheduleResponseDTO updateSchedule(Long id, ScheduleRequestDTO scheduleRequestDTO) {
        Schedule schedule = getScheduleOrThrow(id);

        scheduleMapper.updateFromDTO(scheduleRequestDTO, schedule);
        schedule.setGroup(resolveGroup(scheduleRequestDTO.groupId()));

        Schedule updatedSchedule = scheduleRepository.save(schedule);

        return scheduleMapper.toResponseDTO(updatedSchedule);
    }

    @Override
    public void deleteSchedule(Long id) {
        Schedule schedule = getScheduleOrThrow(id);

        scheduleRepository.delete(schedule);
    }

    @Override
    public List<LocalDate> getLessonDates(Long id) {
        Schedule schedule = getScheduleOrThrow(id);

        return computeLessonDates(schedule);
    }

    @Override
    public List<LocalDate> getLessonDatesByGroupId(Long groupId) {
        return scheduleRepository.findByGroupId(groupId).stream()
                .flatMap(schedule -> computeLessonDates(schedule).stream())
                .distinct()
                .sorted()
                .toList();
    }

    @Override
    public ScheduleResponseDTO cancelLesson(Long id, ScheduleExceptionRequestDTO scheduleExceptionRequestDTO) {
        Schedule schedule = getScheduleOrThrow(id);

        LocalDate exceptionDate = scheduleExceptionRequestDTO.exceptionDate();
        if (!computeLessonDates(schedule).contains(exceptionDate)) {
            throw new InvalidScheduleDateException(
                    "Date " + exceptionDate + " is not a scheduled lesson date for schedule with id: " + id);
        }

        ScheduleException scheduleException = new ScheduleException();
        scheduleException.setExceptionDate(exceptionDate);
        scheduleException.setReason(scheduleExceptionRequestDTO.reason());
        scheduleException.setSchedule(schedule);
        schedule.getExceptions().add(scheduleException);

        Schedule savedSchedule = scheduleRepository.save(schedule);

        return scheduleMapper.toResponseDTO(savedSchedule);
    }

    @Override
    public ScheduleResponseDTO restoreLesson(Long id, Long exceptionId) {
        Schedule schedule = getScheduleOrThrow(id);

        ScheduleException exceptionToRemove = schedule.getExceptions().stream()
                .filter(exception -> exception.getId().equals(exceptionId))
                .findFirst()
                .orElseThrow(() -> new ScheduleExceptionNotFoundException(
                        "Unable to find schedule exception with id: " + exceptionId));
        schedule.getExceptions().remove(exceptionToRemove);

        Schedule savedSchedule = scheduleRepository.save(schedule);

        return scheduleMapper.toResponseDTO(savedSchedule);
    }

    private Schedule getScheduleOrThrow(Long id) {
        return scheduleRepository.findById(id)
                .orElseThrow(() -> new ScheduleNotFoundException("Unable to find schedule with id: " + id));
    }

    private Group resolveGroup(Long groupId) {
        return groupRepository.findById(groupId)
                .orElseThrow(() -> new GroupNotFoundException("Unable to find group with id: " + groupId));
    }

    private List<LocalDate> computeLessonDates(Schedule schedule) {
        Set<LocalDate> excludedDates = schedule.getExceptions().stream()
                .map(ScheduleException::getExceptionDate)
                .collect(Collectors.toSet());

        List<LocalDate> lessonDates = new ArrayList<>();
        LocalDate current = schedule.getAcademicYearStart();
        LocalDate end = schedule.getAcademicYearEnd();
        while (!current.isAfter(end)) {
            if (schedule.getDaysOfWeek().contains(current.getDayOfWeek()) && !excludedDates.contains(current)) {
                lessonDates.add(current);
            }
            current = current.plusDays(1);
        }

        return lessonDates;
    }
}
