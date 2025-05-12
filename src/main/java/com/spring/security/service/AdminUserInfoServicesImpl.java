package com.spring.security.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import com.spring.security.entity.AdminUserInfoDetail;
import com.spring.security.exception.NotFoundException;
import com.spring.security.interfaces.AdminUserInfoService;
import com.spring.security.repositories.UserInfoDetailRepositorie;
import com.spring.security.request.AdminUserInfoRequest;

import jakarta.validation.constraints.NotNull;

@Service
public class AdminUserInfoServicesImpl implements AdminUserInfoService {

	private final ModelMapper modelMapper;
	private final UserInfoDetailRepositorie detailRepositorie;

	public AdminUserInfoServicesImpl(ModelMapper modelMapper, UserInfoDetailRepositorie detailRepositorie) {
		this.modelMapper = modelMapper;
		this.detailRepositorie = detailRepositorie;
	}

	@Override
	public List<AdminUserInfoRequest> getAllUser() {
		List<AdminUserInfoDetail> allUser = detailRepositorie.findAll();
		if (ObjectUtils.isEmpty(allUser)) {
			return new ArrayList<AdminUserInfoRequest>();
		}
		return allUser.stream().map(x -> modelMapper.map(x, AdminUserInfoRequest.class)).toList();
	}

	@Override
	public AdminUserInfoRequest getUserById(@NotNull String id) {
		AdminUserInfoDetail userInfo = findUserById(id);
		return modelMapper.map(userInfo, AdminUserInfoRequest.class);
	}

	@Override
	public ResponseEntity<String> deleteUserById(@NotNull String id) {
		AdminUserInfoDetail userInfo = findUserById(id);
		if (ObjectUtils.isEmpty(userInfo)) {
			detailRepositorie.deleteById(id);
			return ResponseEntity.ok("User deleted successfully");
		}
		return ResponseEntity.ok("User not deleted");

	}

	@Override
	public AdminUserInfoRequest addUser(AdminUserInfoRequest infoRequest, MultipartFile file) {
		if (file != null && file.getSize() > 0) {
			infoRequest.setImgUrl(file.getOriginalFilename());
		}

		if (isEmailAlreadyTaken(infoRequest.getEmail())) {
			return null;
		}

		AdminUserInfoDetail userInfo = modelMapper.map(infoRequest, AdminUserInfoDetail.class);
		userInfo.setRole("ADMIN-USER");
		userInfo.setStatus("Active");
		userInfo.setEmailVerified(true);
		userInfo.setPassword(generateRandomPassword());
		userInfo = detailRepositorie.save(userInfo);

		return modelMapper.map(userInfo, AdminUserInfoRequest.class);
	}

	@Override
	public AdminUserInfoRequest updateUser(String id, MultipartFile file, AdminUserInfoRequest infoRequest) {
		AdminUserInfoDetail adminRequest = findUserById(id);

		if (file != null && file.getSize() > 0) {
			infoRequest.setImgUrl(file.getOriginalFilename());
		} else {
			infoRequest.setImgUrl(adminRequest.getImgUrl());
		}
		try {
			adminRequest.setName(infoRequest.getName());
			adminRequest.setEmail(infoRequest.getEmail());
			adminRequest.setPhone(infoRequest.getPhone());
			adminRequest.setAge(infoRequest.getAge());
			adminRequest.setComment(infoRequest.getComment());
			adminRequest.setPassword(infoRequest.getPassword());
			adminRequest.setStatus(infoRequest.getStatus());
			adminRequest.setUpdateDate(System.currentTimeMillis());

			adminRequest = detailRepositorie.save(adminRequest);
		} catch (Exception e) {
			throw new NotFoundException(e.getLocalizedMessage());
		}

		return modelMapper.map(adminRequest, AdminUserInfoRequest.class);
	}

	@Override
	public ResponseEntity<Object> addAdminUser(AdminUserInfoRequest request) {
		if (isEmailAlreadyTaken(request.getEmail())) {
			return ResponseEntity.badRequest().body("User already exists");
		}

		AdminUserInfoDetail infoDetail = modelMapper.map(request, AdminUserInfoDetail.class);
		AdminUserInfoDetail saveInfoDetail = detailRepositorie.save(infoDetail);
		return ResponseEntity.status(HttpStatus.CREATED).body(modelMapper.map(saveInfoDetail, AdminUserInfoRequest.class));
	}

	private AdminUserInfoDetail findUserById(String id) {
		return detailRepositorie.findById(id).orElseThrow(() -> {
			return new RuntimeException("User not found By provided Id: " + id);
		});
	}

	private boolean isEmailAlreadyTaken(String email) {
		Optional<AdminUserInfoDetail> existingUser = detailRepositorie.findByEmail(email);
		return existingUser.isPresent();
	}

	private String generateRandomPassword() {
		return UUID.randomUUID().toString();
	}

	@Override
	public String[] getAdminRole() {
		List<AdminUserInfoDetail> findAll = detailRepositorie.findAll();
		return findAll.stream().map(AdminUserInfoDetail::getRole).toArray(String[]::new);
	}
}
