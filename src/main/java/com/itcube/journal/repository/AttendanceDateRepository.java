package com.itcube.journal.repository;

import com.itcube.journal.model.AttendanceDate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttendanceDateRepository extends JpaRepository<AttendanceDate, Long> {
}
