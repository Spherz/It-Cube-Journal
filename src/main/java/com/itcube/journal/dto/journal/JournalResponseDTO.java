package com.itcube.journal.dto.journal;

import com.itcube.journal.dto.student.StudentResponseDTO;

import java.time.LocalDate;
import java.util.List;

public record JournalResponseDTO(
        Long groupId,
        String groupName,
        List<LocalDate> lessonDates,
        List<StudentResponseDTO> students) {
}
