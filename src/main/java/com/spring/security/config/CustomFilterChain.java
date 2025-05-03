package com.spring.security.config;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

public class CustomFilterChain extends UsernamePasswordAuthenticationFilter {

	public CustomFilterChain(AuthSuccessHandler successHandler, AuthenticationManager authenticationManager,String loginUrl) {
		super.setAuthenticationManager(authenticationManager);
		super.setAuthenticationSuccessHandler(successHandler);
		super.setAuthenticationFailureHandler(new SimpleUrlAuthenticationFailureHandler(loginUrl+"?error")); 
		super.setFilterProcessesUrl(loginUrl);
	}
}
