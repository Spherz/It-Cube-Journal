package com.itcube.journal.controller;

import com.itcube.journal.domain.Staff;
import com.itcube.journal.repos.StaffRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
@RequestMapping("/staff")
public class StaffController {
    @Autowired
    private StaffRepo staffRepo;

    @GetMapping
    public String staffList(Model model) {
        model.addAttribute("employees", staffRepo.findAll());

        return "staff";
    }

    @PostMapping
    public String add(
            @RequestParam String surname, @RequestParam String firstname,
            @RequestParam String secondname, @RequestParam String dateOfBirth,
            @RequestParam String education, @RequestParam String diplomaNumber,
            @RequestParam String qualification, Map<String, Object> model) {
        Staff employee = new Staff(surname, firstname,
                secondname, dateOfBirth,
                education, diplomaNumber,
                qualification);

        staffRepo.save(employee);

        Iterable<Staff> employees = staffRepo.findAll();

        model.put("employees", employees);

        return "staff";
    }
}
