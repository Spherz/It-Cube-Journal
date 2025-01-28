package com.itcube.journal.controller;

import com.google.gson.Gson;
import com.itcube.journal.model.Students;
import com.itcube.journal.service.AttendanceService;
import com.itcube.journal.service.GroupsService;
import com.itcube.journal.service.StudentsService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/attendance")
public class AttendanceController {

    private final AttendanceService attendanceService;
    private final GroupsService groupsService;
    private final StudentsService studentsService;

    @GetMapping
    public String attendanceList(Model model) {
        model.addAttribute("attendance", attendanceService.findAll());
        model.addAttribute("dates", attendanceService.findAll());
        model.addAttribute("marks", attendanceService.findAll());
        model.addAttribute("students", attendanceService.findAll());
        model.addAttribute("courses", attendanceService.findAll());
        return "attendance";
    }

    @ResponseBody
    @GetMapping("{id}")
    public String loadCoursesList(@PathVariable Integer id) {
        Gson gson = new Gson();
        return gson.toJson(groupsService.findByCourse(id));
    }

    @ResponseBody
    @GetMapping(value = "/students/{name}")
    public String loadStudentsByGroup(@PathVariable String name) {
        Gson gson = new Gson();
        return gson.toJson(studentsService.findByNameGroup(name));
    }

    @GetMapping("/mark")
    public String showMarkAttendanceForm(Model model) {
        List<Students> students = (List<Students>) studentsService.findAll();
        model.addAttribute("students", students);
        return "mark-attendance-form";
    }

    @PostMapping("/mark")
    public String markAttendance(@RequestParam("studentId") Long studentId,
                                 @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
                                @RequestParam("present") boolean present) {
        return null;
    }



//    TODO: Реализовать дома!!!
//    @ResponseBody
//    @GetMapping(value = "/students/studentsAttendance/{id}")
//    public String loadStudentsAttendance(@PathVariable Integer id) {
//        // Реализация загрузку посещаемости
////        Gson gson = new Gson();
////        return gson.toJson(studentsService.findByMark(id));
//    }

    // TODO: Добавить добавление занятий (дата, кого не было, тема занятия,
    //  (Тема и дата занятия при заполнении автоматически добавляются в тем. план))
}
