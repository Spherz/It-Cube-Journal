package com.itcube.journal.repos;

import com.itcube.journal.domain.Attendance;
import com.itcube.journal.domain.ThematicPlanning;
import com.itcube.journal.domain.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AttendanceRepo extends CrudRepository<Attendance, Integer> {

    List<Attendance> findByStudents_Surname(String surname);

    List<Attendance> findByGroups_GroupName(String group);

    List<Attendance> findByDate(String date);

}
