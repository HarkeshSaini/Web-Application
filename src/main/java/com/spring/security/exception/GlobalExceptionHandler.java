package com.spring.security.exception;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {

	private static final Logger LOGGER = Logger.getLogger(GlobalExceptionHandler.class.getName());

	@ExceptionHandler(RuntimeException.class)
	public Object handleRuntimeException(HttpServletRequest request,RuntimeException ex) {
		LOGGER.log(Level.SEVERE, "RuntimeException occurred: " + ex.getMessage());
		ErrorResponse errorResponse = new ErrorResponse("Internal Server Error", ex.getMessage(), System.currentTimeMillis());
		return ExceptionPageHandler.handlerWebPage(errorResponse,request, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(NotFoundException.class)
	public static Object handleNotFoundException(HttpServletRequest request,NotFoundException ex) {
		LOGGER.log(Level.WARNING, "NotFoundException occurred: " + ex.getMessage());
		ErrorResponse errorResponse = new ErrorResponse("Resource Not Found", ex.getMessage(), System.currentTimeMillis());
		return ExceptionPageHandler.handlerWebPage(errorResponse,request, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(Exception.class)
	public static Object handleException(HttpServletRequest request,Exception ex) {
		LOGGER.log(Level.SEVERE, "General exception occurred: " + ex.getMessage());
		ErrorResponse errorResponse = new ErrorResponse("Unexpected Error", ex.getMessage(), System.currentTimeMillis());
		return ExceptionPageHandler.handlerWebPage(errorResponse,request, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(IllegalArgumentException.class)
	public static Object handleIllegalArgumentException(HttpServletRequest request,IllegalArgumentException ex) {
		LOGGER.log(Level.WARNING, "IllegalArgumentException occurred: " + ex.getMessage());
		ErrorResponse errorResponse = new ErrorResponse("Invalid InPost", ex.getMessage(), System.currentTimeMillis());
		return ExceptionPageHandler.handlerWebPage(errorResponse,request, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(UsernameNotFoundException.class)
	public Object handleUsernameNotFound(HttpServletRequest request,UsernameNotFoundException ex) {
		LOGGER.log(Level.WARNING, "UsernameNotFoundException occurred: " + ex.getMessage(), ex);
		ErrorResponse errorResponse = new ErrorResponse("User Not Found", ex.getMessage(), System.currentTimeMillis());
	    return ExceptionPageHandler.handlerWebPage(errorResponse,request, HttpStatus.NOT_FOUND);
   }
}
