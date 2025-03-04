package com.itcube.journal.service;

import com.itcube.journal.model.Themes;
import com.itcube.journal.model.User;
import com.itcube.journal.repos.ThemesRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ThemesService {
    @Autowired
    private ThemesRepo themesRepo;

    public List<Themes> findUserThemes(User user) {
        return themesRepo.findByUser(user);
    }

    public Iterable<Themes> findThemes() {
        return themesRepo.findAll();
    }
}
