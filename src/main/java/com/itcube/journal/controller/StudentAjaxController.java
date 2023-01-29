package com.itcube.journal.controller;

import com.itcube.journal.domain.Students;
import com.itcube.journal.repos.StudentsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class StudentAjaxController {
    @Autowired
    private StudentsRepo studentsRepo;

    @GetMapping("/get_students")
    public List<Students> getStudents() {
        List<Students> studentsList = new ArrayList<>();

        studentsRepo.findAll().forEach(studentsList::add);

        return studentsList;
    }
}
