package com.itcube.journal.service;

import com.itcube.journal.model.ThematicPlanning;
import com.itcube.journal.model.User;
import com.itcube.journal.repos.ThematicPlanningRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ThematicPlanningService {

    private final ThematicPlanningRepo thematicPlanningRepo;

    public Iterable<ThematicPlanning> findAll() {
        log.info("Find all thematic plannings");
        return thematicPlanningRepo.findAll();
    }

    public List<ThematicPlanning> findUserThemes(User user) {
        log.info("Find themes for user {}", user.getId());
        return thematicPlanningRepo.findByUser(user);
    }

    public Iterable<ThematicPlanning> findThemes() {
        log.info("Find all themes");
        return thematicPlanningRepo.findAll();
    }

    public ThematicPlanning save(ThematicPlanning thematicPlanning) {
        log.info("Saving thematic planning {}", thematicPlanning.getThemeName());
        return thematicPlanningRepo.save(thematicPlanning);
    }
}
