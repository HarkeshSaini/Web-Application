package com.spring.security.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.spring.security.exception.NotFoundException;
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
		try {
			List<BlogInfoRequest> blogDetail = this.blogService.findAllBlogByStatus();
			if (ObjectUtils.isEmpty(blogDetail)) {
				model.addAttribute("message", "No blog posts available at the moment.");
			}
			model.addAttribute("blogDetail", blogDetail);
		} catch (Exception e) {
			throw new NotFoundException(e.getMessage());
		}
		return "blog/index";
	}

	@GetMapping("/blog/{pageUrl}")
	public String blog(@PathVariable String pageUrl, HttpServletRequest request, Model model) {
		List<BlogInfoRequest> blogDetail = new ArrayList<>();
		try {
			blogDetail = this.blogService.findAllBlogByStatusAndPageUrl(pageUrl);
			if (ObjectUtils.isEmpty(blogDetail)) {
				throw new NotFoundException("Page not found: " + pageUrl);
			}
		}  catch (Exception e) {
			throw new NotFoundException(e.getMessage());
		}
		model.addAttribute("blogDetail", blogDetail);
		return "blog/innerPage";
	}

}
