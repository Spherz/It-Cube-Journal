package com.itcube.journal.service;

import com.itcube.journal.dto.course.CourseRequestDTO;
import com.itcube.journal.mapper.course.CourseMapper;
import com.itcube.journal.model.Course;
import com.itcube.journal.repos.CourseRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CourseService {

    private final CourseRepo courseRepo;
    private final CourseMapper courseMapper;

    public Iterable<Course> findAll() {
        return courseRepo.findAll();
    }

    public Course find(Long id) {
        return courseRepo.findById(id).get();
    }

    public Course save(CourseRequestDTO courseRequestDTO) {
        Course savedCourse = courseMapper.mapCourseRequestDTOToCourse(courseRequestDTO);
        savedCourse = courseRepo.save(savedCourse);
        return courseRepo.save(savedCourse);
    }
}
