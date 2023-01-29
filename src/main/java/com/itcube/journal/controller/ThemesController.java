package com.itcube.journal.controller;

import com.itcube.journal.domain.Themes;
import com.itcube.journal.domain.User;
import com.itcube.journal.repos.ThemesRepo;
import com.itcube.journal.service.ThemesService;
import com.itcube.journal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@Controller
@RequestMapping("/themes")
public class ThemesController {

    @Autowired
    private ThemesRepo themesRepo;

    @Autowired
    private UserService userService;

    @Autowired
    private ThemesService themesService;

    @GetMapping
    public String getThemesList(Model model, @RequestParam(required = false, defaultValue = "") String filter, Principal principal) {
        String username = principal.getName();
        User user = userService.findOne(username);

        Iterable<Themes> themesByGroup;

        if(user.isAdmin() || user.isMethodist()) {
            model.addAttribute("themesList", themesService.findThemes());
        } else {
            model.addAttribute("themesList", themesService.findUserThemes(user)); // TODO: Добавить поиск
        }

//        if(filter != null && !filter.isEmpty()) {
//            themesByGroup = themesRepo.findByGroups_GroupName(filter);
//            System.out.println(filter);
//        } else {
//            themesByGroup = themesRepo.findAll();
//        }

//        model.addAttribute("themesList", themesByGroup);
        model.addAttribute("filterStudentsByGroup", filter);

        return "themes";
    }
}
