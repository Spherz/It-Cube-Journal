package com.itcube.journal.service;

import com.itcube.journal.dto.students.StudentDTO;
import com.itcube.journal.model.Students;
import com.itcube.journal.repos.StudentsRepo;
import com.itcube.journal.specification.StudentsSpecification;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class StudentsService {

    private final StudentsRepo studentsRepo;
    private final StudentsSpecification studentsSpecification;

    public Page<Students> findAll(Pageable pageable) {
        return studentsRepo.findAll(pageable);
    }

    public Iterable<Students> findAll() {
        return studentsRepo.findAll();
    }

    public List<Students> findByCourse(Integer id) {
        return studentsRepo.findByCourse(id);
    }

    public Page<Students> findByGroupName(String groupName, Pageable pageable) {
        return studentsRepo.findByNameGroup_GroupName(groupName, pageable);
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

    public Students save(Students students) {
        return studentsRepo.save(students);
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
}
