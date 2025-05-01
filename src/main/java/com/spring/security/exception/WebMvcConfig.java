package com.spring.security.exception;

import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

	private final CustomExceptionResolver customExceptionResolver;

	public WebMvcConfig(CustomExceptionResolver customExceptionResolver) {
		this.customExceptionResolver = customExceptionResolver;
	}

	@Override
	public void configureHandlerExceptionResolvers(List<HandlerExceptionResolver> resolvers) {
		resolvers.add(0, customExceptionResolver);
	}
}
