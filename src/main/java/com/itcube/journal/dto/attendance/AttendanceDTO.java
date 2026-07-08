package com.itcube.journal.dto.attendance;

import com.itcube.journal.enums.AttendanceMark;

import java.time.LocalDate;

public record AttendanceDTO(Long id, LocalDate attendanceDate,
                            AttendanceMark mark,
                            Long studentId, Long groupId) {
}
