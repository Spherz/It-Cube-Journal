package com.itcube.journal.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.itcube.journal.dto.attendance.AttendanceDTO;
import com.itcube.journal.model.Attendance;
import com.itcube.journal.model.Course;
import com.itcube.journal.model.Groups;
import com.itcube.journal.model.Schedule;
import com.itcube.journal.model.Students;
import com.itcube.journal.service.AttendanceDateService;
import com.itcube.journal.service.AttendanceService;
import com.itcube.journal.service.CourseService;
import com.itcube.journal.service.GroupsService;
import com.itcube.journal.service.StudentsService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping("/attendance")
public class AttendanceController {

    private final ObjectMapper objectMapper;
    private final GroupsService groupsService;
    private final StudentsService studentsService;
    private final AttendanceService attendanceService;
    private final AttendanceDateService attendanceDateService;
    private final CourseService courseService;

    @GetMapping
    public String getAttendance(
            @RequestParam(value = "courseId", required = false) Long courseId,
            @RequestParam(value = "groupId", required = false) Long groupId,
            Model model) {

        Iterable<Course> courses = courseService.findAll();
        model.addAttribute("courses", courses);
        model.addAttribute("selectedCourseId", courseId);

        if (courseId != null) {
            Iterable<Groups> groups = groupsService.findGroupsByCourseId(courseId);
            model.addAttribute("groups", groups);
            model.addAttribute("selectedGroupId", groupId);

            if (groupId != null) {
                Iterable<Attendance> attendanceList = attendanceService.findAll();
                Iterable<String> dates = attendanceDateService.getFormattedAttendanceDates();
                Iterable<Students> students = studentsService.findAll();

                Map<String, String> attendanceMap = new HashMap<>();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

                for (Attendance attendance : attendanceList) {
                    String key = attendance.getStudents().getId() + "-" + attendance.getAttendanceDate().format(formatter);
                    attendanceMap.put(key, attendance.getMark());
                }

                model.addAttribute("attendanceMap", attendanceMap);
                model.addAttribute("attendance", attendanceList);
                model.addAttribute("dates", dates);
                model.addAttribute("students", students);
            }
        }
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

//    @ResponseBody
    @GetMapping("/schedule/{groupId}")
    public String loadSchedule(@PathVariable Integer groupId, Model model) {
        Groups groups = groupsService.findById(groupId);
        List<Schedule> schedules = groups.getSchedules();
        model.addAttribute("groupId", groups);
        model.addAttribute("schedules", schedules);

        attendanceService.generateAttendanceDates(groups, LocalDate.of(2024, 9, 1).getYear());

        return "attendanceSchedule";
    }

    @PostMapping("/update")
    public ResponseEntity<?> updateAttendance(@RequestBody AttendanceDTO attendanceDTO) {
        Attendance attendance = attendanceService.findByStudentAndDate(attendanceDTO.getStudentId(), attendanceDTO.getAttendanceDate());

        if (attendance == null) {
            attendance = new Attendance();
            attendance.setStudents(studentsService.findById(attendanceDTO.getStudentId()));
            attendance.setAttendanceDate(attendanceDTO.getAttendanceDate());
        }

        attendance.setMark(attendanceDTO.getMark());
        attendanceService.save(attendance);
        return ResponseEntity.ok().build();
    }
}
