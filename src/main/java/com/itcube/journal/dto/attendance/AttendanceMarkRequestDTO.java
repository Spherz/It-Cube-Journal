package com.itcube.journal.dto.attendance;

import com.itcube.journal.enums.AttendanceMark;

import java.time.LocalDate;

public record AttendanceMarkRequestDTO(LocalDate attendanceDate, AttendanceMark mark) {
}
