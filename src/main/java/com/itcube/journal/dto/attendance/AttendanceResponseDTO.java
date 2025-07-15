package com.itcube.journal.dto.attendance;

import java.time.LocalDate;

public record AttendanceResponseDTO(Long id, LocalDate attendanceDate,
                                    String mark) {
}
