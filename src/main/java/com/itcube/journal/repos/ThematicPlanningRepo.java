package com.itcube.journal.repos;

import com.itcube.journal.model.ThematicPlanning;
import com.itcube.journal.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ThematicPlanningRepo extends CrudRepository<ThematicPlanning, Long> {
    List<ThematicPlanning> findAllByThemeName(String theme_name);
    List<ThematicPlanning> findByStaff_Surname(String surname);

    List<ThematicPlanning> findByUser(User user);


}
