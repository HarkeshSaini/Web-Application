package com.spring.security.interfaces;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.spring.security.object.UserInfoRequest;

public interface UserRegisterService {
	
	public List<UserInfoRequest> getAllUser();

	public UserInfoRequest getUserById(Integer id);

	public ResponseEntity<String> deleteUserById(Integer id);

	public UserInfoRequest addUser(UserInfoRequest userRegistrationObject);
}
