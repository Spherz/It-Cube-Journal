package com.itcube.journal.dto.groups;

import java.time.LocalDateTime;
import java.util.List;

public record GroupRequestDTO(String name,
                              Integer hours, Integer decreeNumber,
                              LocalDateTime decreeDate, String educationForm,
                              Long courseId, List<Long> studentIds) {
}
