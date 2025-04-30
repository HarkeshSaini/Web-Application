package com.spring.security.exception;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;

@Controller
@ControllerAdvice
public class ExceptionPageHandler implements ErrorController{
 
	@GetMapping("/error")
    public Object handleError(HttpServletRequest request) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        Integer statusCode = status != null ? Integer.parseInt(status.toString()) : 500;

        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            return new ResponseEntity<>(
                new ErrorResponse("Error occurred", "Status code: " + statusCode, System.currentTimeMillis()),
                HttpStatus.valueOf(statusCode)
            );
        }

        ModelAndView mav = new ModelAndView("404-error");
        mav.addObject("status", statusCode);
        mav.addObject("url", request.getRequestURI());
        return mav;
    }
}
