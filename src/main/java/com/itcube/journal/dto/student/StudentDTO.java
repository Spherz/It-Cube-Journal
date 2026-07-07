package com.itcube.journal.dto.student;

public record StudentDTO(Long id, String firstname,
                         String surname, String patronymic,
                         Long groupId, String groupName) {

}
