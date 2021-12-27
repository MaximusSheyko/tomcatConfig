package com.example.tomcatconfig.service;


import com.example.tomcatconfig.model.User;

import java.util.List;

public interface UserService {
    boolean save(User user);

    User findByLogin(String login);

    List<User> getAllUsers();
}
