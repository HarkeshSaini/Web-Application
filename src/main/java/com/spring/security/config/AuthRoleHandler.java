package com.spring.security.config;

import java.io.IOException;
import java.util.Collection;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import com.spring.security.entity.UserInfoDetail;
import com.spring.security.entity.WebSiteUserDetail;
import com.spring.security.exception.NotFoundException;
import com.spring.security.repositories.UserInfoDetailRepositorie;
import com.spring.security.repositories.WebSiteUserRepository;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class AuthRoleHandler implements AuthenticationSuccessHandler {

    private static final Logger logger = LoggerFactory.getLogger(AuthRoleHandler.class);

    @Autowired
    private WebSiteUserRepository webSiteUserRepository;

    @Autowired
    private UserInfoDetailRepositorie userInfoDetailRepositorie;

    /**
     * Handles successful authentication and redirects users based on their roles.
     */
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        String username = authentication.getName();
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        try {
            String adminRole = getAdminRole(username);
            String userRole = getUserRole(username);
            for (GrantedAuthority authority : authorities) {
                String role = authority.getAuthority();
                if (role.equals(adminRole)) {
                    logger.info("User '{}' authenticated with ADMIN role. Redirecting to /admin/dashboard", username);
                    response.sendRedirect("/admin/dashboard");
                    return;
                } else if (role.equals(userRole)) {
                    logger.info("User '{}' authenticated with USER role. Redirecting to /user/dashboard", username);
                    response.sendRedirect("/user/dashboard");
                    return;
                }
            }
            logger.warn("User '{}' authenticated but has no recognized role.", username);
            response.sendRedirect("/access-denied");
        } catch (NotFoundException e) {
            logger.error("Authentication role resolution failed for user '{}': {}", username, e.getMessage(), e);
            throw new NotFoundException(e.getLocalizedMessage());
        }
    }

    /**
     * Retrieves the admin role from UserInfoDetail repository.
     */
    private String getAdminRole(String username) throws NotFoundException {
        Optional<UserInfoDetail> optional = userInfoDetailRepositorie.findByEmail(username);
        if (optional.isEmpty()) {
        	return "NOT_ROLE";
        }
        return optional.get().getRole();
    }

    /**
     * Retrieves the user role from WebSiteUser repository.
     */
    private String getUserRole(String username) throws NotFoundException {
        WebSiteUserDetail user = webSiteUserRepository.findByUsername(username);
        if (ObjectUtils.isEmpty(user)) {
        	return "NOT_ROLE";
        }
        return user.getRole();
    }
}
