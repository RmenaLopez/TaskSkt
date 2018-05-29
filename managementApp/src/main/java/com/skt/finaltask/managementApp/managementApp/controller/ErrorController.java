package com.skt.finaltask.managementApp.managementApp.controller;

import org.springframework.validation.ObjectError;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

class ErrorController {
    private static final String ERROR_URL = "/error";

    static ModelAndView error(String goBackTo, List<ObjectError> errors){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(ERROR_URL);
        modelAndView.addObject("goBackTo", goBackTo);
        modelAndView.addObject("errors", errors);
        return modelAndView;
    }
}
