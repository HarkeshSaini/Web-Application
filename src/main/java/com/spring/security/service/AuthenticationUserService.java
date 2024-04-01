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
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationUserService implements UserDetailsService {

    @Autowired
    private UserRegistrationRepositorie userRegistrationRepositorie;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        final var userName = "harkesh";
        final var userPassword = "harkesh";
        final var roleId = "user";
        var hashedPassword = encoder.encode(userPassword);
        var user = new User(userName,hashedPassword, AuthorityUtils.createAuthorityList(roleId.toUpperCase()));
        return new UserDetailConfig(user);
 //        var byUserName = userRegistrationRepositorie.findByUserName(username);
//        if (byUserName == null) {
//            throw new ArrayIndexOutOfBoundsException("invalid login credentials...");
//        } else {
//            final var userName = byUserName.getUserName();
//            final var userPassword = byUserName.getUserPassword();
//            final var roleId = byUserName.getRoleId();
//            var hashedPassword = encoder.encode(userPassword);
//            var hashedPassword = encoder.encode(userPassword);
//            var user = new User(userName,hashedPassword, AuthorityUtils.createAuthorityList(roleId.toUpperCase()));
//            return new UserDetailConfig(user);
//        }
     }
}
