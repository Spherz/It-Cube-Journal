package com.itcube.journal.repos;

import com.itcube.journal.domain.Students;
import com.itcube.journal.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface StudentsRepo extends CrudRepository<Students, Long> {
    Page<Students> findAll(Pageable pageable);

    Page<Students> findAllBySurname(String surname, Pageable pageable);

    Page<Students> findByStaff_Surname(String staffSurname, Pageable pageable);
    Page<Students> findByNameGroup_GroupName(String groupName, Pageable pageable);

    List<Students> findByNameGroup_GroupName(String groupName);



//    Page<Students> findByUser(User user);
}
