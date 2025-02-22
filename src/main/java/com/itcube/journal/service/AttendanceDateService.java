package com.itcube.journal.service;

import com.itcube.journal.model.AttendanceDate;
import com.itcube.journal.repos.AttendanceDateRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class AttendanceDateService {

    private final AttendanceDateRepo attendanceDateRepo;

    public List<AttendanceDate> getAttendanceDates() {
        return attendanceDateRepo.findAll();
    }

    public List<String> getFormattedAttendanceDates() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        return attendanceDateRepo.findAll().stream()
                .map(date -> date.getLessonDate().format(formatter))
                .collect(Collectors.toList());
    }
}
