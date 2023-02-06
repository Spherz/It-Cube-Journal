package com.itcube.journal.service;

import com.itcube.journal.domain.Course;
import com.itcube.journal.domain.Groups;
import com.itcube.journal.domain.ThematicPlanning;
import com.itcube.journal.domain.User;
import com.itcube.journal.repos.CourseRepo;
import com.itcube.journal.repos.GroupsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupsService {
    @Autowired
    private GroupsRepo groupsRepo;
    public Iterable<Groups> findAll() {
        return groupsRepo.findAll();
    }

    public List<Groups> findByCourse(Integer id) {
        return groupsRepo.findByCourse(id);
    }

    public List<Groups> findUserGroups(User user) {
        return groupsRepo.findByUser(user);
    }
    public Iterable<Groups> findGroups() {
        return groupsRepo.findAll();
    }
}
