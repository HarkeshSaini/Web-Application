package com.spring.security.controller;

import java.util.List;

import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spring.security.interfaces.UserInfoService;
import com.spring.security.object.UserInfoRequest;

import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/admin")
public class AdminController {

	private final UserInfoService registerService;

	public AdminController(UserInfoService registerService) {
		this.registerService = registerService;
	}

	@GetMapping("/dashboard")
	private String dashboard(HttpServletRequest request, Model model) {
		List<UserInfoRequest> userInfo = this.registerService.getAllUser();
		model.addAttribute("userInfo", userInfo);
		return "admin/dashboard";
	}

	@GetMapping("/getAllUser")
	private String getAllUser(Model model) {
		List<UserInfoRequest> userInfo = this.registerService.getAllUser();
		model.addAttribute("userInfo", userInfo);
		model.addAttribute("message", "All user informations");
		return "admin/showUser";
	}

	@GetMapping("/addUser")
	private String addUser(Model model) {
		model.addAttribute("message", "Add new user for admin!...");
		return "admin/addUser";
	}

	@PostMapping("/addUser")
	private String addUser(@NotNull UserInfoRequest userRequest, Model model) {
		UserInfoRequest addUser = this.registerService.addUser(userRequest);
		if (ObjectUtils.isEmpty(addUser)) {
			model.addAttribute("message", "User not update");
		}
		model.addAttribute("message", "User info successfully submit");
		return "admin/addUser";
	}

	@GetMapping("/editUserInfo/{id}")
	private String editUserInfo(@NotNull @PathVariable String id, Model model) {
		UserInfoRequest userById = this.registerService.getUserById(id);
		model.addAttribute("id", id);
		model.addAttribute("command", userById);
		model.addAttribute("message", "Edit user Info..");
		return "admin/editUser";
	}

	@PostMapping("/editUserInfo/{id}")
	private String editUserInfo(@NotNull @PathVariable String id, @NotNull UserInfoRequest infoRequest, Model model) {
		UserInfoRequest userById = this.registerService.updateUser(id,infoRequest);
		if (ObjectUtils.isEmpty(userById)) {
			model.addAttribute("id", id);
			model.addAttribute("command", infoRequest);
			model.addAttribute("message", "User not updated");
			return "admin/editUser";
		}
		model.addAttribute("id", id);
		model.addAttribute("command", userById);
		model.addAttribute("message", "Successfully updated user Info...");
		return "redirect:/admin/addUser";
	}

	@GetMapping("/deleteUserInfo")
	private String deleteUserInfo(@NotNull @PathVariable String id, Model model) {
		this.registerService.deleteUserById(id);
		return "redirect:/admin/getAllUser";
	}
}
