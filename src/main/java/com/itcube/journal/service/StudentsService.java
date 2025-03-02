package com.itcube.journal.service;

import com.itcube.journal.dto.students.StudentDTO;
import com.itcube.journal.dto.students.StudentsRequestDTO;
import com.itcube.journal.mapper.students.StudentsMapper;
import com.itcube.journal.model.Students;
import com.itcube.journal.repos.StudentsRepo;
import com.itcube.journal.specification.StudentsSpecification;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class StudentsService {

    private final StudentsRepo studentsRepo;
    private final StudentsMapper studentsMapper;
    private final StudentsSpecification studentsSpecification;

    public Page<Students> findAll(Pageable pageable) {
        return studentsRepo.findAll(pageable);
    }

    public Iterable<Students> findAll() {
        return studentsRepo.findAll();
    }

    public Students findById(Integer id) {
        return studentsRepo.findById(id).orElse(null);
    }

    public List<StudentDTO> findByGroupName(String groupName) {
        List<Students> students = studentsRepo.findByNameGroup_GroupName(groupName);
        List<StudentDTO> studentDTOList = new ArrayList<>();

        for (Students student : students) {
            StudentDTO studentDTO = mapStudentsToDTO(student);

            studentDTOList.add(studentDTO);
        }
        return studentDTOList;
    }

    public Students save(StudentsRequestDTO students) {
        Students savedStudents = studentsMapper.mapStudentsRequestDTOToStudents(students);
        savedStudents = studentsRepo.save(savedStudents);
        return savedStudents;
    }

    public Page<Students> searchByKeyword(String keyword, Pageable pageable) {
        Specification<Students> specification = studentsSpecification.searchByKeyword(keyword);
        log.info("searchByKeyword: {}", specification);
        return studentsRepo.findAll(specification, pageable);
    }

    private StudentDTO mapStudentsToDTO(Students student) {
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setId(student.getId());
        studentDTO.setFirstname(student.getFirstname());
        studentDTO.setSurname(student.getSurname());
        studentDTO.setSecondname(student.getSecondname());
        return studentDTO;
    }

    @Transactional
    public Students update(Integer studentId, StudentsRequestDTO studentsRequestDTO) {
        Students updatedStudent = studentsRepo.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Unable to find student with id: " + studentId));

        studentsMapper.updateStudentsFromDTO(studentsRequestDTO, updatedStudent);

        return updatedStudent;
    }
}
