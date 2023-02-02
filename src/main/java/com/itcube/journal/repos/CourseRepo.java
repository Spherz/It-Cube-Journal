package com.itcube.journal.repos;

import com.itcube.journal.domain.Course;
import org.springframework.data.repository.CrudRepository;

public interface CourseRepo extends CrudRepository<Course, Integer> {
}
