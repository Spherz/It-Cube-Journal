package com.itcube.journal.controller;

import com.itcube.journal.repos.GroupsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/groups")
public class GroupsController {
    @Autowired
    private GroupsRepo groupsRepo;

    @GetMapping
    public String groupsList(Model model) {
        model.addAttribute("groups", groupsRepo.findAll());

        return "groups";
    }

}
