package com.spring.security.exception;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ExceptionPageHandler {

	@ExceptionHandler(Exception.class)
	public Object handleException(HttpServletRequest request, Exception ex, Model model) {
		String accept = request.getHeader("Accept");
		if (accept != null && accept.contains("application/json")) {
			new ErrorResponse("An unexpected error occurred", ex.getMessage(), System.currentTimeMillis());
		}
		ModelAndView mav = new ModelAndView("errorPage");
		mav.addObject("errorMessage", ex.getMessage());
		mav.addObject("url", request.getRequestURI());
		mav.addObject("exception", ex);
		return mav;
	}
}
