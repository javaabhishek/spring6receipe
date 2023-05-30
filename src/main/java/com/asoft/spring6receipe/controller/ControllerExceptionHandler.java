package com.asoft.spring6receipe.controller;

import com.asoft.spring6receipe.exceptions.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(NumberFormatException.class)
    public ModelAndView recipeErrorHandler(Exception exception){
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("400error");
        modelAndView.addObject("exception",exception);
        return modelAndView;
    }
}
