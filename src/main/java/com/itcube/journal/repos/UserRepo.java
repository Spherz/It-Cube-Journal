package com.itcube.journal.repos;

import com.itcube.journal.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepo extends JpaRepository<User, Long> {
    User findByUsername(String username);

    User findByStaff_Surname(String staffSurname);

    List<User> findById(Integer id);

    User findByStaffTeacherGroupsGroupName(String groupName);

    User findByThemeNameThemeName(String themeName);


}
