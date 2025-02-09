package com.itcube.journal.controller;

import com.itcube.journal.model.User;
import com.itcube.journal.repos.GroupsRepo;
import com.itcube.journal.service.GroupsService;
import com.itcube.journal.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
@RequestMapping("/groups")
public class GroupsController {

    private final GroupsService groupsService;
    private final UserService userService;

    @GetMapping
    public String groupsList(Model model, Principal principal) {
        String username = principal.getName();
        User user = userService.findOne(username);

        if(user.isAdmin() || user.isMethodist()) {
            model.addAttribute("groups", groupsService.findGroups());
        } else {
            model.addAttribute("groups", groupsService.findUserGroups(user));
        }

        return "groups";
    }

}
