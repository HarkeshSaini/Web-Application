package com.spring.security.interfaces;

import java.util.List;

import org.jetbrains.annotations.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import com.spring.security.request.AdminUserInfoRequest;

public interface AdminUserInfoService {
	
	public List<AdminUserInfoRequest> getAllUser();

	public AdminUserInfoRequest getUserById(int id);

	public ResponseEntity<String> deleteUserById(int id);

	public AdminUserInfoRequest addUser(AdminUserInfoRequest userRegistrationObject, MultipartFile file);

	public String[] getAdminRole();

	public AdminUserInfoRequest updateUser(@NotNull int id, MultipartFile file, @NotNull AdminUserInfoRequest infoRequest);

	public ResponseEntity<Object> addAdminUser(AdminUserInfoRequest request);
}
