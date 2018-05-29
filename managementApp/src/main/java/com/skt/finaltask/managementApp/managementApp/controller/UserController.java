package com.skt.finaltask.managementApp.managementApp.controller;

import com.skt.finaltask.commonLibrary.model.User;
import com.skt.finaltask.managementApp.managementApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

import static com.skt.finaltask.managementApp.managementApp.controller.ErrorController.error;

@RestController
public class UserController {

    @Autowired
    UserService service;

    @GetMapping("/")
    public ModelAndView showNewUserForm(ModelAndView modelAndView){
        modelAndView.setViewName("/newUserForm");
        modelAndView.addObject("user", new User());
        return modelAndView;
    }

    @GetMapping("/users")
    public ModelAndView showUsers(ModelAndView modelAndView){
        modelAndView.setViewName("/users");
        modelAndView.addObject("users", service.getUsers());
        return modelAndView;
    }

    @PostMapping("/")
    public ModelAndView addUser (ModelAndView modelAndView,
                                 @Valid @ModelAttribute("user")User user,
                                 BindingResult result){

        if (result.hasErrors()){
            modelAndView = error("/", result.getAllErrors());
            return modelAndView;
        }

        modelAndView.setViewName("/newUserForm");
        modelAndView.addObject(user);
        service.addUser(new User());
        return modelAndView;
    }
}
