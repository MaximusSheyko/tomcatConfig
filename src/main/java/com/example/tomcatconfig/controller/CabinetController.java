package com.example.tomcatconfig.controller;

import com.example.tomcatconfig.model.Message;
import com.example.tomcatconfig.model.User;
import com.example.tomcatconfig.service.MessageService;
import com.example.tomcatconfig.service.SecurityServiceImpl;
import com.example.tomcatconfig.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/cabinet")
public class CabinetController {

    private MessageService messageService;

    private UserServiceImpl userService;

    private SecurityServiceImpl securityService;

    @Autowired
    public CabinetController(MessageService messageService,UserServiceImpl userService, SecurityServiceImpl securityService) {
        this.messageService = messageService;
        this.userService = userService;
        this.securityService = securityService;
    }

    @RequestMapping()
    public String cabinet(Model model){
        return "cabinet";
    }

    @ModelAttribute("messages")
    public List<Message> messages(){
        User user = userService.findByLogin(securityService.findLoggedInUsername());
        return user.getMessages();
    }
}
