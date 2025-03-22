package com.itcube.journal.dto.course;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class CourseRequestDTO {
    private String courseName;
    private List<String> courseGroup = new ArrayList<>();
}
