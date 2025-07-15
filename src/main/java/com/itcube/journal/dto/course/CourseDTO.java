package com.itcube.journal.dto.course;

import com.itcube.journal.model.Group;

import java.util.List;

public record CourseDTO(Long id, String courseName,
                        List<Group> groups) {
}
