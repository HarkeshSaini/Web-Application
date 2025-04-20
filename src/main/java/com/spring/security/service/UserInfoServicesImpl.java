package com.spring.security.service;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;

import org.jetbrains.annotations.NotNull;
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

@Service
public class UserInfoServicesImpl implements UserInfoService {

	private final ModelMapper modelMapper;

	private final UserInfoDetailRepositorie detailRepositorie;

	public UserInfoServicesImpl(ModelMapper modelMapper, UserInfoDetailRepositorie detailRepositorie) {
		this.modelMapper = modelMapper;
		this.detailRepositorie = detailRepositorie;
	}

	@Override
	public List<UserInfoRequest> getAllUser() {
		List<UserInfoDetail> allUser = detailRepositorie.findAll();
		return allUser.stream().map(x -> modelMapper.map(x, UserInfoRequest.class)).toList();
	}

	@Override
	public UserInfoRequest getUserById(@NotNull String id) {
		Optional<UserInfoDetail> findById = detailRepositorie.findById(id);
		UserInfoDetail orElse = findById.orElse(null);
		if (ObjectUtils.isEmpty(orElse)) {
			throw new RuntimeException("User not found By provide Id:- " + id);
		}
		return modelMapper.map(orElse, UserInfoRequest.class);
	}

	public String[] getAllRole() {
		List<UserInfoDetail> findAll = detailRepositorie.findAll();
		if (ObjectUtils.isEmpty(findAll)) {
			return new String[] { "NOT_RULE" };
		}
		return findAll.stream().map(x -> x.getRole()).toArray(String[]::new);
	}

	@Override
	public ResponseEntity<String> deleteUserById(@NotNull String id) {
		Optional<UserInfoDetail> findById = detailRepositorie.findById(id);
		UserInfoDetail orElse = findById.orElse(null);
		if (ObjectUtils.isEmpty(orElse)) {
			throw new RuntimeException("User not found By provide Id:- " + id);
		}
		detailRepositorie.deleteById(id);
		return ResponseEntity.ok("User deleted successfully");
	}

	@Override
	public UserInfoRequest addUser(UserInfoRequest infoRequest, MultipartFile file) {
		if (file.getSize() != 0) {
			infoRequest.setImgUrl(file.getOriginalFilename());
		}
		UserInfoDetail userInfo = modelMapper.map(infoRequest, UserInfoDetail.class);
		try {
			Optional<UserInfoDetail> findById = detailRepositorie.findByEmail(userInfo.getEmail());
			if (!ObjectUtils.isEmpty(findById.orElse(null))) {
				return null;
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		userInfo.setRole("USER");
		userInfo.setStatus("Active");
		userInfo.setEmailVerified(true);
		userInfo.setPassword(String.valueOf(UUID.randomUUID()));
		userInfo = detailRepositorie.save(userInfo);
		return modelMapper.map(userInfo, UserInfoRequest.class);
	}

	@Override
	public UserInfoRequest updateUser(String id,MultipartFile file, UserInfoRequest infoRequest) {
		Optional<UserInfoDetail> findById = detailRepositorie.findById(id);
		UserInfoDetail adminRequest = findById.orElse(null);
		try {
			if (ObjectUtils.isEmpty(adminRequest)) {
				return null;
			}
			if (file.getSize() != 0) {
				infoRequest.setImgUrl(file.getOriginalFilename());
			} else {
				infoRequest.setImgUrl(adminRequest.getImgUrl());
			}
			adminRequest.setId(id);
			adminRequest.setName(infoRequest.getName());
			adminRequest.setEmail(infoRequest.getEmail());
			adminRequest.setPhone(infoRequest.getPhone());
			adminRequest.setAge(infoRequest.getAge());
			adminRequest.setComment(infoRequest.getComment());
			adminRequest.setPassword(infoRequest.getPassword());
			adminRequest.setStatus(infoRequest.getStatus());
			adminRequest.setRole(adminRequest.getRole());
			adminRequest.setCreateDate(adminRequest.getCreateDate());
		} catch (Exception e) {
			System.out.println(e);
		}
		adminRequest.setUpdateDate(System.currentTimeMillis());
		adminRequest = detailRepositorie.save(adminRequest);
		return modelMapper.map(adminRequest, UserInfoRequest.class);
	}

	@Override
	public ResponseEntity<Object> addAdminUser(UserInfoRequest request) {
		List<UserInfoDetail> findAll = detailRepositorie.findAll();
		if (ObjectUtils.isEmpty(findAll)) {
			UserInfoDetail infoDetail = modelMapper.map(request, UserInfoDetail.class);
			UserInfoDetail saveInfoDetail = detailRepositorie.save(infoDetail);
			return ResponseEntity.status(HttpStatus.CREATED)
					.body(modelMapper.map(saveInfoDetail, UserInfoRequest.class));
		}
		return ResponseEntity.badRequest().body("User already exits");
	}

}
