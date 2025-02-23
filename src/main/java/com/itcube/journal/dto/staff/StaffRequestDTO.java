package com.itcube.journal.dto.staff;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class StaffRequestDTO {
    private String firstName;
    private String surname;
    private String fatherName;
    private LocalDate birthDate;
    private String education;
    private String diplomaNumber;
    private String qualification;
}
