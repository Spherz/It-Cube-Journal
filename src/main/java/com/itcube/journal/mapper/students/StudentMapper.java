package com.itcube.journal.mapper.students;

import com.itcube.journal.dto.student.StudentDTO;
import com.itcube.journal.dto.student.StudentRequestDTO;
import com.itcube.journal.dto.student.StudentResponseDTO;
import com.itcube.journal.model.Student;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StudentMapper {

    Student toEntity(StudentRequestDTO studentRequestDTO);

    StudentResponseDTO toResponseDTO(Student student);

    StudentDTO toDTO(Student student);
}
