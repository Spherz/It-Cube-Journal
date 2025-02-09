package com.itcube.journal.service;

import com.itcube.journal.model.Attendance;
import com.itcube.journal.repos.AttendanceRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AttendanceService {

    private final AttendanceRepo attendanceRepo;

    public Iterable<Attendance> findAll() {
        return attendanceRepo.findAll();
    }
    public List<Attendance> getAttendanceByDate(LocalDate date) {
        return attendanceRepo.findAttendanceByAttendanceDate(date);
    }

}
