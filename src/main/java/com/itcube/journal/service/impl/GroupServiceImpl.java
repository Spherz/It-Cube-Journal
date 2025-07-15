package com.itcube.journal.service.impl;

import com.itcube.journal.dto.groups.GroupRequestDTO;
import com.itcube.journal.dto.groups.GroupResponseDTO;
import com.itcube.journal.exceptions.GroupNotFoundException;
import com.itcube.journal.mapper.group.GroupMapper;
import com.itcube.journal.model.Group;
import com.itcube.journal.repository.GroupRepository;
import com.itcube.journal.service.GroupService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class GroupServiceImpl implements GroupService {

    private final GroupMapper groupMapper;
    private final GroupRepository groupRepository;

    @Override
    public List<GroupResponseDTO> findAllGroups() {
        return groupRepository.findAll().stream()
                .map(groupMapper::toResponseDTO)
                .toList();
    }

    @Override
    public GroupResponseDTO findGroupById(Long id) {
        Group group = groupRepository.findById(id)
                .orElseThrow(() -> new GroupNotFoundException("Unable to find group with id: " + id));

        return groupMapper.toResponseDTO(group);
    }

    @Override
    public GroupResponseDTO createGroup(GroupRequestDTO groupRequestDTO) {
        Group savedGroup = groupMapper.toEntity(groupRequestDTO);

        savedGroup = groupRepository.save(savedGroup);

        return groupMapper.toResponseDTO(savedGroup);
    }

    @Override
    public GroupResponseDTO updateGroup(Long id, GroupRequestDTO groupRequestDTO) {
        Group updatedGroup = groupRepository.findById(id)
                .orElseThrow(() -> new GroupNotFoundException("Unable to find group with id: " + id));

        groupMapper.updateFromDTO(groupRequestDTO, updatedGroup);

        updatedGroup = groupRepository.save(updatedGroup);

        return groupMapper.toResponseDTO(updatedGroup);
    }
}
