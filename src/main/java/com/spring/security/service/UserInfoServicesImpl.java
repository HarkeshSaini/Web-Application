package com.spring.security.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import com.spring.security.entity.UserInfoDetail;
import com.spring.security.interfaces.UserInfoService;
import com.spring.security.repositories.UserInfoDetailRepositorie;
import com.spring.security.request.UserInfoRequest;

import jakarta.validation.constraints.NotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class UserInfoServicesImpl implements UserInfoService {

	private static final Logger logger = LoggerFactory.getLogger(UserInfoServicesImpl.class);

	private final ModelMapper modelMapper;
	private final UserInfoDetailRepositorie detailRepositorie;

	public UserInfoServicesImpl(ModelMapper modelMapper, UserInfoDetailRepositorie detailRepositorie) {
		this.modelMapper = modelMapper;
		this.detailRepositorie = detailRepositorie;
	}

	@Override
	public List<UserInfoRequest> getAllUser() {
		List<UserInfoDetail> allUser = detailRepositorie.findAll();
		if (ObjectUtils.isEmpty(allUser)) {
			logger.warn("No users found.");
		}
		return allUser.stream().map(x -> modelMapper.map(x, UserInfoRequest.class)).toList();
	}

	@Override
	public UserInfoRequest getUserById(@NotNull String id) {
		UserInfoDetail userInfo = findUserById(id);
		return modelMapper.map(userInfo, UserInfoRequest.class);
	}

	@Override
	public ResponseEntity<String> deleteUserById(@NotNull String id) {
		UserInfoDetail userInfo = findUserById(id);
		if(ObjectUtils.isEmpty(userInfo)) {
			detailRepositorie.deleteById(id);
			logger.info("User with id {} deleted successfully", id);
			return ResponseEntity.ok("User deleted successfully");
		}
		logger.info("User id not found {} ", id);
		return ResponseEntity.ok("User not deleted");
		
	}

	@Override
	public UserInfoRequest addUser(UserInfoRequest infoRequest, MultipartFile file) {
		if (file != null && file.getSize() > 0) {
			infoRequest.setImgUrl(file.getOriginalFilename());
		}

		if (isEmailAlreadyTaken(infoRequest.getEmail())) {
			logger.warn("User with email {} already exists.", infoRequest.getEmail());
			return null;
		}

		UserInfoDetail userInfo = modelMapper.map(infoRequest, UserInfoDetail.class);
		userInfo.setRole("ADMIN-USER");
		userInfo.setStatus("Active");
		userInfo.setEmailVerified(true);
		userInfo.setPassword(generateRandomPassword());
		userInfo = detailRepositorie.save(userInfo);

		logger.info("User added successfully with email {}", userInfo.getEmail());
		return modelMapper.map(userInfo, UserInfoRequest.class);
	}

	@Override
	public UserInfoRequest updateUser(String id, MultipartFile file, UserInfoRequest infoRequest) {
		UserInfoDetail adminRequest = findUserById(id);

		if (file != null && file.getSize() > 0) {
			infoRequest.setImgUrl(file.getOriginalFilename());
		} else {
			infoRequest.setImgUrl(adminRequest.getImgUrl());
		}

		adminRequest.setName(infoRequest.getName());
		adminRequest.setEmail(infoRequest.getEmail());
		adminRequest.setPhone(infoRequest.getPhone());
		adminRequest.setAge(infoRequest.getAge());
		adminRequest.setComment(infoRequest.getComment());
		adminRequest.setPassword(infoRequest.getPassword());
		adminRequest.setStatus(infoRequest.getStatus());
		adminRequest.setUpdateDate(System.currentTimeMillis());

		adminRequest = detailRepositorie.save(adminRequest);
		logger.info("User with id {} updated successfully", id);

		return modelMapper.map(adminRequest, UserInfoRequest.class);
	}

	@Override
	public ResponseEntity<Object> addAdminUser(UserInfoRequest request) {
		if (isEmailAlreadyTaken(request.getEmail())) {
			logger.warn("Admin user with email {} already exists.", request.getEmail());
			return ResponseEntity.badRequest().body("User already exists");
		}

		UserInfoDetail infoDetail = modelMapper.map(request, UserInfoDetail.class);
		UserInfoDetail saveInfoDetail = detailRepositorie.save(infoDetail);
		logger.info("Admin user with email {} added successfully", request.getEmail());

		return ResponseEntity.status(HttpStatus.CREATED).body(modelMapper.map(saveInfoDetail, UserInfoRequest.class));
	}

	private UserInfoDetail findUserById(String id) {
		return detailRepositorie.findById(id).orElseThrow(() -> {
			logger.error("User not found with id {}", id);
			return new RuntimeException("User not found By provided Id: " + id);
		});
	}

	private boolean isEmailAlreadyTaken(String email) {
		Optional<UserInfoDetail> existingUser = detailRepositorie.findByEmail(email);
		return existingUser.isPresent();
	}

	private String generateRandomPassword() {
		return UUID.randomUUID().toString();
	}

	@Override
	public String[] getAllRole() {
		List<UserInfoDetail> findAll = detailRepositorie.findAll();
		return findAll.stream().map(UserInfoDetail::getRole).toArray(String[]::new);
	}
}
