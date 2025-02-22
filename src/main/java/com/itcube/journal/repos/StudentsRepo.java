package com.itcube.journal.repos;

import com.itcube.journal.model.Students;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StudentsRepo extends JpaRepository<Students, Integer> {
    Page<Students> findAll(Pageable pageable);

    Page<Students> findAllBySurname(String surname, Pageable pageable);

    Page<Students> findByStaff_Surname(String staffSurname, Pageable pageable);
    Page<Students> findByNameGroup_GroupName(String groupName, Pageable pageable);

    List<Students> findByNameGroup_GroupName(String groupName);

    @Query(value = "select surname, firstname, secondname " +
            "from Students " +
            "where course.id = :id")
    List<Students> findByCourse(@Param("id") Integer id);

    Page<Students> findAll(Specification<Students> specification, Pageable pageable);
}
