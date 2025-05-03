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
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,Authentication authentication) throws IOException {
		String targetUrl = new String();
        if (authentication.getAuthorities().stream().anyMatch(x -> x.getAuthority().equals("ADMIN"))) {
            targetUrl = "/admin/dashboard";
        } else if (authentication.getAuthorities().stream().anyMatch(x -> x.getAuthority().equals("WEB-USER"))) {
            targetUrl = "/user/dashboard";
        }
        response.sendRedirect(targetUrl);
    }
}
