package com.itcube.journal.service;

import com.itcube.journal.model.Course;
import com.itcube.journal.repos.CourseRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseService {
    @Autowired
    private CourseRepo courseRepo;

    public Iterable<Course> findAll() {
        return courseRepo.findAll();
    }

    public Course find(Integer id) {
        return courseRepo.findById(id).get();
    }
}
