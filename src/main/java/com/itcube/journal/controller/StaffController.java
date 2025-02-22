package com.itcube.journal.controller;

import com.itcube.journal.model.Staff;
import com.itcube.journal.repos.StaffRepo;
import com.itcube.journal.service.StaffService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping("/staff")
public class StaffController {

    private final StaffService staffService;

    @GetMapping
    public String staffList(Model model) {
        model.addAttribute("employees", staffService.findAll());

        return "staff";
    }

    @PostMapping
    public String add(
            @RequestParam String surname, @RequestParam String firstname,
            @RequestParam String secondname, @RequestParam String dateOfBirth,
            @RequestParam String education, @RequestParam String diplomaNumber,
            @RequestParam String qualification, Map<String, Object> model) {
        Staff employee = new Staff();

        setEmployee(surname, firstname, secondname, dateOfBirth, education, diplomaNumber, qualification, employee);

        employee = staffService.save(employee);

        Iterable<Staff> employees = staffService.findAll();

        model.put("employees", employees);

        return "staff";
    }

    private void setEmployee(String surname, String firstname,
                             String secondname, String dateOfBirth,
                             String education, String diplomaNumber,
                             String qualification, Staff employee) {
        employee.setSurname(surname);
        employee.setFirstname(firstname);
        employee.setSecondname(secondname);
        employee.setBirthDate(dateOfBirth);
        employee.setEducation(education);
        employee.setDiplomaNumber(diplomaNumber);
        employee.setQualification(qualification);
    }
}
