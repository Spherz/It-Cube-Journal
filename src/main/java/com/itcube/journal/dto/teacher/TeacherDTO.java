package com.itcube.journal.dto.teacher;

public record TeacherDTO(String sub, String username, String email, String firstName, String lastName,
                          String patronymic, String birthDate, String education, String qualification,
                          Integer diplomaNumber) {
}
