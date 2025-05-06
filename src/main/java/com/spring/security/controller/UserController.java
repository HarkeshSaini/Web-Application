package com.spring.security.controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.spring.security.exception.NotFoundException;
import com.spring.security.interfaces.WebSiteUserService;
import com.spring.security.request.WebSiteUserRequest;

import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/user")
public class UserController {

	private final WebSiteUserService userService;

	public UserController(WebSiteUserService userService) {
		this.userService = userService;
	}

	@GetMapping("/dashboard")
	public String userDashboard(Principal principal, HttpServletRequest request, Model model) {
		try {
			WebSiteUserRequest userRequest = userService.findUserByUserName(principal.getName());
			model.addAttribute("message", "Hello, and welcome back! Here’s your personal dashboard where you can manage your data and settings.");
			model.addAttribute("userDetail", userRequest);
		} catch (NotFoundException e) {
			throw new NotFoundException(e.getMessage());
		} catch (Exception e) {
			throw new NotFoundException(e.getMessage());
		}
		return "webUser/dashboard";
	}

	@GetMapping("/information")
	public String userInformation(Principal principal, HttpServletRequest request, Model model) {
		try {
			WebSiteUserRequest userRequest = userService.findUserByUserName(principal.getName());
			model.addAttribute("message", "All your information is here. Please review and update as needed.");
			model.addAttribute("userDetail", userRequest);
		} catch (NotFoundException e) {
			throw new NotFoundException(e.getMessage());
		} catch (Exception e) {
			throw new NotFoundException(e.getMessage());
		}
		return "webUser/information";
	}

	@GetMapping("/update-profile")
	public String updateProfile(Principal principal, HttpServletRequest request, Model model) {
		try {
			WebSiteUserRequest userRequest = userService.findUserByUserName(principal.getName());
			model.addAttribute("message", "Please update your information to keep your profile current.");
			model.addAttribute("userDetail", userRequest);
			model.addAttribute("id", userRequest.getId());
			model.addAttribute("command", userRequest);
		} catch (NotFoundException e) {
			throw new NotFoundException(e.getMessage());
		} catch (Exception e) {
			throw new NotFoundException(e.getMessage());
		}
		return "webUser/updateProfile";
	}
 

	@PostMapping("/update-profile")
	public String updateProfiles(WebSiteUserRequest siteUserRequest, Principal principal, HttpServletRequest request,MultipartFile file,Model model) {
		try {
			String messString = userService.updateProfiles(siteUserRequest,file,principal);
			model.addAttribute("message", messString);
			WebSiteUserRequest userRequest = userService.findUserByUserName(principal.getName());
			model.addAttribute("userDetail", userRequest);
			model.addAttribute("id", userRequest.getId());
			model.addAttribute("command", userRequest);
		} catch (NotFoundException e) {
			throw new NotFoundException(e.getMessage());
		} catch (Exception e) {
			throw new NotFoundException(e.getMessage());
		}
		return "webUser/updateProfile";
	}

}
