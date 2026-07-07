package com.itcube.journal.service.impl;

import com.itcube.journal.dto.course.CourseRequestDTO;
import com.itcube.journal.dto.course.CourseResponseDTO;
import com.itcube.journal.exceptions.CourseHasGroupsException;
import com.itcube.journal.exceptions.CourseNotFoundException;
import com.itcube.journal.mapper.course.CourseMapper;
import com.itcube.journal.model.Course;
import com.itcube.journal.model.Group;
import com.itcube.journal.repository.CourseRepository;
import com.itcube.journal.repository.GroupRepository;
import com.itcube.journal.service.CourseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {

    private final CourseMapper courseMapper;
    private final CourseRepository courseRepository;
    private final GroupRepository groupRepository;

    @Override
    public List<CourseResponseDTO> findAllCourses() {
        return courseRepository.findAll().stream()
                .map(courseMapper::toResponseDTO)
                .toList();
    }

    @Override
    public CourseResponseDTO findCourseById(Long id) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new CourseNotFoundException("Unable to find course with id: " + id));

        return courseMapper.toResponseDTO(course);
    }

    @Override
    public CourseResponseDTO createCourse(CourseRequestDTO courseRequestDTO) {
        Course savedCourse = courseMapper.toEntity(courseRequestDTO);

        savedCourse = courseRepository.save(savedCourse);

        if (courseRequestDTO.groupIds() != null && !courseRequestDTO.groupIds().isEmpty()) {
            List<Group> groups = groupRepository.findAllById(courseRequestDTO.groupIds());
            for (Group group : groups) {
                savedCourse.addGroup(group);
            }
            savedCourse = courseRepository.save(savedCourse);
        }

        return courseMapper.toResponseDTO(savedCourse);
    }

    @Override
    public CourseResponseDTO updateCourse(Long id, CourseRequestDTO courseRequestDTO) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new CourseNotFoundException("Unable to find course with id: " + id));

        courseMapper.updateFromDTO(courseRequestDTO, course);

        if (courseRequestDTO.groupIds() != null) {
            List<Group> resolvedGroups = groupRepository.findAllById(courseRequestDTO.groupIds());

            List<Group> groupsToRemove = course.getGroups().stream()
                    .filter(existing -> !resolvedGroups.contains(existing))
                    .toList();
            groupsToRemove.forEach(course::removeGroup);

            resolvedGroups.stream()
                    .filter(group -> !course.getGroups().contains(group))
                    .forEach(course::addGroup);
        }

        Course updatedCourse = courseRepository.save(course);

        return courseMapper.toResponseDTO(updatedCourse);
    }

    @Override
    public void deleteCourse(Long id) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new CourseNotFoundException("Unable to find course with id: " + id));

        if (!course.getGroups().isEmpty()) {
            throw new CourseHasGroupsException(
                    "Cannot delete course with id: " + id + " because it still has groups assigned");
        }

        courseRepository.delete(course);
    }
}
