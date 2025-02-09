package com.itcube.journal.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.itcube.journal.model.Groups;
import com.itcube.journal.model.Schedule;
import com.itcube.journal.service.AttendanceService;
import com.itcube.journal.service.CourseService;
import com.itcube.journal.service.GroupsService;
import com.itcube.journal.service.StudentsService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/attendance")
public class AttendanceController {

    private final ObjectMapper objectMapper;
    private final GroupsService groupsService;
    private final StudentsService studentsService;
    private final AttendanceService attendanceService;
    private final CourseService courseService;

    @GetMapping
    public String attendanceList(Model model) {
        model.addAttribute("attendance", attendanceService.findAll());
        model.addAttribute("dates", attendanceService.findAll());
        model.addAttribute("marks", attendanceService.findAll());
        model.addAttribute("students", studentsService.findAll());
        model.addAttribute("courses", courseService.findAll());
        return "attendance";
    }

    @ResponseBody
    @GetMapping("{id}")
    public String loadCoursesList(@PathVariable Integer id) throws JsonProcessingException {
        return objectMapper.writeValueAsString(groupsService.findByCourse(id));
    }

    @ResponseBody
    @GetMapping(value = "/students/{name}")
    public String loadStudentsByGroup(@PathVariable String name) throws JsonProcessingException {
        return objectMapper.writeValueAsString(studentsService.findByGroupName(name));
    }

    @ResponseBody
    @GetMapping("/schedule/{groupId}")
    public String loadSchedule(@PathVariable Integer groupId, Model model) {
        Groups groups = groupsService.findById(groupId);
        List<Schedule> schedules = groups.getSchedules();
        model.addAttribute("groupId", groups);
        model.addAttribute("schedules", schedules);

        attendanceService.generateAttendanceDates(groups, LocalDate.now().getYear());

        return "attendanceSchedule";
    }

    @PostMapping("/mark")
    public String markAttendance(@RequestParam("studentId") Long studentId,
                                 @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
                                @RequestParam("present") boolean present) {
        return null;
    }

    // TODO: Добавить добавление занятий (дата, кого не было, тема занятия,
    //  (Тема и дата занятия при заполнении автоматически добавляются в тем. план))
}
