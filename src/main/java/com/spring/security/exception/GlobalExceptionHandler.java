package com.spring.security.exception;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

	private static final Logger LOGGER = Logger.getLogger(GlobalExceptionHandler.class.getName());

	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<ErrorResponse> handleRuntimeException(RuntimeException ex) {
		LOGGER.log(Level.SEVERE, "RuntimeException occurred: " + ex.getMessage());
		ErrorResponse errorResponse = new ErrorResponse("User Not Found", ex.getMessage(), System.currentTimeMillis());
		return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(NotFoundException.class)
	public static ResponseEntity<ErrorResponse> handleNotFoundException(NotFoundException ex) {
		LOGGER.log(Level.WARNING, "NotFoundException occurred: " + ex.getMessage());
		ErrorResponse errorResponse = new ErrorResponse("User Not Found", ex.getMessage(), System.currentTimeMillis());
		return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(Exception.class)
	public static ResponseEntity<ErrorResponse> handleException(Exception ex) {
		LOGGER.log(Level.SEVERE, "General exception occurred: " + ex.getMessage());
		ErrorResponse errorResponse = new ErrorResponse("User Not Found", ex.getMessage(), System.currentTimeMillis());
		return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(IllegalArgumentException.class)
	public static ResponseEntity<ErrorResponse> handleIllegalArgumentException(IllegalArgumentException ex) {
		LOGGER.log(Level.WARNING, "IllegalArgumentException occurred: " + ex.getMessage());
		ErrorResponse errorResponse = new ErrorResponse("User Not Found", ex.getMessage(), System.currentTimeMillis());
		return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(UsernameNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleUsernameNotFound(UsernameNotFoundException ex) {
		LOGGER.log(Level.WARNING, "UsernameNotFoundException occurred: " + ex.getMessage(), ex);
		ErrorResponse errorResponse = new ErrorResponse("User Not Found", ex.getMessage(), System.currentTimeMillis());
		return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
	}
}
