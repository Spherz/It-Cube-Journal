package com.itcube.journal.mapper.groups;

import com.itcube.journal.dto.groups.GroupRequestDTO;
import com.itcube.journal.dto.staff.StaffRequestDTO;
import com.itcube.journal.model.Groups;
import com.itcube.journal.model.Staff;
import org.springframework.stereotype.Component;

@Component
public class GroupsMapper {

    public Groups mapGroupRequestDTOToGroup(GroupRequestDTO groupRequestDTO) {
        Groups groups = new Groups();

        groups.setGroupName(groupRequestDTO.getGroupName() == null ? "" : groupRequestDTO.getGroupName());
        groups.setProgramName(groupRequestDTO.getProgramName() == null ? "" : groupRequestDTO.getProgramName());
        groups.setHours(groupRequestDTO.getHours() == null ? "" : groupRequestDTO.getHours());
        groups.setTeacher(groupRequestDTO.getTeacher() == null ? "" : groupRequestDTO.getTeacher());
        groups.setDecreeNumber(groupRequestDTO.getDecreeNumber() == null ? "" : groupRequestDTO.getDecreeNumber());
        groups.setDecreeDate(groupRequestDTO.getDecreeDate() == null ? "" : groupRequestDTO.getDecreeDate());
        groups.setEducationForm(groupRequestDTO.getEducationForm() == null ? "" : groupRequestDTO.getEducationForm());

        return groups;
    }

    public Groups updateGroupFromDTO(GroupRequestDTO groupRequestDTO, Groups groups) {
        groups.setGroupName(groupRequestDTO.getGroupName() == null ? "" : groupRequestDTO.getGroupName());
        groups.setProgramName(groupRequestDTO.getProgramName() == null ? "" : groupRequestDTO.getProgramName());
        groups.setHours(groupRequestDTO.getHours() == null ? "" : groupRequestDTO.getHours());
        groups.setTeacher(groupRequestDTO.getTeacher() == null ? "" : groupRequestDTO.getTeacher());
        groups.setDecreeNumber(groupRequestDTO.getDecreeNumber() == null ? "" : groupRequestDTO.getDecreeNumber());
        groups.setDecreeDate(groupRequestDTO.getDecreeDate() == null ? "" : groupRequestDTO.getDecreeDate());
        groups.setEducationForm(groupRequestDTO.getEducationForm() == null ? "" : groupRequestDTO.getEducationForm());

        return groups;
    }
}
