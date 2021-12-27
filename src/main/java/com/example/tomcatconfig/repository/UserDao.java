package com.example.tomcatconfig.repository;

import com.example.tomcatconfig.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User, Integer> {
    User findByLogin(String login);
}
