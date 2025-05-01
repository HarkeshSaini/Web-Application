package com.spring.security.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.spring.security.entity.WebSiteUserDetail;
import com.spring.security.interfaces.WebSiteUserService;
import com.spring.security.repositories.WebSiteUserRepository;
import com.spring.security.request.WebSiteUserRequest;
import com.spring.security.utility.CommonUtility;

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
		Map<String, String> response = new HashMap<>();

		// Check if the user already exists
		WebSiteUserDetail existingUser = userRepository.findByEmail(request.getEmail());
		if (existingUser != null) {
			response.put("message", "User already exists. Please change the email ID.");
			return response;
		}

		try {
			WebSiteUserDetail user = modelMapper.map(request, WebSiteUserDetail.class);
			user.setStatus("Active");
			user.setRole("WEB-USER");
			user.setCreatedAt(System.currentTimeMillis());
			user.setDateOfBirth(CommonUtility.dateFormate(request.getDateOfBirth()));

			// Handle file upload if present
			if (!file.isEmpty()) {
				user.setImgUrl(CommonUtility.uploadFile(file));
			}

			// Secure password handling (you should use a password hash in production)
			user.setPassword(UUID.randomUUID().toString()); // Temporary placeholder, replace with password hashing

			WebSiteUserDetail savedUser = userRepository.save(user);

			response.put("message", "Successfully created a new user.");
			response.put("Password", savedUser.getPassword()); // Return the password for initial login (in real life,
																// it should be hashed)
		} catch (Exception e) {
			response.put("message", "An error occurred while creating the user.");
		}

		return response;
	}

	@Override
	public String forgotPassword(WebSiteUserRequest request) {
		try {
			String email = request.getEmail();
			String dateOfBirth = CommonUtility.dateFormate(request.getDateOfBirth());
			String username = request.getUsername();

			// Fetch user based on email, dateOfBirth, and username
			WebSiteUserDetail user = userRepository.findByEmailAndDateOfBirthAndUsername(email, dateOfBirth, username);

			if (user == null) {
				return "No matching user found. Please check your details and try again.";
			}

			return user.getPassword();
		} catch (Exception e) {
			return "An error occurred. Please provide the required details for password recovery.";
		}
	}

	@Override
	public String[] getWebRole() {
		List<WebSiteUserDetail> findAll = userRepository.findAll();
		return findAll.stream().map(WebSiteUserDetail::getRole).toArray(String[]::new);
	}
}
