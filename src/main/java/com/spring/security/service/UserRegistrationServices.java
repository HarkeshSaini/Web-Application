package com.spring.security.service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.spring.security.entity.UserInfoDetail;
import com.spring.security.interfaces.UserRegisterService;
import com.spring.security.object.UserInfoRequest;
import com.spring.security.repositories.UserInfoDetailRepositorie;

@Service
public class UserRegistrationServices implements UserRegisterService {

	private final ModelMapper modelMapper;

	private final UserInfoDetailRepositorie detailRepositorie;

	public UserRegistrationServices(ModelMapper modelMapper, UserInfoDetailRepositorie detailRepositorie) {
		this.modelMapper = modelMapper;
		this.detailRepositorie = detailRepositorie;
	}

	@Override
	public List<UserInfoRequest> getAllUser() {
		List<UserInfoDetail> allUser = detailRepositorie.findAll();
		return allUser.stream().map(x -> modelMapper.map(x, UserInfoRequest.class)).toList();
	}

	@Override
	public UserInfoRequest getUserById(Integer id) {
		Optional<UserInfoDetail> findById = detailRepositorie.findById(id);
		UserInfoDetail orElse = findById.orElse(null);
		if (ObjectUtils.isEmpty(orElse)) {
			throw new RuntimeException("User not found By provide Id:- " + id);
		}
		return modelMapper.map(orElse, UserInfoRequest.class);
	}

	@Override
	public ResponseEntity<String> deleteUserById(Integer id) {
		Optional<UserInfoDetail> findById = detailRepositorie.findById(id);
		UserInfoDetail orElse = findById.orElse(null);
		if (ObjectUtils.isEmpty(orElse)) {
			throw new RuntimeException("User not found By provide Id:- " + id);
		}
		detailRepositorie.deleteById(id);
		return ResponseEntity.ok("User deleted successfully");
	}

	@Override
	public UserInfoRequest addUser(UserInfoRequest infoRequest) {
		UserInfoDetail userInfo = modelMapper.map(infoRequest, UserInfoDetail.class);
		try {
			Optional<UserInfoDetail> findById = detailRepositorie.findByEmail(userInfo.getEmail());
			userInfo = findById.orElse(null);
			if (!ObjectUtils.isEmpty(userInfo)) {
				throw new RuntimeException("User Already Exits:- " + infoRequest.getEmail());
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		userInfo = detailRepositorie.save(userInfo);
		return modelMapper.map(userInfo, UserInfoRequest.class);
	}
	
	
	
}
