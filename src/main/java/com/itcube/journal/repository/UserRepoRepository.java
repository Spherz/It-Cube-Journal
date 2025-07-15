package com.itcube.journal.repository;

import com.itcube.journal.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepoRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
