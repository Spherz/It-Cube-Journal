package com.itcube.journal.repos;

import com.itcube.journal.domain.Attendance;
import com.itcube.journal.domain.AttendanceDates;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AttendanceDatesRepo extends CrudRepository<AttendanceDates, Long> {
    List<AttendanceDates> findById(Integer id);
}
