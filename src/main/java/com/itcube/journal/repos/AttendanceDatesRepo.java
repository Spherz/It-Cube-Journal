package com.itcube.journal.repos;

import com.itcube.journal.domain.Attendance;
import com.itcube.journal.domain.AttendanceDates;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AttendanceDatesRepo extends CrudRepository<AttendanceDates, Integer> {

    @Query("select lessonDate from AttendanceDates where groups.groupName = :name")
    List<AttendanceDates> findByGroups_GroupName (@Param("name") String name);
}
