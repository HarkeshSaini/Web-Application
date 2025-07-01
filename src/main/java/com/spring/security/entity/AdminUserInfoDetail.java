package com.spring.security.entity;

import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

import jakarta.persistence.Id;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@Entity
public class AdminUserInfoDetail implements UserDetails {

 

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private String email;
	private String password;
	private String phone;
	private long dateOfBirth;
	private long createDate;
	private long updateDate;
	private String role;
	private int age;
	private String comment;
	private String status;
	private boolean emailVerified;
	private String imgUrl;

	public AdminUserInfoDetail(User user) {
		this.email = user.getUsername();
		this.password = user.getPassword();
		this.role = user.getAuthorities().stream().map(x -> x.getAuthority()).collect(Collectors.joining(", "));
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + role));
	}

	@Override
	public String getUsername() {
		return email;
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
