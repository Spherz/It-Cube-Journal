package com.itcube.journal.service;

import com.itcube.journal.model.Themes;
import com.itcube.journal.model.User;
import com.itcube.journal.repos.ThemesRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ThemesService {

    private final ThemesRepo themesRepo;

    public List<Themes> findUserThemes(User user) {
        return themesRepo.findByUser(user);
    }

    public Iterable<Themes> findThemes() {
        return themesRepo.findAll();
    }
}
