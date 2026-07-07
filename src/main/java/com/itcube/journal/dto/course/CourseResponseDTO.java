package com.itcube.journal.dto.course;

import com.itcube.journal.dto.groups.GroupResponseDTO;

import java.util.List;

public record CourseResponseDTO(Long id, String courseName,
                                List<GroupResponseDTO> groups) {
}
