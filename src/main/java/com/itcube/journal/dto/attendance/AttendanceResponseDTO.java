package com.itcube.journal.dto.attendance;

import com.itcube.journal.enums.AttendanceMark;

import java.time.LocalDate;

public record AttendanceResponseDTO(Long id, LocalDate attendanceDate,
                                    AttendanceMark mark,
                                    Long studentId, String studentFirstname, String studentSurname,
                                    Long groupId, String groupName) {
}
