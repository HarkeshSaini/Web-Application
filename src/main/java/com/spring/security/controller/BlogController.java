package com.spring.security.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.spring.security.interfaces.BlogInfoService;
import com.spring.security.request.BlogInfoRequest;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class BlogController {

	private final BlogInfoService blogService;

	public BlogController(BlogInfoService blogService) {
		this.blogService = blogService;
	}

	@GetMapping("/blog")
	public String blog(HttpServletRequest request, Model model) {
		List<BlogInfoRequest> blogDetail = this.blogService.findAllBlogByStatus();
		model.addAttribute("blogDetail", blogDetail);
		return "blog/index";
	}

}
