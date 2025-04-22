package com.spring.security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class UserController {

	@GetMapping("/login")
	public String index(HttpServletRequest request, Model model) {
		model.addAttribute("message", "success");
		return "userAdmin/login";
	}

}
