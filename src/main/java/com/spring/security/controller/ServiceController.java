package com.spring.security.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.spring.security.interfaces.CategoryInfoService;
import com.spring.security.request.CategoryInfoRequest;

@Controller
public class ServiceController {

	private final CategoryInfoService categoryService;

	public ServiceController(CategoryInfoService categoryService) {
		this.categoryService = categoryService;
	}

	@GetMapping("/category/{categoryUrl}")
	public String category(@PathVariable String categoryUrl, Model model) {
		List<CategoryInfoRequest> requestDetail = this.categoryService.findAllCategoryByStatusAndCategoryUrl(categoryUrl);
		model.addAttribute("requestDetail", requestDetail);
		if (ObjectUtils.isEmpty(requestDetail)) {
			throw new RuntimeException("Category Contant Not found:");
		}
		return "category/index";
	}

}
