package com.itcube.journal.service;

import com.itcube.journal.model.Groups;
import com.itcube.journal.model.User;
import com.itcube.journal.repos.GroupsRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class GroupsService {

    private final GroupsRepo groupsRepo;

    public Iterable<Groups> findAll() {
        log.info("Find all groups");
        return groupsRepo.findAll();
    }

    public Groups findById(Integer groupId) {
        log.info("Find group by id: {}", groupId);
        return groupsRepo.findById(groupId)
                .orElseThrow(() -> new RuntimeException("Can't find groups with id: " + groupId));
    }

    public List<Groups> findByCourse(Integer id) {
        log.info("Find groups by course {}", id);
        return groupsRepo.findByCourse(id);
    }

    public List<Groups> findUserGroups(User user) {
        log.info("Find groups by user {}", user);
        return groupsRepo.findByUser(user);
    }
    public Iterable<Groups> findGroups() {
        return groupsRepo.findAll();
    }

    public List<Groups> findGroupsByName(String groupName) {
        log.info("Find groups by name {}", groupName);
        return groupsRepo.findGroupsByGroupName(groupName);
    }

    public List<Groups> findGroupsByCourseId(Long courseId) {
        log.info("Find groups by course id {}", courseId);
        return groupsRepo.findByCourseId(courseId);
    }
}
