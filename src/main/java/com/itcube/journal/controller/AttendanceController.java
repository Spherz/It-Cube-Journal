package com.itcube.journal.controller;

import com.google.gson.Gson;
import com.itcube.journal.domain.Students;
import com.itcube.journal.repos.AttendanceDatesRepo;
import com.itcube.journal.repos.AttendanceRepo;
import com.itcube.journal.repos.StudentsRepo;
import com.itcube.journal.service.AttendanceService;
import com.itcube.journal.service.CourseService;
import com.itcube.journal.service.GroupsService;
import com.itcube.journal.service.StudentsService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/attendance")
public class AttendanceController {

    private AttendanceRepo attendanceRepo;


    private AttendanceDatesRepo attendanceDatesRepo;

    private AttendanceService attendanceService;


    private CourseService courseService;


    private GroupsService groupsService;


    private StudentsService studentsService;


    private StudentsRepo studentsRepo;

    public AttendanceController(AttendanceRepo attendanceRepo, AttendanceDatesRepo attendanceDatesRepo,
                                CourseService courseService, GroupsService groupsService,
                                StudentsService studentsService, StudentsRepo studentsRepo,
                                AttendanceService attendanceService) {
        this.attendanceRepo = attendanceRepo;
        this.attendanceDatesRepo = attendanceDatesRepo;
        this.courseService = courseService;
        this.groupsService = groupsService;
        this.studentsService = studentsService;
        this.studentsRepo = studentsRepo;
        this.attendanceService = attendanceService;
    }

    @GetMapping
    public String attendanceList(Model model) {
        model.addAttribute("attendance", attendanceRepo.findAll());
        model.addAttribute("dates", attendanceDatesRepo.findAll());
        model.addAttribute("marks", attendanceRepo.findAll());
        model.addAttribute("students", studentsRepo.findAll());
        model.addAttribute("courses", courseService.findAll());
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
        List<Students> students = (List<Students>) studentsRepo.findAll();
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
