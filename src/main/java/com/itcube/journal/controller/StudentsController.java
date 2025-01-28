package com.itcube.journal.controller;

import com.itcube.journal.model.Groups;
import com.itcube.journal.model.Students;
import com.itcube.journal.repos.GroupsRepo;
import com.itcube.journal.repos.StaffRepo;
import com.itcube.journal.repos.StudentsRepo;
import com.itcube.journal.repos.UserRepo;
import com.itcube.journal.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping("/students")
public class StudentsController {

    private final StudentsRepo studentsRepo;
    private final UserRepo userRepo;
    private final GroupsRepo groupsRepo;
    private final StaffRepo staffRepo;
    private final UserService userService;

    // TODO: Вернуть поиск и сделать его по keyword
    @GetMapping
    public String studentsList(@RequestParam(required = false, defaultValue = "") String filter,
                               Model model, @PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable) {
        Page<Students> page;

        if(filter != null && !filter.isEmpty()) {
            page = studentsRepo.findByNameGroup_GroupName(filter, pageable);
            System.out.println(filter);
        } else {
            page = studentsRepo.findAll(pageable);
        }

        model.addAttribute("filterStudents", filter);
        model.addAttribute("page", page);
        model.addAttribute("url", "/students");
        model.addAttribute("employees", staffRepo.findAll());

        return "students";
    }

    @GetMapping("/students/get_select_value")
    public String getValueFromDropDown(@RequestParam("groupsList") String selectValue) {
        System.out.println(selectValue);

        return "students";
    }

    @GetMapping("/students")
    public String studentsListByGroup(@RequestParam("groupsList") String groupsFilter, Model model) {
        Iterable<Groups> studentsByGroup;

        if(groupsFilter != null && !groupsFilter.isEmpty()) {
            studentsByGroup = groupsRepo.findGroupsByGroupName(groupsFilter);
            System.out.println(groupsFilter);
        } else {
            studentsByGroup = groupsRepo.findAll();
        }

        model.addAttribute("groupsFilter", groupsFilter);
        model.addAttribute("groups", studentsByGroup);

        return "students";
    }

    @PostMapping
    public String add(
            @RequestParam String surname, @RequestParam String firstname,
            @RequestParam String secondname, @RequestParam String birthDate,
            @RequestParam String certificate, @RequestParam String studentClass,
            @RequestParam String parentFullName, @RequestParam String school,
            @RequestParam String email, @RequestParam String numberPhone,
            Map<String, Object> model, @PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable
    ) {
        Students student = new Students();

        setStudent(surname, firstname, secondname, birthDate, certificate, studentClass, parentFullName, school, email, numberPhone, student);

        studentsRepo.save(student);

        Page<Students> page = studentsRepo.findAll(pageable);

        model.put("page", page);
        model.put("url", "/students");

        return "students";
    }

    private void setStudent(String surname, String firstname, String secondname, String birthDate, String certificate, String studentClass, String parentFullName, String school, String email, String numberPhone, Students student) {
        student.setSurname(surname);
        student.setFirstname(firstname);
        student.setSecondname(secondname);
        student.setDateOfBirth(birthDate);
        student.setCertificateNumber(certificate);
        student.setStudentClass(studentClass);
        student.setParent(parentFullName);
        student.setSchool(school);
        student.setEmail(email);
        student.setPhoneNumber(numberPhone);
    }


}
