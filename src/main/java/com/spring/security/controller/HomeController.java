package com.spring.security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class HomeController {

	@GetMapping("/")
	public String index(HttpServletRequest request, Model model) {
		model.addAttribute("message", "success");
		return "index";
	}

	@GetMapping("/admin")
	public String dashBoardLogin(HttpServletRequest request, Model model) {
		model.addAttribute("message", "success");
		return "admin/login";
	}

}
