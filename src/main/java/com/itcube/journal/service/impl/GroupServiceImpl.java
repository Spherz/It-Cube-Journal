package com.itcube.journal.service.impl;

import com.itcube.journal.dto.groups.GroupRequestDTO;
import com.itcube.journal.dto.groups.GroupResponseDTO;
import com.itcube.journal.exceptions.CourseNotFoundException;
import com.itcube.journal.exceptions.GroupNotFoundException;
import com.itcube.journal.mapper.group.GroupMapper;
import com.itcube.journal.model.Course;
import com.itcube.journal.model.Group;
import com.itcube.journal.model.Student;
import com.itcube.journal.repository.CourseRepository;
import com.itcube.journal.repository.GroupRepository;
import com.itcube.journal.repository.StudentRepository;
import com.itcube.journal.service.GroupService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class GroupServiceImpl implements GroupService {

    private final GroupMapper groupMapper;
    private final GroupRepository groupRepository;
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    @Override
    public List<GroupResponseDTO> findAllGroups() {
        return groupRepository.findAllWithCourse().stream()
                .map(groupMapper::toResponseDTO)
                .toList();
    }

    @Override
    public GroupResponseDTO findGroupById(Long id) {
        Group group = groupRepository.findByIdWithCourse(id)
                .orElseThrow(() -> new GroupNotFoundException("Unable to find group with id: " + id));

        return groupMapper.toResponseDTO(group);
    }

    @Override
    public GroupResponseDTO createGroup(GroupRequestDTO groupRequestDTO) {
        Group savedGroup = groupMapper.toEntity(groupRequestDTO);

        if (groupRequestDTO.courseId() != null) {
            Course course = courseRepository.findById(groupRequestDTO.courseId())
                    .orElseThrow(() -> new CourseNotFoundException("Unable to find course with id: " + groupRequestDTO.courseId()));
            savedGroup.setCourse(course);
        }

        savedGroup = groupRepository.save(savedGroup);

        if (groupRequestDTO.studentIds() != null && !groupRequestDTO.studentIds().isEmpty()) {
            List<Student> resolvedStudents = studentRepository.findAllById(groupRequestDTO.studentIds());
            for (Student student : resolvedStudents) {
                savedGroup.addStudent(student);
            }
            studentRepository.saveAll(resolvedStudents);
        }

        return groupMapper.toResponseDTO(savedGroup);
    }

    @Override
    public GroupResponseDTO updateGroup(Long id, GroupRequestDTO groupRequestDTO) {
        Group group = groupRepository.findById(id)
                .orElseThrow(() -> new GroupNotFoundException("Unable to find group with id: " + id));

        groupMapper.updateFromDTO(groupRequestDTO, group);

        if (groupRequestDTO.courseId() != null) {
            Course course = courseRepository.findById(groupRequestDTO.courseId())
                    .orElseThrow(() -> new CourseNotFoundException("Unable to find course with id: " + groupRequestDTO.courseId()));
            group.setCourse(course);
        } else {
            group.setCourse(null);
        }

        if (groupRequestDTO.studentIds() != null) {
            List<Student> resolvedStudents = studentRepository.findAllById(groupRequestDTO.studentIds());

            List<Student> currentStudents = new ArrayList<>(group.getStudents());

            List<Student> studentsToRemove = currentStudents.stream()
                    .filter(existing -> !resolvedStudents.contains(existing))
                    .toList();
            studentsToRemove.forEach(group::removeStudent);

            List<Student> studentsToAdd = resolvedStudents.stream()
                    .filter(student -> !group.getStudents().contains(student))
                    .toList();
            studentsToAdd.forEach(group::addStudent);

            List<Student> touchedStudents = new ArrayList<>(studentsToRemove);
            touchedStudents.addAll(studentsToAdd);
            studentRepository.saveAll(touchedStudents);
        }

        Group updatedGroup = groupRepository.save(group);

        return groupMapper.toResponseDTO(updatedGroup);
    }
}
