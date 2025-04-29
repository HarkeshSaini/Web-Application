package com.spring.security.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.spring.security.exception.GlobalExceptionHandler;
import com.spring.security.exception.NotFoundException;
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
		try {
			String str="about-us";
			List<DefaultInfoRequest> defaultDetail = defaultInfoService.findAllDefaultByStatusAndPageUrl(str);
			model.addAttribute("defaultDetail", defaultDetail);
		} catch (NotFoundException e) {
			GlobalExceptionHandler.handleNotFoundException(e);
		}
		return "default";
	}
	
	@GetMapping("/terms-and-conditions")
	public String termsAndconditions(HttpServletRequest request, Model model) {
		try {
			String str="terms-and-conditions";
			List<DefaultInfoRequest> defaultDetail = defaultInfoService.findAllDefaultByStatusAndPageUrl(str);
			model.addAttribute("defaultDetail", defaultDetail);
		} catch (NotFoundException e) {
			GlobalExceptionHandler.handleNotFoundException(e);
		}
		return "default";
	}
	
	@GetMapping("/privacy-policy")
	public String privacyPolicy(HttpServletRequest request, Model model) {
		try {
			String str="privacy-policy";
			List<DefaultInfoRequest> defaultDetail = defaultInfoService.findAllDefaultByStatusAndPageUrl(str);
			model.addAttribute("defaultDetail", defaultDetail);
		} catch (NotFoundException e) {
			GlobalExceptionHandler.handleNotFoundException(e);
		}
		return "default";
	}

}
