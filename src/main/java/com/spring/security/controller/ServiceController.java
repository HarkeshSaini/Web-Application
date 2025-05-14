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

@Controller
public class ServiceController {

	private final CategoryInfoService categoryService;

	public ServiceController(CategoryInfoService categoryService) {
		this.categoryService = categoryService;
	}

     /* Handles the general category page with a single 'url' path variable.
	 * @param url   the category URL
     * @param model the model to add attributes
     * @return the view name for the category index page
     */

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

	/**
	 * Handles the specific category page with two path variables: 'url' and 'categoryUrl'.
	 *
	 * @param url         the main category URL
	 * @param categoryUrl the specific category URL
	 * @param model       the model to add attributes
	 * @return the view name for the category inner page
	 */

	@GetMapping("/{url}/{categoryUrl}")
	public String categoryPage(@PathVariable String url, @PathVariable String categoryUrl, Model model) {
		List<CategoryInfoRequest> requestDetail = categoryService.findCategory(url, categoryUrl);
		if (ObjectUtils.isEmpty(requestDetail)) {
			throw new NotFoundException("Page not found for URL: " + url + "/" + categoryUrl);
		}
		model.addAttribute("requestDetail", requestDetail);
		return "category/innerPage";
	}
}