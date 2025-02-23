package com.itcube.journal.mapper.course;

import com.itcube.journal.dto.course.CourseRequestDTO;
import com.itcube.journal.model.Course;
import org.springframework.stereotype.Component;

@Component
public class CourseMapper {

    public Course mapCourseRequestDTOToCourse(CourseRequestDTO courseRequestDTO) {
        Course course = new Course();
        course.setCourseName(courseRequestDTO.getCourseName() == null ? "" : courseRequestDTO.getCourseName());
        return course;
    }
}
