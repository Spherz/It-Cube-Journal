package com.itcube.journal.service;

import com.itcube.journal.model.User;
import com.itcube.journal.repository.UserRepoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepoRepository userRepoRepository;

    List<User> users;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepoRepository.findByUsername(username);
    }

    public List<User> findAll() {
        return userRepoRepository.findAll();
    }

    public List<User> findByUserNameOrEmail(String username) {

        return users.stream()
                .filter(x -> x.getUsername().equalsIgnoreCase(username))
                .collect(Collectors.toList());

    }

    public User save(User user) {
        return userRepoRepository.save(user);
    }

    public User findOne(String username) {
        return userRepoRepository.findByUsername(username);
    }

}
