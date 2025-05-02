package com.spring.security.config;

import java.util.Arrays;

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
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.spring.security.service.CustomUserService;

@Configuration
public class SecurityConfig {

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

	/**
	 * Configures HTTP security, login, and access control.
	 */

	@Bean
	protected SecurityFilterChain filterChain(HttpSecurity http, AuthRoleHandler authRoleHandler) throws Exception {
		CustomLoginFilter adminFilter = new CustomLoginFilter(authenticationManager(http), authRoleHandler, "/adminUser");
		CustomLoginFilter userFilter = new CustomLoginFilter(authenticationManager(http), authRoleHandler, "/webUser");
		http.csrf(AbstractHttpConfigurer::disable).authorizeHttpRequests(auth -> auth
				
		.requestMatchers("/admin", "/login").permitAll()
		.requestMatchers("/admin/**").hasAnyRole("ADMIN", "ADMIN-USER")
		.requestMatchers("/user/**").hasRole("WEB-USER").anyRequest()
		.permitAll())

		.addFilterBefore(adminFilter, UsernamePasswordAuthenticationFilter.class)
		.addFilterBefore(userFilter, UsernamePasswordAuthenticationFilter.class)
		.formLogin(x->x.disable()).logout(LogoutConfigurer::permitAll)
		.exceptionHandling(acc -> acc.accessDeniedPage("/accessDenied"));
		return http.build();
	}

	@Bean
	protected CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration();
		configuration.setAllowedOrigins(Arrays.asList("http://localhost:8080"));
		configuration.setAllowedMethods(Arrays.asList("GET", "POST"));
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;
	}

}
