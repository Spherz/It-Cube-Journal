package com.itcube.journal.dto.schedule;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Set;

public record ScheduleResponseDTO(Long id, Set<DayOfWeek> daysOfWeek,
                                  LocalTime startTime, LocalTime endTime,
                                  LocalDate academicYearStart, LocalDate academicYearEnd,
                                  Integer lessonsPerWeek,
                                  Long groupId,
                                  List<ScheduleExceptionDTO> exceptions) {
}
