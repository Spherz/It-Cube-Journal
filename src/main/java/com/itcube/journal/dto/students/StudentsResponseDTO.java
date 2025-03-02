package com.itcube.journal.dto.students;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class StudentsResponseDTO {
    private String firstname;
    private String surname;
    private String secondname;
    private String dateOfBirth;
    private String certificateNumber;
    private String parent;
    private String studentClass;
    private String school;
    private String phoneNumber;
    private String email;
}
