package com.example.tomcatconfig.controller;

import com.example.tomcatconfig.model.User;
import com.example.tomcatconfig.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@Controller
@RequestMapping("/registration")
public class RegistrationController {

    private final UserServiceImpl userService;

    @Autowired
    public RegistrationController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @GetMapping()
    public String registration(@RequestParam(value = "error", required = false) String error, Model model){
        model.addAttribute("error",Objects.nonNull(error));
        model.addAttribute("user", new User());
        return "registration";
    }

    @PostMapping()
    public String createUser(@ModelAttribute("user")User user,Model model){
        if (!checkEqualsPasswords(user)){
            model = messageError(model, "Password mismatch");
        }
        else if (!userService.save(user)){
            model = messageError(model, "User is already exist");
        }
        return "registration";
    }

    private Model messageError(Model model, String message){
        return model.addAttribute( "error", message);
    }

    private Boolean checkEqualsPasswords(User user){
        return user.getPassword().equals(user.getConfirmPassword());
    }
}
