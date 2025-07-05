package com.itcube.journal.dto.course;

import com.itcube.journal.dto.groups.GroupRequestDTO;

import java.util.List;

public record CourseRequestDTO(String courseName, List<GroupRequestDTO> groups) {
}
