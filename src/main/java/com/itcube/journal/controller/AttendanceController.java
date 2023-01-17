package com.itcube.journal.controller;

import com.itcube.journal.domain.Groups;
import com.itcube.journal.domain.Marks;
import com.itcube.journal.domain.Students;
import com.itcube.journal.repos.AttendanceDatesRepo;
import com.itcube.journal.repos.AttendanceRepo;
import com.itcube.journal.repos.GroupsRepo;
import com.itcube.journal.repos.StudentsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

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
    private StudentsRepo studentsRepo;

    @GetMapping
    public String attendanceList(Model model, @RequestParam(required = false, defaultValue = "") String filter) {
        Iterable<Students> studentsByGroup;

        if(filter != null && !filter.isEmpty()) {
            studentsByGroup = studentsRepo.findByNameGroup_GroupName(filter);
        } else {
            studentsByGroup = studentsRepo.findAll();
        }

        model.addAttribute("attendance",attendanceRepo.findAll());
        model.addAttribute("dates", attendanceDatesRepo.findAll());
        model.addAttribute("studentMarks", Marks.values());
//        model.addAttribute("groups",groupsRepo.findAll());
        model.addAttribute("students", studentsByGroup);
        model.addAttribute("filterStudentsByGroup", filter);

        return "attendance";
    }

}
