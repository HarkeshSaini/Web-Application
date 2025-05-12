package com.spring.security.entity;

import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Document(collection = "AdminUserInfoDetail")
public class AdminUserInfoDetail implements UserDetails {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	private String id;
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
   	 return Collections.singletonList(new SimpleGrantedAuthority("ROLE_"+role));
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
