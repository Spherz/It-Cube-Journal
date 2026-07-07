package com.itcube.journal.mapper.group;

import com.itcube.journal.dto.groups.GroupDTO;
import com.itcube.journal.dto.groups.GroupRequestDTO;
import com.itcube.journal.dto.groups.GroupResponseDTO;
import com.itcube.journal.model.Group;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface GroupMapper {

    @Mapping(target = "students", ignore = true)
    @Mapping(target = "course", ignore = true)
    Group toEntity(GroupRequestDTO groupRequestDTO);

    @Mapping(target = "studentCount", expression = "java(group.getStudents().size())")
    @Mapping(target = "courseId", expression = "java(group.getCourse() != null ? group.getCourse().getId() : null)")
    @Mapping(target = "courseName", expression = "java(group.getCourse() != null ? group.getCourse().getCourseName() : null)")
    GroupResponseDTO toResponseDTO(Group group);

    @Mapping(target = "studentCount", expression = "java(group.getStudents().size())")
    GroupDTO toDTO(Group group);

    @Mapping(target = "students", ignore = true)
    @Mapping(target = "course", ignore = true)
    void updateFromDTO(GroupRequestDTO groupRequestDTO, @MappingTarget Group group);
}
