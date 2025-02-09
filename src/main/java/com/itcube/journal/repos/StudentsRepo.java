package com.itcube.journal.repos;

import com.itcube.journal.model.Students;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StudentsRepo extends CrudRepository<Students, Long> {
    Page<Students> findAll(Pageable pageable);

    Page<Students> findAllBySurname(String surname, Pageable pageable);

    Page<Students> findByStaff_Surname(String staffSurname, Pageable pageable);
    Page<Students> findByNameGroup_GroupName(String groupName, Pageable pageable);

    List<Students> findByNameGroup_GroupName(String groupName);

    @Query(value = "select surname, firstname, secondname " +
            "from Students " +
            "where course.id = :id")
    List<Students> findByCourse(@Param("id") Integer id);

//    @Query(value = "select s.id, s.surname, s.firstname, s.secondname, m " +
//            "from Students s " +
//            "join s.attendance m on s.id = m.student.id " +
//            "where s.nameGroup.groupName = :groupName")
//    List<Students> findByNameGroup(@Param("groupName") String groupName);

//    @Query(value = "select new com.itcube.journal.domain.Students(s.id, s.surname, s.firstname, s.secondname, m) from Students s join s.attendance m on s.id = m.student.id where s.nameGroup.groupName = :groupName")
//    List<Students> findByNameGroup(@Param("groupName") String groupName);

    //    Page<Students> findByUser(User user);
}
