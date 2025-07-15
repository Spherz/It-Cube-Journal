package com.itcube.journal.mapper.group;

import com.itcube.journal.dto.groups.GroupDTO;
import com.itcube.journal.dto.groups.GroupRequestDTO;
import com.itcube.journal.dto.groups.GroupResponseDTO;
import com.itcube.journal.model.Group;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface GroupMapper {

    Group toEntity(GroupRequestDTO groupRequestDTO);

    GroupResponseDTO toResponseDTO(Group group);

    GroupDTO toDTO(Group group);

    void updateFromDTO(GroupRequestDTO groupRequestDTO, @MappingTarget Group group);
}
