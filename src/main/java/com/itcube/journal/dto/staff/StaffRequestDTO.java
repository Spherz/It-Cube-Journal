package com.itcube.journal.dto.staff;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
    private List<String> staffGroup = new ArrayList<>();
}
