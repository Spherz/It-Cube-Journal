package com.itcube.journal.service;

import com.itcube.journal.dto.groups.GroupRequestDTO;
import com.itcube.journal.mapper.groups.GroupsMapper;
import com.itcube.journal.model.Groups;
import com.itcube.journal.model.User;
import com.itcube.journal.repos.GroupsRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class GroupsService {

    private final GroupsRepo groupsRepo;
    private final GroupsMapper groupsMapper;

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

    public Groups save(GroupRequestDTO groupRequestDTO) {
        Groups group = groupsMapper.mapGroupRequestDTOToGroup(groupRequestDTO);
        groupsRepo.save(group);

        return group;
    }

    @Transactional
    public Groups update(GroupRequestDTO groupRequestDTO, Integer groupId) {
        Groups groups = groupsRepo.findById(groupId)
                .orElseThrow(() -> new RuntimeException("Can't find groups with id: " + groupId));

        groupsMapper.updateGroupFromDTO(groupRequestDTO, groups);

        return groupsRepo.save(groups);
    }
}
