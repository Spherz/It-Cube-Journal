package com.itcube.journal.repository;

import com.itcube.journal.model.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface GroupRepository extends JpaRepository<Group, Long> {

    @Query("SELECT g FROM Group g LEFT JOIN FETCH g.course")
    List<Group> findAllWithCourse();

    @Query("SELECT g FROM Group g LEFT JOIN FETCH g.course WHERE g.id = :id")
    Optional<Group> findByIdWithCourse(@Param("id") Long id);

    List<Group> findByEmployeeSub(String employeeSub);
}
