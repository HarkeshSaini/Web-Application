package com.spring.security.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

public class CustomLoginFilter extends UsernamePasswordAuthenticationFilter {

    private static final Logger logger = LoggerFactory.getLogger(CustomLoginFilter.class);

    public CustomLoginFilter(AuthenticationManager authManager, AuthRoleHandler authRoleHandler, String loginProcessingUrl) {
        setAuthenticationManager(authManager);
        setFilterProcessesUrl(loginProcessingUrl);
        setAuthenticationSuccessHandler(authRoleHandler);
        String failureUrl = (loginProcessingUrl.matches("^/admin.*")) ? "/admin?error" : "/login?error";
        setAuthenticationFailureHandler(new SimpleUrlAuthenticationFailureHandler(failureUrl));
        logger.debug("CustomLoginFilter initialized with loginProcessingUrl: {}", loginProcessingUrl);
    }
}
