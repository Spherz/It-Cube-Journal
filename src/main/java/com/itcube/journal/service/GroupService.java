package com.itcube.journal.service;

import com.itcube.journal.dto.groups.GroupRequestDTO;
import com.itcube.journal.dto.groups.GroupResponseDTO;

import java.util.List;

public interface GroupService {

    List<GroupResponseDTO> findAllGroups();

    GroupResponseDTO findGroupById(Long id);

    GroupResponseDTO createGroup(GroupRequestDTO groupRequestDTO);

    GroupResponseDTO updateGroup(Long id, GroupRequestDTO groupRequestDTO);

    GroupResponseDTO assignTeacher(Long id, String employeeSub);

    List<GroupResponseDTO> findGroupsByEmployeeSub(String employeeSub);
}
