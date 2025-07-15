package com.itcube.journal.mapper.student;

import com.itcube.journal.dto.student.StudentDTO;
import com.itcube.journal.dto.student.StudentRequestDTO;
import com.itcube.journal.dto.student.StudentResponseDTO;
import com.itcube.journal.model.Student;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface StudentMapper {

    Student toEntity(StudentRequestDTO studentRequestDTO);

    StudentResponseDTO toResponseDTO(Student student);

    StudentDTO toDTO(Student student);

    void updateFromDTO(StudentRequestDTO studentRequestDTO, @MappingTarget Student student);
}
