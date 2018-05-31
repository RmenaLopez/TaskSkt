package com.skt.finaltask.managementApp.managementApp.controller;

import com.skt.finaltask.commonLibrary.model.User;
import com.skt.finaltask.managementApp.managementApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    private final String USER_FORM_URL = "/new-user";
    private final String USER_LIST_URL = "/users";

    @Autowired
    private UserService service;

    @GetMapping(USER_FORM_URL)
    public ModelAndView showNewUserForm(ModelAndView modelAndView){
        modelAndView.setViewName("/newUserForm");
        modelAndView.addObject("user", new User());
        return modelAndView;
    }

    @GetMapping(USER_LIST_URL)
    public ModelAndView showUsers(ModelAndView modelAndView){
        modelAndView.setViewName("/usersList");
        modelAndView.addObject("users", service.getUsers());
        return modelAndView;
    }

    @PostMapping(USER_FORM_URL)
    public ModelAndView addUser (ModelAndView modelAndView,
                                 @Valid @ModelAttribute("user")User user,
                                 BindingResult result){

        if (result.hasErrors()){
            modelAndView = error(USER_FORM_URL, result.getAllErrors());
            return modelAndView;
        }

        modelAndView.setViewName("/newUserForm");
        modelAndView.addObject(new User());
        modelAndView.setStatus(HttpStatus.CREATED);
        service.addUser(user);
        return modelAndView;
    }

}
