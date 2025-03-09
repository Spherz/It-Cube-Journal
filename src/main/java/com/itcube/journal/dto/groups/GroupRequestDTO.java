package com.itcube.journal.dto.groups;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class GroupRequestDTO {

    private String groupName;
    private String programName;
    private String hours;
    private String teacher;
    private String decreeNumber;
    private String decreeDate;
    private String educationForm;
}
