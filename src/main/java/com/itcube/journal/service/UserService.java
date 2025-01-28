package com.itcube.journal.service;

import com.itcube.journal.model.User;
import com.itcube.journal.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepo userRepo;

//    @Autowired
//    private PasswordEncoder passwordEncoder;

    List<User> users;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepo.findByUsername(username);
    }

    public UserDetails loadByStaffSurname(String staffSurname) throws UsernameNotFoundException {
        return userRepo.findByStaff_Surname(staffSurname);
    }

    public List<User> findByUserNameOrEmail(String username) {

        List<User> result = users.stream()
                .filter(x -> x.getUsername().equalsIgnoreCase(username))
                .collect(Collectors.toList());

        return result;

    }

    public User findOne(String username) {
        return userRepo.findByUsername(username);
    }

    // TODO: Добавить метод для добавления пользователей в систему
}
