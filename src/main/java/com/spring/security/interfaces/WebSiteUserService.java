package com.spring.security.interfaces;

import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.spring.security.request.WebSiteUserRequest;

public interface WebSiteUserService {

	Map<String, String> createNewUserForWeb(WebSiteUserRequest request, MultipartFile file);

	String forgotPassword(WebSiteUserRequest request);

}
