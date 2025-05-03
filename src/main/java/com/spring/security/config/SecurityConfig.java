package com.spring.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

import com.spring.security.service.CustomUserService;

@Component
@Configuration
public class SecurityConfig {
	
	private static final String LOGIN_URL_ADMIN = "/admin";
	private static final String LOGIN_URL_USER = "/login";

	/**
	 * Defines the custom AuthenticationHandler implementation.
	 */
	private final AuthSuccessHandler authSuccessHandler;

	public SecurityConfig(AuthSuccessHandler authSuccessHandler) {
		this.authSuccessHandler = authSuccessHandler;
	}

	/**
	 * Defines the custom UserDetailsService implementation.
	 */
	@Bean
	protected UserDetailsService userDetailsService() {
		return new CustomUserService();
	}

	/**
	 * Password encoder bean using BCrypt.
	 */
	@Bean
	protected BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	/**
	 * Authentication provider using DAO and BCrypt.
	 */
	@Bean
	protected DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setUserDetailsService(userDetailsService());
		provider.setPasswordEncoder(passwordEncoder());
		return provider;
	}

	/**
	 * Authentication manager configuration.
	 */
	@Bean
	protected AuthenticationManager authenticationManager(HttpSecurity httpSecurity) throws Exception {
		AuthenticationManagerBuilder builder = httpSecurity.getSharedObject(AuthenticationManagerBuilder.class);
		builder.authenticationProvider(authenticationProvider());
		return builder.build();
	}

	@Bean
	protected SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		CustomFilterChain adminFilterChain = new CustomFilterChain(authSuccessHandler, authenticationManager(http), LOGIN_URL_ADMIN);
		CustomFilterChain userFilterChain = new CustomFilterChain(authSuccessHandler, authenticationManager(http),	LOGIN_URL_USER);

		http.csrf(AbstractHttpConfigurer::disable)
			.authorizeHttpRequests(authz -> authz.requestMatchers(LOGIN_URL_USER,LOGIN_URL_ADMIN).permitAll()
			.requestMatchers("/admin/**").hasRole("ADMIN")
			.requestMatchers("/user/**").hasRole("WEB-USER").anyRequest().permitAll()
		);
		
		http.addFilterBefore(adminFilterChain, UsernamePasswordAuthenticationFilter.class);
		http.addFilterBefore(userFilterChain, UsernamePasswordAuthenticationFilter.class);
		
        http.formLogin(login -> login.disable());
		http.logout(logout -> logout.logoutUrl("/logout").permitAll());
		http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED));  
		return http.build();
	}

}
