package com.itcube.journal.repos;

import com.itcube.journal.domain.Groups;
import com.itcube.journal.domain.ThematicPlanning;
import com.itcube.journal.domain.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface GroupsRepo extends CrudRepository<Groups, Long> {
    List<Groups> findGroupsByGroupName(String groupName);

    List<Groups> findById(Integer id);

    List<Groups> findByUser(User user);
}
