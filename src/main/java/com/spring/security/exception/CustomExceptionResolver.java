package com.spring.security.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Configuration
public class CustomExceptionResolver implements HandlerExceptionResolver {

	private static final Logger logger = LoggerFactory.getLogger(CustomExceptionResolver.class);

	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,Exception ex) {
		logger.info("Handled exception silently: {}", ex.getMessage());
		response.setStatus(HttpServletResponse.SC_NOT_FOUND);
		ModelAndView mav = new ModelAndView("404-error");
		mav.addObject("message", ex.getMessage());
		return mav;
	}
}
