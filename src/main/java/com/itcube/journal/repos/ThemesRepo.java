package com.itcube.journal.repos;

import com.itcube.journal.domain.ThematicPlanning;
import com.itcube.journal.domain.Themes;
import com.itcube.journal.domain.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ThemesRepo extends CrudRepository<Themes, Integer> {
    List<Themes> findByGroups_GroupName(String group);

    List<Themes> findByUser(User user);
}
