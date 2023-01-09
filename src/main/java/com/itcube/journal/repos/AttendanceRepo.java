package com.itcube.journal.repos;

import com.itcube.journal.domain.Attendance;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AttendanceRepo extends CrudRepository<Attendance, Long> {

    List<Attendance> findByStudents_Surname(String surname);

    List<Attendance> findByGroups_GroupName(String group);

    List<Attendance> findByDate(String date);

}
