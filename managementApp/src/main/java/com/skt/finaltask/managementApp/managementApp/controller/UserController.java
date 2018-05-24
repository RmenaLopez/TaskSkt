package com.skt.finaltask.managementApp.managementApp.controller;

import com.skt.finaltask.commonLibrary.model.User;
import com.skt.finaltask.managementApp.managementApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class UserController {

    @Autowired
    UserService service;

    @GetMapping("/")
    public ModelAndView showNewUserForm(ModelAndView modelAndView){
        modelAndView.setViewName("newUserForm");
        return modelAndView;
    }

    @GetMapping("/users")
    public ModelAndView showUsers(ModelAndView modelAndView){
        modelAndView.setViewName("users");
        modelAndView.addObject("users", service.getUsers());
        return modelAndView;
    }

    @PostMapping("/")
    public ModelAndView addUser (ModelAndView modelAndView,
                                 @RequestParam String name, @RequestParam Integer age){
        modelAndView.setViewName("newUserForm");
        service.addUser(new User(name, age));
        return modelAndView;
    }


}
