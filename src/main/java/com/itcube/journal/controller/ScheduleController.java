package com.itcube.journal.controller;

import com.itcube.journal.dto.schedule.ScheduleExceptionRequestDTO;
import com.itcube.journal.dto.schedule.ScheduleRequestDTO;
import com.itcube.journal.dto.schedule.ScheduleResponseDTO;
import com.itcube.journal.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/schedules")
public class ScheduleController {

    private final ScheduleService scheduleService;

    @GetMapping
    public ResponseEntity<List<ScheduleResponseDTO>> getAllSchedules() {
        return ResponseEntity.ok(scheduleService.findAllSchedules());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ScheduleResponseDTO> getScheduleById(@PathVariable Long id) {
        return ResponseEntity.ok(scheduleService.findScheduleById(id));
    }

    @GetMapping("/group/{groupId}")
    public ResponseEntity<List<ScheduleResponseDTO>> getSchedulesByGroupId(@PathVariable Long groupId) {
        return ResponseEntity.ok(scheduleService.findSchedulesByGroupId(groupId));
    }

    @PostMapping
    public ResponseEntity<ScheduleResponseDTO> createSchedule(
            @RequestBody ScheduleRequestDTO scheduleRequestDTO) {
        return ResponseEntity.ok(scheduleService.createSchedule(scheduleRequestDTO));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ScheduleResponseDTO> updateSchedule(
            @PathVariable Long id,
            @RequestBody ScheduleRequestDTO scheduleRequestDTO) {
        return ResponseEntity.ok(scheduleService.updateSchedule(id, scheduleRequestDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSchedule(@PathVariable Long id) {
        scheduleService.deleteSchedule(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/lesson-dates")
    public ResponseEntity<List<LocalDate>> getLessonDates(@PathVariable Long id) {
        return ResponseEntity.ok(scheduleService.getLessonDates(id));
    }

    @PostMapping("/{id}/exceptions")
    public ResponseEntity<ScheduleResponseDTO> cancelLesson(
            @PathVariable Long id,
            @RequestBody ScheduleExceptionRequestDTO scheduleExceptionRequestDTO) {
        return ResponseEntity.ok(scheduleService.cancelLesson(id, scheduleExceptionRequestDTO));
    }

    @DeleteMapping("/{id}/exceptions/{exceptionId}")
    public ResponseEntity<ScheduleResponseDTO> restoreLesson(
            @PathVariable Long id,
            @PathVariable Long exceptionId) {
        return ResponseEntity.ok(scheduleService.restoreLesson(id, exceptionId));
    }
}
