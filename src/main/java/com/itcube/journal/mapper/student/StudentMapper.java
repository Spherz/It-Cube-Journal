package com.itcube.journal.mapper.student;

import com.itcube.journal.dto.student.StudentDTO;
import com.itcube.journal.dto.student.StudentRequestDTO;
import com.itcube.journal.dto.student.StudentResponseDTO;
import com.itcube.journal.model.Student;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface StudentMapper {

    @Mapping(target = "group", ignore = true)
    Student toEntity(StudentRequestDTO studentRequestDTO);

    @Mapping(target = "groupId", expression = "java(student.getGroup() != null ? student.getGroup().getId() : null)")
    @Mapping(target = "groupName", expression = "java(student.getGroup() != null ? student.getGroup().getName() : null)")
    StudentResponseDTO toResponseDTO(Student student);

    @Mapping(target = "groupId", expression = "java(student.getGroup() != null ? student.getGroup().getId() : null)")
    @Mapping(target = "groupName", expression = "java(student.getGroup() != null ? student.getGroup().getName() : null)")
    StudentDTO toDTO(Student student);

    @Mapping(target = "group", ignore = true)
    void updateFromDTO(StudentRequestDTO studentRequestDTO, @MappingTarget Student student);
}
