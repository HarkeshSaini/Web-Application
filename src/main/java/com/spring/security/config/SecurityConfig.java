package com.spring.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.spring.security.interfaces.UserInfoService;
import com.spring.security.service.CustomUserService;

import jakarta.servlet.http.HttpServletRequest;

@Configuration
public class SecurityConfig {

	@Autowired
	private UserInfoService userInfoService;

	/**
	 * Defines the custom UserDetailsService implementation.
	 */
	@Bean
	public UserDetailsService userDetailsService() {
		return new CustomUserService();
	}

	/**
	 * Password encoder bean using BCrypt.
	 */
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	/**
	 * Authentication provider using DAO and BCrypt.
	 */
	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setUserDetailsService(userDetailsService());
		provider.setPasswordEncoder(passwordEncoder());
		return provider;
	}

	/**
	 * Authentication manager configuration.
	 */
	@Bean
	public AuthenticationManager authenticationManager(HttpSecurity httpSecurity) throws Exception {
		AuthenticationManagerBuilder builder = httpSecurity.getSharedObject(AuthenticationManagerBuilder.class);
		builder.authenticationProvider(authenticationProvider());
		return builder.build();
	}

	/**
	 * Configures HTTP security, login, and access control.
	 */
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http, AuthRoleHandler authRoleHandler) throws Exception {
	    // Create separate filters for admin and user logins
	    CustomLoginFilter adminFilter = new CustomLoginFilter(authenticationManager(http), authRoleHandler, "/admin/process");
	    CustomLoginFilter userFilter = new CustomLoginFilter(authenticationManager(http), authRoleHandler, "/login/process");

	    http.csrf(AbstractHttpConfigurer::disable)
	        .authorizeHttpRequests(auth -> auth
	        	.requestMatchers("/login", "/login/process", "/admin", "/admin/process").permitAll()
	            
	            .requestMatchers("/admin/**").hasAnyRole(userInfoService.getAllRole())  
	            .requestMatchers("/user/**").hasRole("WEB-USER")
	            .anyRequest().permitAll()
	        )

	        // Custom login filters before default Spring filter
	        .addFilterBefore(adminFilter, UsernamePasswordAuthenticationFilter.class)
	        .addFilterBefore(userFilter, UsernamePasswordAuthenticationFilter.class)

	        // Disable Spring’s default form login
	        .formLogin(AbstractHttpConfigurer::disable)

	        // Enable logout
	        .logout(LogoutConfigurer::permitAll);

	    return http.build();
	}


}
