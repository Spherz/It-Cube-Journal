package com.itcube.journal.controller;

import com.google.gson.Gson;
import com.itcube.journal.repos.AttendanceDatesRepo;
import com.itcube.journal.repos.AttendanceRepo;
import com.itcube.journal.repos.GroupsRepo;
import com.itcube.journal.repos.StudentsRepo;
import com.itcube.journal.service.CourseService;
import com.itcube.journal.service.GroupsService;
import com.itcube.journal.service.StudentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/attendance")
public class AttendanceController {

    @Autowired
    private AttendanceRepo attendanceRepo;

    @Autowired
    private GroupsRepo groupsRepo;

    @Autowired
    private AttendanceDatesRepo attendanceDatesRepo;

    @Autowired
    private CourseService courseService;

    @Autowired
    private GroupsService groupsService;

    @Autowired
    private StudentsService studentsService;

    @Autowired
    private StudentsRepo studentsRepo;

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
}
