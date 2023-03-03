package com.itcube.journal.service;

import com.itcube.journal.domain.StudentsAttendance;
import com.itcube.journal.repos.StudentsAttendanceRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentsAttendanceService {
    @Autowired
    private StudentsAttendanceRepo studentsAttendanceRepo;

    public List<StudentsAttendance> findByMarkAndDate(Integer id) { return studentsAttendanceRepo.findByDateAndMark(id); }
}
