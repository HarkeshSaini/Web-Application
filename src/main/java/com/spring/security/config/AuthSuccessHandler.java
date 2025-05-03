package com.spring.security.config;

import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class AuthSuccessHandler implements AuthenticationSuccessHandler {

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
	    if (authentication.getAuthorities().stream().anyMatch(x -> x.getAuthority().equals("ADMIN"))) {
	        response.sendRedirect("/admin/dashboard");
	    } else if (authentication.getAuthorities().stream().anyMatch(x -> x.getAuthority().equals("WEB-USER"))) {
	        response.sendRedirect("/user/dashboard");
	    } else {
	        response.sendRedirect("/access-denied");
	    }
	}

}
