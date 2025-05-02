package com.spring.security.exception;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;

@Controller
public class ExceptionPageHandler implements ErrorController {

	@GetMapping("/error")
	public Object handleError(HttpServletRequest request) {
		try {
			Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
			int statusCode = status != null ? Integer.parseInt(status.toString()) : 500;

			String accept = request.getHeader("Accept");
			if (accept != null && accept.contains("application/json")) {
				return new ResponseEntity<>(
						new ErrorResponse("Error occurred", "Status code: " + statusCode, System.currentTimeMillis()),
						HttpStatus.valueOf(statusCode));
			}

			ModelAndView mav = new ModelAndView("404-error");
			mav.addObject("message", request.getMethod());
			mav.addObject("url", request.getRequestURI());
			mav.addObject("error", RequestDispatcher.ERROR_STATUS_CODE.toString());
			mav.addObject("timestamp", System.currentTimeMillis());
			return mav;

		} catch (Exception ex) {
			return fallbackErrorPage(request,ex.getMessage());
		}
	}

	public static Object handlerWebPage(ErrorResponse errorResponse, HttpServletRequest request, HttpStatus status) {
		try {
			int statusCode = status != null ? status.value() : 500;
			String accept = request.getHeader("Accept");

			if (accept != null && accept.contains("application/json")) {
				return new ResponseEntity<>(errorResponse, HttpStatus.valueOf(statusCode));
			}

			ModelAndView mav = new ModelAndView("404-error");
			mav.addObject("message", errorResponse.getMessage());
			mav.addObject("url", request.getRequestURI());
			mav.addObject("error", errorResponse.getError());
			mav.addObject("timestamp", errorResponse.getTimestamp());
			return mav;

		} catch (Exception ex) {
			return fallbackErrorPage(request,errorResponse.getError());
		}
	}

	private static Object fallbackErrorPage(HttpServletRequest request, String error) {
		ModelAndView mav = new ModelAndView("404-error");
		mav.addObject("message", "An unexpected error occurred.");
		mav.addObject("url", request.getRequestURI());
		mav.addObject("error", error);
		mav.addObject("timestamp", System.currentTimeMillis());
		return mav;
	}
}
