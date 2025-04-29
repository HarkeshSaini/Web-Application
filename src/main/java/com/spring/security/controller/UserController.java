package com.spring.security.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import com.spring.security.exception.GlobalExceptionHandler;
import com.spring.security.exception.NotFoundException;
import com.spring.security.interfaces.WebSiteUserService;
import com.spring.security.request.WebSiteUserRequest;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class UserController {

	private final WebSiteUserService userService;

	public UserController(WebSiteUserService userService) {
		this.userService = userService;
	}

	@GetMapping("/login")
	public String index(HttpServletRequest request, Model model) {
		model.addAttribute("message", "Welcome back! Please sign in.");
		return "webUser/login";
	}

	@GetMapping("/sign-up")
	public String signUp(HttpServletRequest request, Model model) {
		model.addAttribute("message", "Welcome back! Please sign in.");
		return "webUser/signUp";
	}

	@PostMapping("/sign-up")
	private String createNewUserForWeb(WebSiteUserRequest request, MultipartFile file, Model model) {
		try {
			Map<String, String> messString = userService.createNewUserForWeb(request, file);
			model.addAttribute("message", (String) messString.get("message"));
			model.addAttribute("Password", (String) messString.get("Password"));
		} catch (NotFoundException e) {
			GlobalExceptionHandler.handleNotFoundException(e);
		}
		return "webUser/signUp";
	}

	@GetMapping("/forgotPassword")
	private String forgotPassword(HttpServletRequest request, Model model) {
		model.addAttribute("message", "Please provide the required details and then click on 'Forgot Password'.");
		return "webUser/forPass";
	}

	@PostMapping("/forgotPassword")
	private String forgotPasswordRet(WebSiteUserRequest request, Model model) {
		try {
			String password = userService.forgotPassword(request);
			model.addAttribute("message", "Your forgot password:- " + password);
		} catch (NotFoundException e) {
			GlobalExceptionHandler.handleNotFoundException(e);
		}
		return "webUser/forPass";
	}

}
