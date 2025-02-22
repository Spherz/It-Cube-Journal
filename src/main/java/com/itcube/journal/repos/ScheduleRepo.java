package com.itcube.journal.repos;

import com.itcube.journal.model.Groups;
import com.itcube.journal.model.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScheduleRepo extends JpaRepository<Schedule, Long> {
    List<Schedule> findByGroups(Groups groups);
}
