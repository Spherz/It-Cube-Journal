package com.itcube.journal.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.itcube.journal.service.CourseService;
import com.itcube.journal.service.StudentsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.Serializable;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/courses")
public class CourseController implements Serializable {

    private final CourseService courseService;
    private final StudentsService studentsService;
    private final ObjectMapper objectMapper;

    @GetMapping
    public String getCoursesList(Model model) {
        model.addAttribute("courses", courseService.findAll());
        return "courses";
    }

    @ResponseBody
    @GetMapping("{id}")
    public String loadStudentsByCourse(@PathVariable("id") Integer id) throws JsonProcessingException {
        log.info("Mapped json: {}", objectMapper.writeValueAsString(studentsService.findByCourse(id)));
        return objectMapper.writeValueAsString(studentsService.findByCourse(id));
    }

}
