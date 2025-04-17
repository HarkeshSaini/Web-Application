package com.spring.security.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.security.interfaces.UserInfoService;
import com.spring.security.request.UserInfoRequest;

@RestController
@RequestMapping("/api")
public class RestControllers {

	private final UserInfoService infoService;

	public RestControllers(UserInfoService infoService) {
		this.infoService = infoService;
	}

	@PostMapping(value = "/addAdminUser")
	private ResponseEntity<Object> addAdminUser(@RequestBody UserInfoRequest request) {
		return this.infoService.addAdminUser(request);
	}
 

}
