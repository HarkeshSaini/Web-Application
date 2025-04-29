package com.spring.security.config;

import java.io.IOException;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import com.spring.security.entity.UserInfoDetail;
import com.spring.security.entity.WebSiteUserDetail;
import com.spring.security.exception.GlobalExceptionHandler;
import com.spring.security.exception.NotFoundException;
import com.spring.security.repositories.UserInfoDetailRepositorie;
import com.spring.security.repositories.WebSiteUserRepository;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class AuthRoleHandler implements AuthenticationSuccessHandler {

	@Autowired
	private WebSiteUserRepository webSiteUserRepository;

	@Autowired
	private UserInfoDetailRepositorie userInfoDetailRepositorie;

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
		try {
			for (GrantedAuthority authority : authorities) {
				if (authority.getAuthority().equals(getRoleAdminUser(authentication.getName()))) {
					response.sendRedirect("/admin/dashboard");
					return;
				} else if (authority.getAuthority().equals(getRoleWebUser(authentication.getName()))) {
					response.sendRedirect("/user/dashboard");
					return;
				}
			}
		} catch (NotFoundException e) {
			GlobalExceptionHandler.handleNotFoundException(e);
		}
	}

	private String getRoleAdminUser(String userName) {
		UserInfoDetail orElse = userInfoDetailRepositorie.findByEmail(userName).orElse(null);
		if (ObjectUtils.isEmpty(orElse)) {
			return "NOT_ROLE";
		}
		return orElse.getRole();
	}

	private String getRoleWebUser(String userName) {
		WebSiteUserDetail data = webSiteUserRepository.findByUsername(userName);
		if (ObjectUtils.isEmpty(data)) {
			return "NOT_ROLE";
		}
		return data.getRole();
	}
}
