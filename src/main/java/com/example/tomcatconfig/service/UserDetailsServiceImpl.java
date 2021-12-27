package com.example.tomcatconfig.service;

import com.example.tomcatconfig.dto.RoleStatus;
import com.example.tomcatconfig.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private UserServiceImpl userService;

    private BCryptPasswordEncoder encoder;

    @Autowired
    public UserDetailsServiceImpl(UserServiceImpl userService, BCryptPasswordEncoder encoder) {
        this.userService = userService;
        this.encoder = encoder;
    }

    @Override
    public UserDetails loadUserByUsername(String name) {
        User user = userService.findByLogin(name);
        UserDetails details = org.springframework.security.core.userdetails.User.builder()
                .username(user.getLogin().trim())
                .password(user.getPassword().trim())
                .authorities(RoleStatus.USER.name())
                .build();
        return Optional.of(details).orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}
