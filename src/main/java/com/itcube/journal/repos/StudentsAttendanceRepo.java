package com.itcube.journal.repos;

import com.itcube.journal.domain.StudentsAttendance;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StudentsAttendanceRepo extends CrudRepository<StudentsAttendance, Integer> {

}
