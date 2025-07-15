package com.itcube.journal.dto.attendance;

import java.time.LocalDate;

public record AttendanceDTO(Long id, LocalDate attendanceDate,
                            String mark) {
}
