package com.spring.security.service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.spring.security.entity.UserInfoDetail;
import com.spring.security.interfaces.UserInfoService;
import com.spring.security.object.UserInfoRequest;
import com.spring.security.repositories.UserInfoDetailRepositorie;

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
	public UserInfoRequest getUserById(Integer id) {
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
