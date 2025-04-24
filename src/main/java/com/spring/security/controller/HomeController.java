package com.spring.security.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;

import com.spring.security.interfaces.DefaultInfoService;
import com.spring.security.request.DefaultInfoRequest;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class HomeController {
	
	private final DefaultInfoService defaultInfoService;
	
	public HomeController(DefaultInfoService defaultInfoService) {
		this.defaultInfoService = defaultInfoService;
	}

	@GetMapping("/")
	public String index(HttpServletRequest request, Model model) {
		model.addAttribute("message", "success");
		return "index";
	}

	@GetMapping("/contact-us")
	public String contactUs(HttpServletRequest request, Model model) {
		model.addAttribute("message", "success");
		return "contactUs";
	}

	@GetMapping("/about-us")
	public String aboutUs(HttpServletRequest request, Model model) {
		List<DefaultInfoRequest> defaultDetail = defaultInfoService.findAllDefaultByStatusAndPageUrl("about-us");
		model.addAttribute("defaultDetail", defaultDetail);
		if(ObjectUtils.isEmpty(defaultDetail)) {
			throw new RuntimeException("Default Info Contant Not found:");
		}
		return "default";
	}

}
