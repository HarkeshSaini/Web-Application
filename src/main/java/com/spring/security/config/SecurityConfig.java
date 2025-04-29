package com.spring.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties.Filter;
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

import com.spring.security.interfaces.UserInfoService;
import com.spring.security.service.CustomUserService;

@Configuration
public class SecurityConfig {

	@Autowired
	private UserInfoService infoService;

	@Bean
	public UserDetailsService userDetailsService() {
		return new CustomUserService();
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(userDetailsService());
		authProvider.setPasswordEncoder(passwordEncoder());
		return authProvider;
	}

	protected AuthenticationManager configure(HttpSecurity httpSecurity) throws Exception {
		AuthenticationManagerBuilder builder = httpSecurity.getSharedObject(AuthenticationManagerBuilder.class);
		builder.authenticationProvider(authenticationProvider());
		return builder.build();
	}

	@Bean
	protected AuthRoleHandler authSuccessHandler() {
		return new AuthRoleHandler();
	}

	@Bean
	protected SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
		httpSecurity.csrf(AbstractHttpConfigurer::disable);
		httpSecurity.authorizeHttpRequests(auth -> auth.requestMatchers("/admin/**").hasAnyRole("ADMIN", "ADMIN-USER")
		.requestMatchers("/user/**").hasRole("USER").anyRequest().authenticated());
		httpSecurity.formLogin(form -> form.loginPage("/admin").successHandler(authSuccessHandler()).permitAll());
		httpSecurity.logout(LogoutConfigurer::permitAll);
		return httpSecurity.build();
	}

}