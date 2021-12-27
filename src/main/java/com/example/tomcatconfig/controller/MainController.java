package com.example.tomcatconfig.controller;

import com.example.tomcatconfig.service.SecurityServiceImpl;
import com.example.tomcatconfig.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

@Controller
@RequestMapping()
public class MainController {

    private final UserServiceImpl userService;

    private final SecurityServiceImpl securityService;

    @Autowired
    public MainController(UserServiceImpl userService,SecurityServiceImpl securityService) {
        this.userService = userService;
        this.securityService = securityService;
    }

    @RequestMapping("/login")
    public String greeting(@RequestParam(value = "error", required = false) String error,
                           @RequestParam(value = "logout", required = false) String logout,
                           Model model){
        model.addAttribute( "error",Objects.nonNull(error));
        model.addAttribute( "logout", Objects.nonNull(logout));
        return "login";
    }

    @RequestMapping("/logout")
    public String logout(HttpServletRequest request,HttpServletResponse response, SecurityContextLogoutHandler logoutHandler){
        if (securityService.authenticationIsNotAnonymous()){
            String login = securityService.findLoggedInUsername().trim();
            userService.updateDateLastActivityByLogin(login);
            securityService.closeSession(request, response, logoutHandler);
            return "redirect:/login?logout";
        }
        return "login";
    }
}
