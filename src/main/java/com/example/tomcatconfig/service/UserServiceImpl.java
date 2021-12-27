package com.example.tomcatconfig.service;

import com.example.tomcatconfig.model.Role;
import com.example.tomcatconfig.model.User;
import com.example.tomcatconfig.repository.RoleDao;
import com.example.tomcatconfig.repository.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class UserServiceImpl implements UserService{

    private static final String DATE_FORMAT = "E, dd MMM yyyy hh:mm:ss";

    private final UserDao userDao;

    private final RoleDao roleDao;

    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserDao userDao,RoleDao roleDao,BCryptPasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.roleDao = roleDao;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public boolean save(User user) {
        User newUser = findByLogin(user.login);
        if (Objects.isNull(newUser)){
            Set<Role> roles = new HashSet<>();
            roles.add(roleDao.getById(1));
            user.setRoles(roles);
            user.setPassword(passwordEncoder.encode(user.getConfirmPassword()));
            user.setDateRegistration(registerDateInFormat());
            userDao.save(user);
            return true;
        }
        return false;
    }


    @Override
    public User findByLogin(String login) {
        return userDao.findByLogin(login);
    }

    @Override
    public List<User> getAllUsers() {
        return userDao.findAll();
    }

    private static LocalDateTime registerDateInFormat(){
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT, Locale.ENGLISH);
        return now;
    }

    public User findById(int id) throws UserNotFoundException {
        return Optional.of(userDao.findById(id))
                .get().orElseThrow(() -> new UserNotFoundException("User not found"));
    }

    public void deleteById(int id){
        userDao.deleteById(id);
    }


    public void updateDateLastActivityByLogin(String login){
        User user = userDao.findByLogin(login);
        user.setDateLastActivity(registerDateInFormat());
        userDao.save(user);
    }
}
