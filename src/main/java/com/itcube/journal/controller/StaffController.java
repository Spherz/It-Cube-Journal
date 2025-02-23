package com.itcube.journal.controller;

import com.itcube.journal.dto.staff.StaffRequestDTO;
import com.itcube.journal.model.Staff;
import com.itcube.journal.service.StaffService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
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
            @RequestParam String secondname, @RequestParam LocalDate dateOfBirth,
            @RequestParam String education, @RequestParam String diplomaNumber,
            @RequestParam String qualification, Map<String, Object> model) {
        Staff employee = new Staff();

        setEmployee(surname, firstname, secondname, dateOfBirth, education, diplomaNumber, qualification, employee);

        employee = staffService.save(employee);

        Iterable<Staff> employees = staffService.findAll();

        model.put("employees", employees);

        return "staff";
    }

    @GetMapping("/{staffId}")
    public String updateStaffForm(@PathVariable Integer staffId, Model model) {
        model.addAttribute("staff", staffService.findById(staffId));
        return "staffEdit";
    }

    @PostMapping("/update/{staffId}")
    public String updateStaff(@PathVariable Integer staffId, @ModelAttribute StaffRequestDTO staffRequestDTO) {
        staffService.update(staffRequestDTO, staffId);
        return "redirect:/staff";
    }

    private void setEmployee(String surname, String firstname,
                             String secondname, LocalDate dateOfBirth,
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
