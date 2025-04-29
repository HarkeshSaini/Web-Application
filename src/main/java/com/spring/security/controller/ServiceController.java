package com.spring.security.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.spring.security.exception.GlobalExceptionHandler;
import com.spring.security.interfaces.CategoryInfoService;
import com.spring.security.request.CategoryInfoRequest;
import com.spring.security.request.CategoryReq;

@Controller
public class ServiceController {

	private final CategoryInfoService categoryService;

	public ServiceController(CategoryInfoService categoryService) {
		this.categoryService = categoryService;
	}

	@GetMapping("/{url}")
	public String category(@PathVariable String url, Model model) {
		try {
			List<CategoryInfoRequest> requestDetail = this.categoryService.findAllCategoryByStatusAndCategoryUrl(url);
			CategoryReq categoryRequest = categoryService.getCategoryByUrl(url);
			model.addAttribute("requestDetail", requestDetail);
			model.addAttribute("categoryRequest", categoryRequest);
		} catch (IllegalArgumentException e) {
			GlobalExceptionHandler.handleIllegalArgumentException(e);
		}
		return "category/index";
	}
	
	@GetMapping("/{url}/{categoryUrl}")
	public String categoryPage(@PathVariable String url,@PathVariable String categoryUrl, Model model) {
		try {
			List<CategoryInfoRequest> requestDetail = this.categoryService.findCatergory(url,categoryUrl);
			model.addAttribute("requestDetail", requestDetail);
		} catch (IllegalArgumentException e) {
			GlobalExceptionHandler.handleIllegalArgumentException(e);
		}
		return "category/innerPage";
	}

}
