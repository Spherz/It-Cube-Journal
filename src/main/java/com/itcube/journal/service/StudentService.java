package com.itcube.journal.service;

import com.itcube.journal.dto.student.StudentRequestDTO;
import com.itcube.journal.dto.student.StudentResponseDTO;

import java.util.List;

public interface StudentService {

    List<StudentResponseDTO> findAllStudents();

    StudentResponseDTO findStudentById(Long id);

    List<StudentResponseDTO> findStudentsByGroupId(Long groupId);

    StudentResponseDTO createStudent(StudentRequestDTO studentRequestDTO);

    StudentResponseDTO updateStudent(Long id, StudentRequestDTO studentRequestDTO);
}
