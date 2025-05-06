package com.spring.security.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.spring.security.service.CustomUserService;

@Configuration
@EnableWebSecurity
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
	 * SecurityFilterChain manager configuration for adminSecurityFilterChain.
	 */
	@Bean
	@Order(1)
	protected SecurityFilterChain adminSecurityFilterChain(HttpSecurity http) throws Exception {
		http.csrf(AbstractHttpConfigurer::disable).securityMatcher("/admin/**", "/admin", "/adminUser")
		.authorizeHttpRequests(auth -> auth.requestMatchers("/admin", "/adminUser").permitAll().anyRequest().hasAnyRole("ADMIN","ADMIN-USER"))
		.formLogin(form -> form.loginPage("/admin").loginProcessingUrl("/adminUser").defaultSuccessUrl("/admin/dashboard", true).permitAll())
		.logout(logout -> logout.logoutUrl("/admin/logout").logoutSuccessUrl("/admin?logout"));
		return http.build();
	}

	/**
	 * SecurityFilterChain manager configuration for userSecurityFilterChain.
	 */
	@Bean
	@Order(2)
	protected SecurityFilterChain userSecurityFilterChain(HttpSecurity http) throws Exception {
		http.csrf(AbstractHttpConfigurer::disable).securityMatcher("/user/**", "/login", "/loginUser")
		.authorizeHttpRequests(auth -> auth.requestMatchers("/login", "/loginUser").permitAll().anyRequest().hasRole("WEB-USER"))
		.formLogin(form -> form.loginPage("/login").loginProcessingUrl("/loginUser").defaultSuccessUrl("/user/dashboard", true).permitAll())
		.logout(logout -> logout.logoutUrl("/user/logout").logoutSuccessUrl("/login?logout"));
		return http.build();
	}

	/**
	 * CorsConfigurationSource for URL AllowedMethod Access.
	 */

	@Bean
	protected CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration();
		configuration.setAllowedOrigins(List.of("http://localhost:8080"));
		configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
		configuration.setAllowedHeaders(List.of("Authorization", "Content-Type"));
		configuration.setAllowCredentials(true);
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;
	}

}
