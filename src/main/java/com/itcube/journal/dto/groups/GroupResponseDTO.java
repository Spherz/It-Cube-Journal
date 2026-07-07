package com.itcube.journal.dto.groups;

import java.time.LocalDateTime;

public record GroupResponseDTO(Long id, String name,
                               Integer hours,
                               Integer decreeNumber, LocalDateTime decreeDate,
                               String educationForm,
                               Long courseId, String courseName,
                               Integer studentCount) {
}
