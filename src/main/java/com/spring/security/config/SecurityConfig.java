package com.spring.security.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.spring.security.interfaces.UserInfoService;
import com.spring.security.interfaces.WebSiteUserService;
import com.spring.security.service.CustomUserService;

@Configuration
public class SecurityConfig {
	
	@Autowired
	private UserInfoService userInfoService;
	
	@Autowired
	private WebSiteUserService siteUserService;

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
	    CustomLoginFilter adminFilter = new CustomLoginFilter(authenticationManager(http), authRoleHandler, "/adminUser");
	    CustomLoginFilter userFilter = new CustomLoginFilter(authenticationManager(http), authRoleHandler, "/webUser");

        http.csrf(AbstractHttpConfigurer::disable)
        .authorizeHttpRequests(auth -> auth.requestMatchers("/admin", "/login").permitAll()
        .requestMatchers("/admin/**","/user/**").authenticated().anyRequest().permitAll())
        
        .addFilterBefore(adminFilter, UsernamePasswordAuthenticationFilter.class)
        .addFilterBefore(userFilter, UsernamePasswordAuthenticationFilter.class)
       
        .formLogin(AbstractHttpConfigurer::disable)
        .logout(LogoutConfigurer::permitAll)
        .exceptionHandling(acc -> acc.accessDeniedPage("/accessDenied"));
	    return http.build();
	}



}
