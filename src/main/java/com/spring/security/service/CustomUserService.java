package com.spring.security.service;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.spring.security.entity.UserInfoDetail;
import com.spring.security.entity.WebSiteUserDetail;
import com.spring.security.repositories.UserInfoDetailRepositorie;
import com.spring.security.repositories.WebSiteUserRepository;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class CustomUserService implements UserDetailsService {

	@Autowired
	private WebSiteUserRepository webSiteUserRepository;

	@Autowired
	private UserInfoDetailRepositorie userInfoDetailRepositorie;

	@Override
	public UserDetails loadUserByUsername(String emailId) throws UsernameNotFoundException {

		Optional<UserInfoDetail> costumUser = userInfoDetailRepositorie.findByEmail(emailId);
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		UserInfoDetail orElse = costumUser.orElse(null);
		WebSiteUserDetail detail = null;
		try {
			if (ObjectUtils.isEmpty(orElse)) {
				detail = webSiteUserRepository.findByUsername(emailId);
				if (ObjectUtils.isEmpty(detail)) {
					throw new RuntimeException("User not found.");
				}
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		String role = new String();
		String password = new String();
		Collection<? extends GrantedAuthority> authorities = null;
		PasswordEncoder encoder = new BCryptPasswordEncoder();
		try {
			if (!ObjectUtils.isEmpty(orElse)) {
				role = orElse.getRole();
				authorities = orElse.getAuthorities();
				password = orElse.getPassword();
			} else {
				role = detail.getRole();
				authorities = detail.getAuthorities();
				password = detail.getPassword();
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		request.getSession().setAttribute("role", role);
		return User.builder().username(emailId).password(encoder.encode(password)).authorities(authorities).build();
	}
}
