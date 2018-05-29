package com.skt.finaltask.managementApp.managementApp.controller;

import org.springframework.validation.ObjectError;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

public class ErrorController {
    public static ModelAndView error(String goBackTo, List<ObjectError> errors){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/error");
        modelAndView.addObject("goBackTo", goBackTo);
        modelAndView.addObject("errors", errors);
        return modelAndView;
    }
}
