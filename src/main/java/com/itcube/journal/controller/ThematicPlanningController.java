package com.itcube.journal.controller;

import com.itcube.journal.model.ThematicPlanning;
import com.itcube.journal.model.User;
import com.itcube.journal.repos.ThematicPlanningRepo;
import com.itcube.journal.repos.UserRepo;
import com.itcube.journal.service.ThematicPlanningService;
import com.itcube.journal.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping("/planning")
public class ThematicPlanningController {
    private final ThematicPlanningRepo thematicPlanningRepo;
    private final UserRepo userRepo;
    private final UserService userService;
    private final ThematicPlanningService thematicPlanningService;

    @GetMapping
    public String showThemes(Model model, Principal principal) {
        String username = principal.getName();
        User user = userService.findOne(username);

        if(user.isAdmin() || user.isMethodist()) {
            model.addAttribute("themes", thematicPlanningService.findThemes());
        } else {
            model.addAttribute("themes", thematicPlanningService.findUserThemes(user));
        }
        return "planning";
    }
    // TODO: вернуть поиск
//    @GetMapping
//    public String themeList(@RequestParam(required = false, defaultValue = "") String filter, Model model) {
//        Iterable<ThematicPlanning> thematicPlannings;
//        Iterable<ThematicPlanning> thematicBySurname;
//
//        if(filter != null && !filter.isEmpty()) {
//            thematicBySurname = thematicPlanningRepo.findByStaff_Surname(filter);
//        } else {
//            thematicBySurname = thematicPlanningRepo.findAll();
//        }
//
////        if(filter != null && !filter.isEmpty()) {
////            thematicPlannings = thematicPlanningRepo.findAllByThemeName(filter);
////        } else {
////            thematicPlannings = thematicPlanningRepo.findAll();
////        }
//
//        /*model.addAttribute("filter", filter);
//        model.addAttribute("themes", thematicPlannings);*/
//        model.addAttribute("filter", filter);
//        model.addAttribute("themes", thematicBySurname);
//
//        return "planning";
//    }

    @GetMapping("/planning/{username}")
    public String plansByTeacher(@PathVariable ThematicPlanning themes, Model model) {
        model.addAttribute("test", themes);
        return "planning";
    }

    @GetMapping("{planning}")
    public String planningEditForm(@PathVariable ThematicPlanning planning, Model model) {

        model.addAttribute("plansList", planning);

        return "planningEdit";
    }

    @PostMapping
    public String add(
            @AuthenticationPrincipal User user,
            @RequestParam String themeName,
            @RequestParam String course,
            @RequestParam String totalHours,
            Map<String, Object> model
    ) {
        ThematicPlanning planning = new ThematicPlanning();

        setPlanning(themeName, course, totalHours, planning);

        thematicPlanningRepo.save(planning);

        Iterable<ThematicPlanning> plannings = thematicPlanningRepo.findAll();

        model.put("plannings", plannings);

        return "redirect:/planning";
    }

    private void setPlanning(String themeName, String course, String totalHours, ThematicPlanning planning) {
        planning.setThemeName(themeName);
        planning.setCourse(course);
        planning.setTotalHours(totalHours);
    }

}
