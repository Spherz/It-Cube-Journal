package com.itcube.journal.service.impl;

import com.itcube.journal.dto.student.StudentRequestDTO;
import com.itcube.journal.dto.student.StudentResponseDTO;
import com.itcube.journal.exceptions.GroupNotFoundException;
import com.itcube.journal.exceptions.StudentNotFoundException;
import com.itcube.journal.mapper.student.StudentMapper;
import com.itcube.journal.model.Group;
import com.itcube.journal.model.Student;
import com.itcube.journal.repository.GroupRepository;
import com.itcube.journal.repository.StudentRepository;
import com.itcube.journal.service.StudentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentMapper studentMapper;
    private final StudentRepository studentRepository;
    private final GroupRepository groupRepository;

    @Override
    public List<StudentResponseDTO> findAllStudents() {
        return studentRepository.findAll().stream()
                .map(studentMapper::toResponseDTO)
                .toList();
    }

    @Override
    public StudentResponseDTO findStudentById(Long id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException("Unable to find student with id: " + id));

        return studentMapper.toResponseDTO(student);
    }

    @Override
    public List<StudentResponseDTO> findStudentsByGroupId(Long groupId) {
        return studentRepository.findByGroupId(groupId).stream()
                .map(studentMapper::toResponseDTO)
                .toList();
    }

    @Override
    public StudentResponseDTO createStudent(StudentRequestDTO studentRequestDTO) {

        Student savedStudent = studentMapper.toEntity(studentRequestDTO);

        if (studentRequestDTO.groupId() != null) {
            Group group = groupRepository.findById(studentRequestDTO.groupId())
                    .orElseThrow(() -> new GroupNotFoundException("Unable to find group with id: " + studentRequestDTO.groupId()));
            savedStudent.setGroup(group);
        }

        savedStudent = studentRepository.save(savedStudent);

        return studentMapper.toResponseDTO(savedStudent);
    }

    @Override
    public StudentResponseDTO updateStudent(Long id, StudentRequestDTO studentRequestDTO) {
        Student updatedStudent = studentRepository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException("Unable to find student with id: " + id));

        studentMapper.updateFromDTO(studentRequestDTO, updatedStudent);

        if (studentRequestDTO.groupId() != null) {
            Group group = groupRepository.findById(studentRequestDTO.groupId())
                    .orElseThrow(() -> new GroupNotFoundException("Unable to find group with id: " + studentRequestDTO.groupId()));
            updatedStudent.setGroup(group);
        } else {
            updatedStudent.setGroup(null);
        }

        updatedStudent = studentRepository.save(updatedStudent);

        return studentMapper.toResponseDTO(updatedStudent);
    }
}
