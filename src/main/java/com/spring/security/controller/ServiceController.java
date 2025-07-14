package com.spring.security.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.spring.security.exception.NotFoundException;
import com.spring.security.interfaces.CategoryInfoService;
import com.spring.security.request.CategoryInfoRequest;
import com.spring.security.request.CategoryReq;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class ServiceController {

	private final CategoryInfoService categoryService;

	public ServiceController(CategoryInfoService categoryService) {
		this.categoryService = categoryService;
	}
 

	@GetMapping("/{url}")
	public String category(@PathVariable String url, Model model) {
		List<CategoryInfoRequest> requestDetail = categoryService.findAllCategoryByStatusAndCategoryUrl(url);
		if (ObjectUtils.isEmpty(requestDetail)) {
			throw new NotFoundException("Page not found: " + url);
		}
		ResponseEntity<CategoryReq> categoryRequest = categoryService.getCategoryByUrl(url);
		model.addAttribute("requestDetail", requestDetail);
		model.addAttribute("categoryRequest", Optional.ofNullable(categoryRequest.getBody()).orElseThrow(() -> new NotFoundException("Category not found for URL: " + url)));
		return "category/index";
	}

	 
	@GetMapping("/{url}/{categoryUrl}")
	public String categoryPage(@PathVariable String url, @PathVariable String categoryUrl, Model model) {
		List<CategoryInfoRequest> requestDetail = categoryService.findCategory(url, categoryUrl);
		if (ObjectUtils.isEmpty(requestDetail)) {
			throw new NotFoundException("Page not found for URL: " + url + "/" + categoryUrl);
		}
		model.addAttribute("requestDetail", requestDetail);
		return "category/innerPage";
	}
	
	@GetMapping("/services")
	public String servicePage(HttpServletRequest request) {
		return "service/search";
	}
	
	@GetMapping("/support")
	public String supportPage(HttpServletRequest request) {
		return "support/index";
	}
}