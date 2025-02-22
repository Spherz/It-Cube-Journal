package com.itcube.journal.service;

import com.itcube.journal.model.Attendance;
import com.itcube.journal.model.AttendanceDate;
import com.itcube.journal.model.Groups;
import com.itcube.journal.model.Schedule;
import com.itcube.journal.repos.AttendanceDateRepo;
import com.itcube.journal.repos.AttendanceRepo;
import com.itcube.journal.repos.ScheduleRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AttendanceService {

    private final ScheduleRepo scheduleRepo;
    private final AttendanceRepo attendanceRepo;
    private final AttendanceDateRepo attendanceDateRepo;

    public Iterable<Attendance> findAll() {
        return attendanceRepo.findAll();
    }

    public Attendance findByStudentAndDate(Integer studentId, LocalDate attendanceDate) {
        return attendanceRepo.findByStudentsIdAndAttendanceDate(studentId, attendanceDate);
    }

    public void generateAttendanceDates(Groups group, int year) {
        List<Schedule> schedules = scheduleRepo.findByGroups(group);
        List<AttendanceDate> attendanceDates = new ArrayList<>();

        for (Schedule schedule : schedules) {
            LocalDate startDate = LocalDate.of(year, 9, 3);
            LocalDate endDate = LocalDate.of(year + 1, 6, 30);

            LocalDate date = startDate;

            while (date.isBefore(endDate)) {
                if(date.getDayOfWeek() == schedule.getDayOfWeek()) {
                    AttendanceDate attendanceDate = new AttendanceDate();
                    attendanceDate.setGroup(group);
                    attendanceDate.setLessonDate(date);
                    attendanceDates.add(attendanceDate);
                }
                date = date.plusDays(1);
            }
        }

        attendanceDateRepo.saveAll(attendanceDates);
    }

    public Attendance save(Attendance attendance) {
        return attendanceRepo.save(attendance);
    }

}
