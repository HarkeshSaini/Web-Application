package com.spring.security.service;

import java.util.Collection;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.spring.security.entity.UserInfoDetail;
import com.spring.security.entity.WebSiteUserDetail;
import com.spring.security.repositories.UserInfoDetailRepositorie;
import com.spring.security.repositories.WebSiteUserRepository;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class CustomUserService implements UserDetailsService {

    private static final Logger LOGGER = Logger.getLogger(CustomUserService.class.getName());

    @Autowired
    private WebSiteUserRepository webSiteUserRepository;

    @Autowired
    private UserInfoDetailRepositorie userInfoDetailRepositorie;

    @Override
    public UserDetails loadUserByUsername(String emailId) throws UsernameNotFoundException {
        try {
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            Optional<UserInfoDetail> optionalUserInfo = userInfoDetailRepositorie.findByEmail(emailId);
            if (optionalUserInfo.isPresent()) {
                UserInfoDetail userInfo = optionalUserInfo.get();
                setSessionRole(request, userInfo.getRole());
                return buildUserDetails(emailId, userInfo.getPassword(), userInfo.getAuthorities());
            }
            WebSiteUserDetail webUser = webSiteUserRepository.findByUsername(emailId);
            if (webUser != null) {
                setSessionRole(request, webUser.getRole());
                return buildUserDetails(emailId, webUser.getPassword(), webUser.getAuthorities());
            }
            LOGGER.warning("User not found with email: " + emailId);
            throw new UsernameNotFoundException("User not found with email: " + emailId);

        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error loading user by username");
            throw new UsernameNotFoundException("Authentication failed: " + e.getMessage());
        }
    }

    private UserDetails buildUserDetails(String emailId, String password,Collection<? extends GrantedAuthority> authorities) {
    	PasswordEncoder encoder = new BCryptPasswordEncoder();
    	return User.builder().username(emailId).password(encoder.encode(password)).authorities(authorities).build();
    }

    private void setSessionRole(HttpServletRequest request, String role) {
        if (request != null) {
            request.getSession().setAttribute("role", role);
        }
    }
}
