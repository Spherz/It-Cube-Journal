package com.itcube.journal.repos;

import com.itcube.journal.domain.Students;
import com.itcube.journal.domain.StudentsAttendance;
import com.itcube.journal.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Set;

public interface StudentsRepo extends CrudRepository<Students, Long> {
    Page<Students> findAll(Pageable pageable);

    Page<Students> findAllBySurname(String surname, Pageable pageable);

    Page<Students> findByStaff_Surname(String staffSurname, Pageable pageable);
    Page<Students> findByNameGroup_GroupName(String groupName, Pageable pageable);

    List<Students> findByNameGroup_GroupName(String groupName);

    @Query(value = "select surname, firstname, secondname from Students where course.id = :id")
    List<Students> findByCourse(@Param("id") Integer id);

    @Query(value = "select new com.itcube.journal.domain.Students(s.id, s.surname, s.firstname, s.secondname) from Students s where s.nameGroup.groupName = :groupName")
    List<Students> findByNameGroup(@Param("groupName") String groupName);
    //    Page<Students> findByUser(User user);
}
