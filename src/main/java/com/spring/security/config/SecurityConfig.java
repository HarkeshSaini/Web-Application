package com.spring.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.spring.security.service.CustomUserService;

import static org.springframework.security.config.Customizer.withDefaults;

@Component
@Configuration
public class SecurityConfig {
	
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
	protected SecurityFilterChain securityFilterChain(HttpSecurity http,AuthenticationManager authenticationManager) throws Exception {
		CustomFilterChain adminFilterChain = new CustomFilterChain(authSuccessHandler, authenticationManager, "/adminUser");
		CustomFilterChain userFilterChain = new CustomFilterChain(authSuccessHandler, authenticationManager, "/loginUser");
        
		http.cors(withDefaults()).csrf(AbstractHttpConfigurer::disable)
        .authorizeHttpRequests(authz -> authz.requestMatchers("/login", "/admin").permitAll()
            .requestMatchers("/admin/**").hasAuthority("ADMIN")
            .requestMatchers("/user/**").hasAuthority("WEB-USER").anyRequest().permitAll()
        );
		
		http.addFilterBefore(adminFilterChain, UsernamePasswordAuthenticationFilter.class);
		http.addFilterBefore(userFilterChain, UsernamePasswordAuthenticationFilter.class);
		
        http.formLogin(x->x.disable()).logout(logout -> logout.logoutUrl("/logout").permitAll());
        http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED));

		return http.build();
	}
	
	@Bean
	protected CorsConfigurationSource corsConfigurationSource() {
	    CorsConfiguration configuration = new CorsConfiguration();
	    configuration.addAllowedOrigin("http://localhost:8080");  
	    configuration.addAllowedMethod(HttpMethod.GET);  
	    configuration.addAllowedMethod(HttpMethod.POST);  
	    configuration.addAllowedHeader("Authorization");  
	    configuration.addAllowedHeader("Content-Type");   
	    configuration.setAllowCredentials(true);  

	    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	    source.registerCorsConfiguration("/**", configuration);   
	    return source;
	}


}
