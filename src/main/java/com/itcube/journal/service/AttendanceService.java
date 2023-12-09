package com.itcube.journal.service;

import com.itcube.journal.domain.Attendance;
import com.itcube.journal.repos.AttendanceRepo;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class AttendanceService {

    private AttendanceRepo attendanceRepo;

    public AttendanceService(AttendanceRepo attendanceRepo) {
        this.attendanceRepo = attendanceRepo;
    }

    public List<Attendance> getAttendanceByDate(LocalDate date) {
        return attendanceRepo.findAttendanceByAttendanceDate(date);
    }

//    public void markAttendance(Long studentId, LocalDate date, boolean present) {
//        attendanceRepo.save(new Attendance(studentId, date, present));
//    }
}
