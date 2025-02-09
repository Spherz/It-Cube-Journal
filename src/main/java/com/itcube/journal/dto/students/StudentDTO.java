package com.itcube.journal.dto.students;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class StudentDTO {
    private Integer id;
    private String firstname;
    private String surname;
    private String secondname;
}
