package com.itcube.journal.mapper.students;

import com.itcube.journal.dto.students.StudentsRequestDTO;
import com.itcube.journal.dto.students.StudentsResponseDTO;
import com.itcube.journal.model.Students;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;

@Component
public class StudentsMapper {

    public Students updateStudentsFromDTO(StudentsRequestDTO studentsRequestDTO, Students students) {
        students.setFirstname(studentsRequestDTO.getFirstname() == null ? students.getFirstname() : studentsRequestDTO.getFirstname());
        students.setSurname(studentsRequestDTO.getSurname() == null ? students.getSurname() : studentsRequestDTO.getSurname());
        students.setSecondname(studentsRequestDTO.getSecondname() == null ? students.getSecondname() : studentsRequestDTO.getSecondname());
        students.setParent(studentsRequestDTO.getParent() == null ? students.getParent() : studentsRequestDTO.getParent());
        students.setStudentClass(studentsRequestDTO.getStudentClass() == null ? students.getStudentClass() : studentsRequestDTO.getStudentClass());
        students.setSchool(studentsRequestDTO.getSchool() == null ? students.getSchool() : studentsRequestDTO.getSchool());
        students.setPhoneNumber(studentsRequestDTO.getPhoneNumber() == null ? students.getPhoneNumber() : studentsRequestDTO.getPhoneNumber());
        students.setEmail(studentsRequestDTO.getEmail() == null ? students.getEmail() : studentsRequestDTO.getEmail());

        return students;
    }

    public Students mapStudentsRequestDTOToStudents(StudentsRequestDTO studentsRequestDTO) {
        Students students = new Students();
        students.setFirstname(studentsRequestDTO.getFirstname());
        students.setSurname(studentsRequestDTO.getSurname());
        students.setSecondname(studentsRequestDTO.getSecondname());
        students.setDateOfBirth(studentsRequestDTO.getDateOfBirth());
        students.setCertificateNumber(studentsRequestDTO.getCertificateNumber());
        students.setParent(studentsRequestDTO.getParent());
        students.setSchool(studentsRequestDTO.getSchool());
        students.setStudentClass(studentsRequestDTO.getStudentClass());
        students.setPhoneNumber(studentsRequestDTO.getPhoneNumber());
        students.setEmail(studentsRequestDTO.getEmail());
        return students;
    }

    public StudentsResponseDTO mapStudentsToStudentsResponseDTO(Students students) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        StudentsResponseDTO studentsResponseDTO = new StudentsResponseDTO();
        studentsResponseDTO.setFirstname(students.getFirstname());
        studentsResponseDTO.setSurname(students.getSurname());
        studentsResponseDTO.setSecondname(students.getSecondname());
        studentsResponseDTO.setDateOfBirth(students.getDateOfBirth().format(formatter));
        studentsResponseDTO.setCertificateNumber(students.getCertificateNumber());
        studentsResponseDTO.setParent(students.getParent());
        studentsResponseDTO.setSchool(students.getSchool());
        studentsResponseDTO.setStudentClass(students.getStudentClass());
        studentsResponseDTO.setPhoneNumber(students.getPhoneNumber());
        studentsResponseDTO.setEmail(students.getEmail());
        return studentsResponseDTO;
    }
}
