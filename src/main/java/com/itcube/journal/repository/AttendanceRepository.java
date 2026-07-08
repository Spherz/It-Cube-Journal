package com.itcube.journal.repository;

import com.itcube.journal.model.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface AttendanceRepository extends JpaRepository<Attendance, Long> {

    List<Attendance> findByStudentsId(Long studentId);

    List<Attendance> findByGroupsId(Long groupId);

    Optional<Attendance> findByStudentsIdAndAttendanceDate(Long studentId, LocalDate attendanceDate);
}
