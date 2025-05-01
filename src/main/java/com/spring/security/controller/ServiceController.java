package com.spring.security.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.spring.security.exception.GlobalExceptionHandler;
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

	/**
	 * Handles the general category page with a single 'url' path variable.
	 * 
	 * @param url   the category URL
	 * @param model the model to add attributes
	 * @return the view name for the category index page
	 */
	@GetMapping("/{url}")
	public String category(@PathVariable String url, HttpServletRequest request, Model model) {
		try {
			List<CategoryInfoRequest> requestDetail = this.categoryService.findAllCategoryByStatusAndCategoryUrl(url);
			ResponseEntity<CategoryReq> categoryRequest = categoryService.getCategoryByUrl(url);
			try {
				if (ObjectUtils.isEmpty(requestDetail)) {
					throw new NotFoundException("Page not found: " + url);
				}
			} catch (Exception e) {
				throw new NotFoundException("Page not found: ");
			}
			model.addAttribute("requestDetail", requestDetail);
			model.addAttribute("categoryRequest", categoryRequest.getBody());
		} catch (IllegalArgumentException e) {
			throw new NotFoundException(e.getLocalizedMessage());
		} catch (Exception e) {
			throw new NotFoundException(e.getMessage());
		}
		return "category/index";
	}

	/**
	 * Handles the specific category page with two path variables: 'url' and
	 * 'categoryUrl'.
	 * 
	 * @param url         the main category URL
	 * @param categoryUrl the specific category URL
	 * @param model       the model to add attributes
	 * @return the view name for the category inner page
	 */

	@GetMapping("/{url}/{categoryUrl}")
	public String categoryPage(@PathVariable String url, HttpServletRequest request, @PathVariable String categoryUrl,
			Model model) {
		try {
			List<CategoryInfoRequest> requestDetail = this.categoryService.findCategory(url, categoryUrl);
			if (requestDetail == null || requestDetail.isEmpty()) {
				throw new NotFoundException("Page not found: ");
			}
			model.addAttribute("requestDetail", requestDetail);
		} catch (IllegalArgumentException e) {
			GlobalExceptionHandler.handleIllegalArgumentException(request, e);
			throw new NotFoundException(e.getLocalizedMessage());
		} catch (Exception e) {
			throw new NotFoundException(e.getMessage());
		}
		return "category/innerPage";
	}
}
