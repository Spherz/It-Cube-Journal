package com.itcube.journal.repos;

import com.itcube.journal.model.AttendanceDate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttendanceDateRepo extends JpaRepository<AttendanceDate, Long> {
}
