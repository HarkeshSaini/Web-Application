package com.spring.security.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.spring.security.exception.NotFoundException;
import com.spring.security.interfaces.BlogInfoService;
import com.spring.security.request.BlogInfoRequest;

@Controller
public class BlogController {

	private final BlogInfoService blogService;

	public BlogController(BlogInfoService blogService) {
		this.blogService = blogService;
	}

	@GetMapping("/blog")
	public String blog(Model model) {
		var allBlogByStatus = blogService.findAllBlogByStatus();
		List<BlogInfoRequest> blogDetail = Optional.ofNullable(allBlogByStatus)
		.filter(details -> !details.isEmpty())
		.orElseThrow(() -> new NotFoundException("No blog posts available at the moment."));
		model.addAttribute("blogDetail", blogDetail);
		return "blog/index";
	}

	@GetMapping("/blog/{pageUrl}")
	public String blog(@PathVariable String pageUrl, Model model) {
		var allBlogByStatusAndPageUrl = blogService.findAllBlogByStatusAndPageUrl(pageUrl);
		List<BlogInfoRequest> blogDetail = Optional.ofNullable(allBlogByStatusAndPageUrl)
		.filter(details -> !details.isEmpty())
		.orElseThrow(() -> new NotFoundException("Page not found: " + pageUrl));
		model.addAttribute("blogDetail", blogDetail);
		model.addAttribute("recentBlog", blogService.getLatestBlog(5));
		return "blog/innerPage";
	}
}