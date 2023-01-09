package com.itcube.journal.service;

import com.itcube.journal.domain.User;
import com.itcube.journal.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepo userRepo;

//    @Autowired
//    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepo.findByUsername(username);
    }

    public UserDetails loadByStaffSurname(String staffSurname) throws UsernameNotFoundException {
        return userRepo.findByStaff_Surname(staffSurname);
    }

    public User findOne(String username) {
        return userRepo.findByUsername(username);
    }

    // TODO: Добавить метод для добавления пользователей в систему
}
