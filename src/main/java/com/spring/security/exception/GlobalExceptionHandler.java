package com.spring.security.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestControllerAdvice
public class GlobalExceptionHandler {

	private static final Logger LOGGER = Logger.getLogger(GlobalExceptionHandler.class.getName());

	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<Map<String, String>> handleRuntimeException(RuntimeException exception) {
		LOGGER.log(Level.SEVERE, "RuntimeException occurred: " + exception.getMessage(), exception);
		Map<String, String> error = new HashMap<>();
		error.put("error", "An error occurred");
		error.put("message", exception.getMessage());
		return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(NotFoundException.class)
	public static ResponseEntity<Map<String, String>> handleNotFoundException(NotFoundException exception) {
		LOGGER.log(Level.WARNING, "NotFoundException occurred: " + exception.getMessage(), exception);
		Map<String, String> error = new HashMap<>();
		error.put("error", "Resource Not Found");
		error.put("message", exception.getMessage());
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(Exception.class)
	public static ResponseEntity<Map<String, String>> handleException(Exception exception) {
		LOGGER.log(Level.SEVERE, "General exception occurred: " + exception.getMessage(), exception);
		Map<String, String> error = new HashMap<>();
		error.put("error", "Internal Server Error");
		error.put("message", exception.getMessage());
		return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(IllegalArgumentException.class)
	public static ResponseEntity<Map<String, String>> handleIllegalArgumentException(IllegalArgumentException exception) {
		LOGGER.log(Level.WARNING, "IllegalArgumentException occurred: " + exception.getMessage(), exception);
		Map<String, String> error = new HashMap<>();
		error.put("error", "Bad Request");
		error.put("message", exception.getMessage());
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(UsernameNotFoundException.class)
	public ResponseEntity<Map<String, String>> handleUsernameNotFound(UsernameNotFoundException ex) {
		LOGGER.log(Level.WARNING, "UsernameNotFoundException occurred: " + ex.getMessage(), ex);
		Map<String, String> error = new HashMap<>();
		error.put("error", "User Not Found");
		error.put("message", ex.getMessage());
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}
}
