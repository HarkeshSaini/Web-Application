package com.spring.security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class HomeController {

	@GetMapping("/")
	public String index(HttpServletRequest request, Model model) {
		model.addAttribute("status", "success");
		return "index";
	}

	@GetMapping("/login")
	public String dashBoardLogin(HttpServletRequest request, Model model) {
		model.addAttribute("status", "success");
		return "admin/login";
	}

}
