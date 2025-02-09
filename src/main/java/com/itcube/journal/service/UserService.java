package com.itcube.journal.service;

import com.itcube.journal.model.User;
import com.itcube.journal.repos.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepo userRepo;

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

    public Iterable<User> findAll() {
        return userRepo.findAll();
    }

    public List<User> findByUserNameOrEmail(String username) {

        return users.stream()
                .filter(x -> x.getUsername().equalsIgnoreCase(username))
                .collect(Collectors.toList());

    }

    public User save(User user) {
        return userRepo.save(user);
    }

    public User findOne(String username) {
        return userRepo.findByUsername(username);
    }

    // TODO: Добавить метод для добавления пользователей в систему
}
