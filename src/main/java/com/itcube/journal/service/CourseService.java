package com.itcube.journal.service;

import com.itcube.journal.dto.course.CourseRequestDTO;
import com.itcube.journal.mapper.course.CourseMapper;
import com.itcube.journal.model.Course;
import com.itcube.journal.model.Groups;
import com.itcube.journal.repos.CourseRepo;
import com.itcube.journal.repos.GroupsRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CourseService {

    private final CourseRepo courseRepo;
    private final CourseMapper courseMapper;
    private final GroupsRepo groupsRepo;

    public Iterable<Course> findAll() {
        return courseRepo.findAll();
    }

    public Course findById(Long id) {
        return courseRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Course not found with id: " + id));
    }

    public Course save(CourseRequestDTO courseRequestDTO) {
        Course savedCourse = courseMapper.mapCourseRequestDTOToCourse(courseRequestDTO);
        savedCourse = courseRepo.save(savedCourse);
        return courseRepo.save(savedCourse);
    }

    @Transactional
    public Course update(CourseRequestDTO courseRequestDTO, Long courseId) {
        Course updatedCourse = courseRepo.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found with id: " + courseId));
        List<Groups> courseGroups = updatedCourse.getGroups();

        courseMapper.mapCourseRequestToCourse(updatedCourse, courseRequestDTO);

        List<Groups> newGroups = courseRequestDTO.getCourseGroup()
                .stream()
                .map(groupsRepo::findByGroupName)
                .collect(Collectors.toList());

        for (Groups group : new ArrayList<>(courseGroups)) {
            if (!newGroups.contains(group)) {
                updatedCourse.removeGroup(group);
            }
        }

        newGroups.forEach(updatedCourse::addGroup);

        updatedCourse = courseRepo.save(updatedCourse);

        return updatedCourse;
    }
}
