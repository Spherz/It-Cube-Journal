package com.itcube.journal.service;

import com.itcube.journal.domain.ThematicPlanning;
import com.itcube.journal.domain.User;
import com.itcube.journal.repos.ThematicPlanningRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ThematicPlanningService {

    @Autowired
    private ThematicPlanningRepo thematicPlanningRepo;

    public List<ThematicPlanning> findUserThemes(User user) {
        return thematicPlanningRepo.findByUser(user);
    }
    public Iterable<ThematicPlanning> findThemes() {
        return thematicPlanningRepo.findAll();
    }
}
