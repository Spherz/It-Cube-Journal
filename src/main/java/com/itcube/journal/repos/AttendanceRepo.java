package com.itcube.journal.repos;

import com.itcube.journal.model.Attendance;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.List;

public interface AttendanceRepo extends CrudRepository<Attendance, Integer> {

    List<Attendance> findByStudents_Surname(String surname);

    List<Attendance> findByGroups_GroupName(String group);

    List<Attendance> findAttendanceByAttendanceDate(LocalDate date);

}
