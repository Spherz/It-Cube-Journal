package com.itcube.journal.dto.schedule;

import java.time.LocalDate;

public record ScheduleExceptionRequestDTO(LocalDate exceptionDate, String reason) {
}
