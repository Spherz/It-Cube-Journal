package com.itcube.journal.controller;

import com.google.gson.Gson;
import com.itcube.journal.service.CourseService;
import com.itcube.journal.service.StudentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;

@Controller
@RequestMapping("/courses")
public class CourseController implements Serializable {

    @Autowired
    private CourseService courseService;

    @Autowired
    private StudentsService studentsService;

    @GetMapping
    public String getCoursesList(Model model) {
        model.addAttribute("courses", courseService.findAll());
        return "courses";
    }

    @ResponseBody
    @GetMapping("{id}")
    public String loadStudentsByCourse(@PathVariable("id") Integer id) {
        Gson gson = new Gson();
        System.out.println(gson.toJson(studentsService.findByCourse(id)));
        return gson.toJson(studentsService.findByCourse(id));
    }

}
