package com.itcube.journal.repos;

import com.itcube.journal.domain.StudentsAttendance;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StudentsAttendanceRepo extends CrudRepository<StudentsAttendance, Integer> {

    @Query(value = "select new com.itcube.journal.domain.StudentsAttendance(s.students, s.dates, s.mark) from StudentsAttendance s")
    List<StudentsAttendance> findByDateAndMark(@Param("student_id") Integer id);
}
