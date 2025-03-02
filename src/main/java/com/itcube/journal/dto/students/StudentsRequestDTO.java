package com.itcube.journal.dto.students;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class StudentsRequestDTO {
    private String firstname;
    private String surname;
    private String secondname;
    @DateTimeFormat(pattern = "dd.MM.yyyy")
    private LocalDate dateOfBirth;
    private String certificateNumber;
    private String parent;
    private String studentClass;
    private String school;
    private String phoneNumber;
    private String email;
}
