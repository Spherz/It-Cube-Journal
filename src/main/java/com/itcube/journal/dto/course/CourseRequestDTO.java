package com.itcube.journal.dto.course;

import java.util.List;

public record CourseRequestDTO(String courseName, List<Long> groupIds) {
}
