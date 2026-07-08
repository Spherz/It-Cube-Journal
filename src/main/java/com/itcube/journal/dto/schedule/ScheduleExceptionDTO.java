package com.itcube.journal.dto.schedule;

import java.time.LocalDate;

public record ScheduleExceptionDTO(Long id, LocalDate exceptionDate, String reason) {
}
