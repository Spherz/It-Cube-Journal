package com.itcube.journal.dto.student;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public record StudentResponseDTO(
        Long id,
        String firstname,
        String surname,
        String patronymic,
        @DateTimeFormat(pattern = "dd.MM.yyyy")
        LocalDate dateOfBirth,
        String certificateNumber,
        String parent,
        String studentClass,
        String school,
        String phoneNumber,
        String email){
}
