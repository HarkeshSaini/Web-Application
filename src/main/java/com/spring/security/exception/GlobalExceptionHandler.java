package com.spring.security.exception;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {

	private static final Logger LOGGER = Logger.getLogger(GlobalExceptionHandler.class.getName());

	@ExceptionHandler(RuntimeException.class)
	public static Object handleRuntimeException(HttpServletRequest request, RuntimeException ex) {
		LOGGER.log(Level.SEVERE, "Unhandled runtime exception at path: {0}, message: {1}",
		new Object[] { request.getRequestURI(), ex.getMessage() });
		return buildErrorResponse("Internal Server Error", ex, request, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(NotFoundException.class)
	public static Object handleNotFoundException(HttpServletRequest request, NotFoundException ex) {
		LOGGER.log(Level.WARNING, "Resource not found at path: {0}, message: {1}",
		new Object[] { request.getRequestURI(), ex.getMessage() });
		return buildErrorResponse("Resource Not Found", ex, request, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(Exception.class)
	public static Object handleGeneralException(HttpServletRequest request, Exception ex) {
		LOGGER.log(Level.SEVERE, "Unexpected error at path: {0}, message: {1}",
		new Object[] { request.getRequestURI(), ex.getMessage() });
		return buildErrorResponse("Unexpected Error", ex, request, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(IllegalArgumentException.class)
	public static Object handleIllegalArgumentException(HttpServletRequest request, IllegalArgumentException ex) {
		LOGGER.log(Level.WARNING, "Invalid input at path: {0}, message: {1}",
		new Object[] { request.getRequestURI(), ex.getMessage() });
		return buildErrorResponse("Invalid Input", ex, request, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(UsernameNotFoundException.class)
	public static Object handleUsernameNotFound(HttpServletRequest request, UsernameNotFoundException ex) {
		LOGGER.log(Level.WARNING, "User not found at path: {0}, message: {1}",
		new Object[] { request.getRequestURI(), ex.getMessage() });
		return buildErrorResponse("User Not Found", ex, request, HttpStatus.NOT_FOUND);
	}

	private static Object buildErrorResponse(String title, Exception ex, HttpServletRequest request, HttpStatus status) {
		ErrorResponse errorResponse = new ErrorResponse(title, ex.getMessage(), System.currentTimeMillis());
		return ExceptionPageHandler.handlerWebPage(errorResponse, request, status);
	}
}
