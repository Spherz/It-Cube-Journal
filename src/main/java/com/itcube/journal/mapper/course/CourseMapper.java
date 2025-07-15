package com.itcube.journal.mapper.course;

import com.itcube.journal.dto.course.CourseDTO;
import com.itcube.journal.dto.course.CourseRequestDTO;
import com.itcube.journal.dto.course.CourseResponseDTO;
import com.itcube.journal.model.Course;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CourseMapper {

    Course toEntity(CourseRequestDTO courseRequestDTO);

    CourseResponseDTO toResponseDTO(Course course);

    CourseDTO toDTO(Course course);

    void updateFromDTO(CourseRequestDTO courseRequestDTO, @MappingTarget Course course);
}
