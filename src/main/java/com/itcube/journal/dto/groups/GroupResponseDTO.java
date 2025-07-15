package com.itcube.journal.dto.groups;

import java.time.LocalDateTime;

public record GroupResponseDTO(Long id, String name,
                               String programName, Integer hours,
                               Integer decreeNumber, LocalDateTime decreeDate,
                               String educationForm) {
}
