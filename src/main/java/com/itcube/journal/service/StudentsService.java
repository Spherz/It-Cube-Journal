package com.itcube.journal.service;

import com.itcube.journal.model.Students;
import com.itcube.journal.repos.StudentsRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentsService {

    private final StudentsRepo studentsRepo;

    public Iterable<Students> findAll() {
        return studentsRepo.findAll();
    }

    public List<Students> findByCourse(Integer id) {
        return studentsRepo.findByCourse(id);
    }

//    public List<Students> findByNameGroup(String name) { return studentsRepo.findByNameGroup(name); }
}
