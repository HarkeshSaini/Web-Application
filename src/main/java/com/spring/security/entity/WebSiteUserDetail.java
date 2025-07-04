package com.spring.security.entity;

import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Setter
@Getter
@NoArgsConstructor
@Entity
public class WebSiteUserDetail implements UserDetails {

    private static final long serialVersionUID = 1L;

    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String fullName;
    private String email;
    private String password;
    private String status;
    private String role; 
    @Column(columnDefinition = "LONGTEXT")
    private String destination;
	private String aboutMe;
    private String phone;
    private String username;
    private String dateOfBirth;
    private String gender;
    private String imgUrl;
    private String address;
    private long createdAt;
    private long updatedAt;
    
    public WebSiteUserDetail(User user) {
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.role = user.getAuthorities().stream().map(x -> x.getAuthority()).collect(Collectors.joining(", "));
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
    	 return Collections.singletonList(new SimpleGrantedAuthority("ROLE_"+role));
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
