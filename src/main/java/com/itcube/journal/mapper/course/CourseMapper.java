package com.itcube.journal.mapper.course;

import com.itcube.journal.dto.course.CourseDTO;
import com.itcube.journal.dto.course.CourseRequestDTO;
import com.itcube.journal.dto.course.CourseResponseDTO;
import com.itcube.journal.mapper.group.GroupMapper;
import com.itcube.journal.model.Course;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = GroupMapper.class)
public interface CourseMapper {

    @Mapping(target = "groups", ignore = true)
    Course toEntity(CourseRequestDTO courseRequestDTO);

    CourseResponseDTO toResponseDTO(Course course);

    CourseDTO toDTO(Course course);

    @Mapping(target = "groups", ignore = true)
    void updateFromDTO(CourseRequestDTO courseRequestDTO, @MappingTarget Course course);
}
