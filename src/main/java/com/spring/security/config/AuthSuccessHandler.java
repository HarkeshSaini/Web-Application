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

import com.spring.security.entity.UserInfoDetail;
import com.spring.security.entity.WebSiteUserDetail;
import com.spring.security.repositories.UserInfoDetailRepositorie;
import com.spring.security.repositories.WebSiteUserRepository;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class AuthSuccessHandler implements AuthenticationSuccessHandler {
	
	@Autowired
	private WebSiteUserRepository webSiteUserRepository;

	@Autowired
    private UserInfoDetailRepositorie userInfoDetailRepositorie;
    
	private static final Logger logger = LoggerFactory.getLogger(AuthSuccessHandler.class);

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request,HttpServletResponse response,Authentication authentication) throws IOException {
	   
		String username = authentication.getName();
	    logger.info("Authenticated user: {}", username);
	  
	    Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
	    for (GrantedAuthority authority : authorities) {
	       
	    	String role = authority.getAuthority();
	        logger.info("User has role: {}", role);
	        if (role.equals(adminRole(username))) {
	            response.sendRedirect("/admin/dashboard");
	            return;
	        } else if (role.equals(userRole(username))) {
	            response.sendRedirect("/user/dashboard");
	            return;
	        }
	    }
	    response.sendRedirect("/access-denied");
	}
	
   public String userRole(String username) {
		WebSiteUserDetail user  = webSiteUserRepository.findByUsername(username);
		return user != null ? user.getRole() : "NOT_ROLE";
	}
		
	public String adminRole(String username) {
		Optional<UserInfoDetail> optional = userInfoDetailRepositorie.findByEmail(username);
		return optional.map(obj -> obj.getRole()).orElse("NOT_ROLE");
	}
}

