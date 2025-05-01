package com.spring.security.interfaces;

import java.util.List;

import org.jetbrains.annotations.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import com.spring.security.request.UserInfoRequest;

public interface UserInfoService {
	
	public List<UserInfoRequest> getAllUser();

	public UserInfoRequest getUserById(String id);

	public ResponseEntity<String> deleteUserById(String id);

	public UserInfoRequest addUser(UserInfoRequest userRegistrationObject, MultipartFile file);

	public String[] getAdminRole();

	public UserInfoRequest updateUser(@NotNull String id, MultipartFile file, @NotNull UserInfoRequest infoRequest);

	public ResponseEntity<Object> addAdminUser(UserInfoRequest request);
}
