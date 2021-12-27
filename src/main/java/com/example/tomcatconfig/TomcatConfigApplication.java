package com.example.tomcatconfig;

import com.example.tomcatconfig.model.User;
import com.example.tomcatconfig.repository.UserDao;
import com.example.tomcatconfig.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class TomcatConfigApplication {

    public static void main(String[] args) {
        SpringApplication.run(TomcatConfigApplication.class,args);
        System.out.println("sdsd");
    }
}
