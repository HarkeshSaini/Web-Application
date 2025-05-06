package com.spring.security.interfaces;

import java.security.Principal;
import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.spring.security.request.WebSiteUserRequest;

public interface WebSiteUserService {

	String[] getWebRole();

	Map<String, String> createNewUserForWeb(WebSiteUserRequest request, MultipartFile file);

	String forgotPassword(WebSiteUserRequest request);

	WebSiteUserRequest findUserByUserName(String name);

	List<WebSiteUserRequest> findAllUser();

	String updateProfiles(WebSiteUserRequest siteUserRequest, MultipartFile file, Principal principal);

}
