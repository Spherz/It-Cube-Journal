package com.itcube.journal.service;

import com.itcube.journal.dto.schedule.ScheduleExceptionRequestDTO;
import com.itcube.journal.dto.schedule.ScheduleRequestDTO;
import com.itcube.journal.dto.schedule.ScheduleResponseDTO;

import java.time.LocalDate;
import java.util.List;

public interface ScheduleService {

    List<ScheduleResponseDTO> findAllSchedules();

    ScheduleResponseDTO findScheduleById(Long id);

    List<ScheduleResponseDTO> findSchedulesByGroupId(Long groupId);

    ScheduleResponseDTO createSchedule(ScheduleRequestDTO scheduleRequestDTO);

    ScheduleResponseDTO updateSchedule(Long id, ScheduleRequestDTO scheduleRequestDTO);

    void deleteSchedule(Long id);

    List<LocalDate> getLessonDates(Long id);

    List<LocalDate> getLessonDatesByGroupId(Long groupId);

    ScheduleResponseDTO cancelLesson(Long id, ScheduleExceptionRequestDTO scheduleExceptionRequestDTO);

    ScheduleResponseDTO restoreLesson(Long id, Long exceptionId);
}
