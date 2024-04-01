package com.spring.security.service;

import com.spring.security.config.UserDetailConfig;
import com.spring.security.repositories.UserRegistrationRepositorie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationUserService implements UserDetailsService {

    @Autowired
    private UserRegistrationRepositorie userRegistrationRepositorie;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        var byUserName = userRegistrationRepositorie.findByUserName(username);
//        if (byUserName == null) {
//            throw new ArrayIndexOutOfBoundsException("invalid login credentials...");
//        } else {
//            var passwordEncoder = new BCryptPasswordEncoder();
//            var hashedPassword = passwordEncoder.encode(byUserName.getUserPassword());
//            var user = new User(byUserName.getUserName(),hashedPassword, AuthorityUtils.createAuthorityList(byUserName.getRoleId().toUpperCase()));
//            return new UserDetailConfig(user);
//        }
        var passwordEncoder = new BCryptPasswordEncoder();
        var hashedPassword = passwordEncoder.encode("harkesh");
        var user = new User("harkesh",hashedPassword, AuthorityUtils.createAuthorityList("user".toUpperCase()));
        return new UserDetailConfig(user);
     }
}
