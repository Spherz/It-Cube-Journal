package com.itcube.journal.service;

import com.itcube.journal.dto.course.CourseRequestDTO;
import com.itcube.journal.dto.course.CourseResponseDTO;

import java.util.List;

public interface CourseService {

    List<CourseResponseDTO> findAllCourses();

    CourseResponseDTO findCourseById(Long id);

    CourseResponseDTO createCourse(CourseRequestDTO courseRequestDTO);

    CourseResponseDTO updateCourse(Long id, CourseRequestDTO courseRequestDTO);

    void deleteCourse(Long id);
}
