package com.spring.security.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
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

	private static final Logger logger = LogManager.getLogger(ServiceController.class);

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
	public String category(@PathVariable String url, Model model) {
		try {
			logger.info("Fetching category details for URL: {}", url);
			// Fetch category details based on the provided 'url'
			List<CategoryInfoRequest> requestDetail = this.categoryService.findAllCategoryByStatusAndCategoryUrl(url);
			ResponseEntity<CategoryReq> categoryRequest = categoryService.getCategoryByUrl(url);

			if (requestDetail == null || categoryRequest.getBody() == null) {
				logger.warn("No category details found for URL: {}", url);
			}

			// Add the fetched details to the model
			model.addAttribute("requestDetail", requestDetail);
			model.addAttribute("categoryRequest", categoryRequest.getBody());
		} catch (IllegalArgumentException e) {
			logger.error("Error occurred while fetching category for URL: {}", url);
			GlobalExceptionHandler.handleIllegalArgumentException(e);
		} catch (Exception e) {
			logger.error("Unexpected error occurred while fetching category for URL: {}", url);
			GlobalExceptionHandler.handleException(e);
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
	public String categoryPage(@PathVariable String url, @PathVariable String categoryUrl, Model model) {
		try {
			logger.info("Fetching category page details for URL: {} and category URL: {}", url, categoryUrl);

			// Fetch specific category details based on both 'url' and 'categoryUrl'
			List<CategoryInfoRequest> requestDetail = this.categoryService.findCategory(url, categoryUrl);

			if (requestDetail == null || requestDetail.isEmpty()) {
				logger.warn("No details found for category URL: {}", categoryUrl);
			}

			// Add the fetched details to the model
			model.addAttribute("requestDetail", requestDetail);
		} catch (IllegalArgumentException e) {
			logger.error("Error occurred while fetching category page for URL: {} and category URL: {}", url,
					categoryUrl);
			GlobalExceptionHandler.handleIllegalArgumentException(e);
		} catch (Exception e) {
			logger.error("Unexpected error occurred while fetching category page for URL: {} and category URL: {}", url,categoryUrl);
			GlobalExceptionHandler.handleException(e);
		}
		return "category/innerPage";
	}
}
