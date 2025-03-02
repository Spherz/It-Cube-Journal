package com.itcube.journal.controller;

import com.itcube.journal.dto.students.StudentsRequestDTO;
import com.itcube.journal.model.Students;
import com.itcube.journal.service.StudentsService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
@RequestMapping("/students")
public class StudentsController {

    private final StudentsService studentsService;

    @GetMapping
    public String studentsList(@RequestParam(required = false, defaultValue = "") String filter,
                               Model model, @PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable) {

        Page<Students> page = filter.isEmpty()
                ? studentsService.findAll(pageable)
                : studentsService.searchByKeyword(filter, pageable);

        model.addAttribute("filterStudents", filter);
        model.addAttribute("page", page);
        model.addAttribute("url", "/students");
        model.addAttribute("employees", studentsService.findAll(pageable));

        return "students";
    }

    @GetMapping("/students/get_select_value")
    public String getValueFromDropDown(@RequestParam("groupsList") String selectValue) {
        System.out.println(selectValue);

        return "students";
    }

    @GetMapping("/{studentId}")
    public String studentUpdateForm(@PathVariable Integer studentId, Model model) {
        model.addAttribute("student", studentsService.findById(studentId));
        return "studentsEdit";
    }

    @PostMapping("/update/{studentId}")
    public String updateStudent(@PathVariable Integer studentId, @ModelAttribute StudentsRequestDTO studentsRequestDTO) {
        studentsService.update(studentId, studentsRequestDTO);
        return "redirect:/students";
    }

    @PostMapping
    public String add(
            @ModelAttribute StudentsRequestDTO studentsRequestDTO,
            Map<String, Object> model, @PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable
    ) {
        studentsService.save(studentsRequestDTO);

        Page<Students> page = studentsService.findAll(pageable);

        model.put("page", page);
        model.put("url", "/students");

        return "students";
    }
}
