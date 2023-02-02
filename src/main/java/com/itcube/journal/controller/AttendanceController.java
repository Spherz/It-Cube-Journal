package com.itcube.journal.controller;

import com.itcube.journal.domain.Attendance;
import com.itcube.journal.domain.AttendanceDates;
import com.itcube.journal.domain.Marks;
import com.itcube.journal.repos.AttendanceDatesRepo;
import com.itcube.journal.repos.AttendanceRepo;
import com.itcube.journal.repos.GroupsRepo;
import com.itcube.journal.repos.StudentsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

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
        Iterable<Attendance> studentsByGroup;

        if(filter != null && !filter.isEmpty()) {
            studentsByGroup = attendanceRepo.findByGroups_GroupName(filter);
            System.out.println(filter);
        } else {
            studentsByGroup = attendanceRepo.findAll();
        }

        model.addAttribute("attendance", studentsByGroup);
        model.addAttribute("dates", attendanceDatesRepo.findAll());
        model.addAttribute("studentMarks", Marks.values());
        model.addAttribute("filterStudentsByGroup", filter);

        return "attendance";
    }

    @GetMapping("{attendance}")
    public String attendanceEditForm(@PathVariable Attendance attendance, Model model) {
        Iterable<AttendanceDates> dates = attendanceDatesRepo.findAll();
        Iterable<Attendance> attendanceMarks = attendanceRepo.findAll();

        model.addAttribute("attendance", attendance);
        model.addAttribute("attendanceMarks", attendanceMarks);
        model.addAttribute("marks", Marks.values());
        model.addAttribute("dates", dates);

        return "attendanceEdit";
    }

//    @PostMapping
//    public String attendanceSave(
//            @RequestParam Map<String, String> form,
//            @RequestParam("dateId") AttendanceDates date,
//            @RequestParam("attendanceId") Attendance attendance)
//    {
////        attendance.setDate(date);
//        System.out.println(attendance.getDate());
//
//        Set<String> marks = Arrays.stream(Marks.values())
//                .map(Marks::name)
//                .collect(Collectors.toSet());
//
//        attendance.getMark().clear();
//
//        for(String key : form.keySet()) {
//            if(marks.contains(key)) {
//                attendance.getMark().add(Marks.valueOf(key));
//            }
//        }
//
//        attendanceRepo.save(attendance);
//
//        return "redirect:/attendance";
//    }
}
