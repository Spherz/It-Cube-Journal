package com.itcube.journal.dto.attendance;

import java.time.LocalDate;

public record AttendanceRequestDTO(LocalDate attendanceDate, String mark) {
}
