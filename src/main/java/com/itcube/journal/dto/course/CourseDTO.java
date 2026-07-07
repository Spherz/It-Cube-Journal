package com.itcube.journal.dto.course;

import com.itcube.journal.dto.groups.GroupDTO;

import java.util.List;

public record CourseDTO(Long id, String courseName,
                        List<GroupDTO> groups) {
}
