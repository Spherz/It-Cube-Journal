package com.itcube.journal.dto.staff;

import com.itcube.journal.dto.groups.GroupDTO;

import java.time.LocalDateTime;
import java.util.List;

public record EmployeeRequestDTO(String firstName, String surname,
                                 String patronymic, LocalDateTime birthDate,
                                 String education, Integer diplomaNumber,
                                 String qualification, List<GroupDTO> groups) {

}
