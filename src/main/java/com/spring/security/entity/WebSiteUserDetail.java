package com.spring.security.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;
import java.util.Collections;

@Data
@NoArgsConstructor
@Document(collection = "WebSiteUserDetail")
public class WebSiteUserDetail implements UserDetails {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    private String fullName;
    private String email;
    private String password;
    private String status;
    private String role;  
    private String phoneNumber;
    private String username;
    private String dateOfBirth;
    private String gender;
    private String imgUrl;
    private String address;
    private long createdAt;
    private long updatedAt;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority(role));
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
