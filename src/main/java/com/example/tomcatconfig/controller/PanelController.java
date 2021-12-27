package com.example.tomcatconfig.controller;

import com.example.tomcatconfig.model.Message;
import com.example.tomcatconfig.model.User;
import com.example.tomcatconfig.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping({"/panel", "/"})
public class PanelController {

    private final UserServiceImpl userService;

    private final SecurityServiceImpl securityService;

    private final MessageServiceImpl messageService;

    @Autowired
    public PanelController(UserServiceImpl userService, SecurityServiceImpl securityService, MessageServiceImpl messageService) {
        this.userService = userService;
        this.securityService = securityService;
        this.messageService = messageService;
    }

    @GetMapping()
    public String usersTable(Model model){
        model.addAttribute( "message", new Message());
        return "users_table";
    }

    @PostMapping()
    public String selectLoginUser(Model model){
        return "redirect:/";
    }


    @PostMapping("/delete/{id}")
    public String delete(@PathVariable("id") int id) {
        userService.deleteById(id);
        return "redirect:/panel";
    }

    @Secured( "ADMIN" )
    @PostMapping("/block/{id}")
    public String block(@PathVariable("id") int id){
        return "redirect:/panel";
    }

    @Secured( "ADMIN" )
    @PostMapping("/unblock/{id}")
    public String unblock(@PathVariable("id") int id){
        return "redirect:/panel";
    }

    @GetMapping("/{id}/new-message")
    public String edit(Model model, @PathVariable("id") int id) throws UserNotFoundException {
        model.addAttribute( "message", new Message());
        model.addAttribute( "recipientId", id);
        model.addAttribute("recipient", userService.findById(id));
        model.addAttribute("sender", userService.findByLogin(securityService.findLoggedInUsername()));
        return "/new-message";
    }

    @PostMapping("/send")
    public String sendMessage(@ModelAttribute("message") Message message, Model model) throws UserNotFoundException {
        if (model.getAttribute( "recipientId" ) instanceof Integer){
            Integer id = (Integer) model.getAttribute("recipientId");
            message.setRecipientId(userService.findById(id));
        }
        if (model.getAttribute( "sender" ) instanceof User){
            User sender = (User) model.getAttribute("sender");
            message.setSender(sender.getId());
        }
        messageService.saveMessage(message);
        return "redirect:/panel";
    }

    @ModelAttribute("users")
    public List<User> users(){
        return userService.getAllUsers();
    }

}