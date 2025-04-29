package com.spring.security.exception;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public static ModelAndView handleRuntimeException(RuntimeException exception) {
        ModelAndView modelAndView = new ModelAndView("error/500");
        modelAndView.addObject("error", "Internal Server Error");
        modelAndView.addObject("message", exception.getMessage());
        return modelAndView;
    }
    
    @ExceptionHandler(NotFoundException.class)
    public static ModelAndView handleNotFoundException(NotFoundException exception) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("error", "Not Found");
        modelAndView.addObject("message", exception.getMessage());
        modelAndView.setViewName("404-error");
        return modelAndView;
    }

    @ExceptionHandler(Exception.class)
    public static ModelAndView handleException(Exception exception) {
        ModelAndView modelAndView = new ModelAndView("error/500");
        modelAndView.addObject("error", "Unexpected Error");
        modelAndView.addObject("message", exception.getMessage());
        return modelAndView;
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public static ModelAndView handleIllegalArgumentException(IllegalArgumentException exception) {
        ModelAndView modelAndView = new ModelAndView("error/400");
        modelAndView.addObject("error", "Invalid Argument");
        modelAndView.addObject("message", exception.getMessage());
        return modelAndView;
    }
}
