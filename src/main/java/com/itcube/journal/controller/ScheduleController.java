package com.itcube.journal.controller;

import com.itcube.journal.service.AttendanceService;
import com.itcube.journal.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/schedule")
public class ScheduleController {

    private final ScheduleService scheduleService;
    private final AttendanceService attendanceService;

    @GetMapping
    public String getSchedule(Model model) {
        model.addAttribute("schedules", scheduleService.getAll());
        return "attendanceSchedule";
    }
}
