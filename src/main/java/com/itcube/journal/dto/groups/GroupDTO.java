package com.itcube.journal.dto.groups;

import java.time.LocalDateTime;

public record GroupDTO(Long id, String name,
                       Integer hours,
                       Integer decreeNumber, LocalDateTime decreeDate,
                       String educationForm, Integer studentCount) {
}
