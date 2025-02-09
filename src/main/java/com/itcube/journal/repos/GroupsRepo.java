package com.itcube.journal.repos;

import com.itcube.journal.model.Groups;
import com.itcube.journal.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface GroupsRepo extends CrudRepository<Groups, Long> {
    List<Groups> findGroupsByGroupName(String groupName);
    Optional<Groups> findById(Integer id);
    List<Groups> findByUser(User user);
    @Query(value = "select groupName from Groups where course.id = :id")
    List<Groups> findByCourse(@Param("id") Integer id);
}
