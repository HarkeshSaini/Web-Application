package com.spring.security.service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import com.spring.security.entity.WebSiteUserDetail;
import com.spring.security.interfaces.WebSiteUserService;
import com.spring.security.repositories.WebSiteUserRepository;
import com.spring.security.request.WebSiteUserRequest;
import com.spring.security.utility.CommanUtility;

@Service
public class WebSiteUserServiceImpl implements WebSiteUserService {
	private final ModelMapper modelMapper;

	private final WebSiteUserRepository userRepository;

	public WebSiteUserServiceImpl(WebSiteUserRepository userRepository, ModelMapper modelMapper) {
		this.modelMapper = modelMapper;
		this.userRepository = userRepository;
	}

	@Override
	public Map<String, String> createNewUserForWeb(WebSiteUserRequest request, MultipartFile file) {
		Map<String, String> map = new HashMap<String, String>();
		WebSiteUserDetail mapData = modelMapper.map(request, WebSiteUserDetail.class);
		try {
			WebSiteUserDetail detail = userRepository.findByEmail(request.getEmail());
			if (ObjectUtils.isEmpty(detail)) {
				mapData.setStatus("Active");
				mapData.setRole("WEB-USER");
				mapData.setCreatedAt(System.currentTimeMillis());
				if (!file.isEmpty()) {
					mapData.setImgUrl(CommanUtility.uploadFile(file));
				}
				mapData.setDateOfBirth(CommanUtility.dateFormate(mapData.getDateOfBirth()));
				mapData.setPassword(String.valueOf(UUID.randomUUID()));
				WebSiteUserDetail saveData = userRepository.save(mapData);
				map.put("message", "Successfully created a new user?");
				map.put("Password", saveData.getPassword());
				return map;
			}
		} catch (Exception e) {
			throw new RuntimeException("User not Created.");
		}
		map.put("message", "User not created because the user already exists. Please change the email ID.");
		return map;
	}

	@Override
	public String forgotPassword(WebSiteUserRequest request) {
		try {
			String email = request.getEmail();
			String dateOfBirth = CommanUtility.dateFormate(request.getDateOfBirth());
			String username = request.getUsername();
			WebSiteUserDetail user=userRepository.findByEmailAndDateOfBirthAndUsername(email,dateOfBirth,username);
			if(ObjectUtils.isEmpty(user)){
				return "No matching user found. Please check your details and try again.";
			}
			return user.getPassword();
		} catch (Exception e) {
			return "Submit required details for forgot password";
		}
	}

}
