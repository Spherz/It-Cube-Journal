package com.itcube.journal.controller;

import com.google.gson.Gson;
import com.itcube.journal.service.CourseService;
import com.itcube.journal.service.StudentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/courses")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @Autowired
    private StudentsService studentsService;

    @GetMapping
    public String getCoursesList(ModelMap modelMap) {
        modelMap.put("courses", courseService.findAll());
        return "courses";
    }

    @ResponseBody
    @GetMapping("{id}")
    public String loadStudentsByCourse(@PathVariable("id") Integer id) {
        Gson gson = new Gson();
        return gson.toJson(studentsService.findByCourse(id));
    }

}
