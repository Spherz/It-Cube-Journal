package com.itcube.journal.service;

import com.itcube.journal.domain.Students;
import com.itcube.journal.repos.StudentsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentsService {

    @Autowired
    private StudentsRepo studentsRepo;

    public List<Students> findByCourse(Integer id) {
        return studentsRepo.findByCourse(id);
    }

    public List<Students> findByNameGroup(String name) { return studentsRepo.findByNameGroup(name); }

//    public List<Students> findByMark(Integer id) { return studentsRepo.findByMark(id); }
}
