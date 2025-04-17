package com.spring.security.interfaces;

import java.util.List;

import org.jetbrains.annotations.NotNull;
import org.springframework.http.ResponseEntity;

import com.spring.security.request.UserInfoRequest;

public interface UserInfoService {
	
	public List<UserInfoRequest> getAllUser();

	public UserInfoRequest getUserById(String id);

	public ResponseEntity<String> deleteUserById(String id);

	public UserInfoRequest addUser(UserInfoRequest userRegistrationObject);

	public String[] getAllRole();

	public UserInfoRequest updateUser(@NotNull String id, @NotNull UserInfoRequest infoRequest);

	public ResponseEntity<Object> addAdminUser(UserInfoRequest request);
}
