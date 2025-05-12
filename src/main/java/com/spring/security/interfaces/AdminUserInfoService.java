package com.spring.security.interfaces;

import java.util.List;

import org.jetbrains.annotations.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import com.spring.security.request.AdminUserInfoRequest;

public interface AdminUserInfoService {
	
	public List<AdminUserInfoRequest> getAllUser();

	public AdminUserInfoRequest getUserById(String id);

	public ResponseEntity<String> deleteUserById(String id);

	public AdminUserInfoRequest addUser(AdminUserInfoRequest userRegistrationObject, MultipartFile file);

	public String[] getAdminRole();

	public AdminUserInfoRequest updateUser(@NotNull String id, MultipartFile file, @NotNull AdminUserInfoRequest infoRequest);

	public ResponseEntity<Object> addAdminUser(AdminUserInfoRequest request);
}
