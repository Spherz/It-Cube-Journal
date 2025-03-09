package com.itcube.journal.controller;

import com.itcube.journal.dto.groups.GroupRequestDTO;
import com.itcube.journal.model.User;
import com.itcube.journal.service.GroupsService;
import com.itcube.journal.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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

    @GetMapping("/{groupId}")
    public String groupUpdateForm(@PathVariable Integer groupId, Model model) {
        model.addAttribute("groups", groupsService.findById(groupId));
        return "groupsEdit";
    }

    @PostMapping("/create")
    public String createGroup(@ModelAttribute GroupRequestDTO groupsRequestDTO) {
        groupsService.save(groupsRequestDTO);
        return "redirect:/groups";
    }

    @PostMapping("/update/{groupId}")
    public String updateGroup(@PathVariable Integer groupId, @ModelAttribute GroupRequestDTO groupsRequestDTO) {
        groupsService.update(groupsRequestDTO, groupId);
        return "redirect:/groups";
    }
}
