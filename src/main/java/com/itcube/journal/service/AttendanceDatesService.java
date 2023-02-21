package com.itcube.journal.service;

import com.itcube.journal.domain.AttendanceDates;
import com.itcube.journal.repos.AttendanceDatesRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AttendanceDatesService {
    @Autowired
    private AttendanceDatesRepo attendanceDatesRepo;

    public List<AttendanceDates> findByGroups_GroupName(String name) { return attendanceDatesRepo.findByGroups_GroupName(name); }

}
