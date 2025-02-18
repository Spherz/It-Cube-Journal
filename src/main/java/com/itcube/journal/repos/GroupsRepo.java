package com.itcube.journal.repos;

import com.itcube.journal.domain.Groups;
import com.itcube.journal.domain.Students;
import com.itcube.journal.domain.ThematicPlanning;
import com.itcube.journal.domain.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface GroupsRepo extends CrudRepository<Groups, Long> {
    List<Groups> findGroupsByGroupName(String groupName);
    List<Groups> findById(Integer id);
    List<Groups> findByUser(User user);
    @Query(value = "select groupName from Groups where course.id = :id")
    List<Groups> findByCourse(@Param("id") Integer id);
}
