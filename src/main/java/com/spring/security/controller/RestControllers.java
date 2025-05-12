package com.spring.security.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.security.interfaces.AdminUserInfoService;
import com.spring.security.interfaces.BlogInfoService;
import com.spring.security.interfaces.CategoryInfoService;
import com.spring.security.interfaces.ContactInfoService;
import com.spring.security.interfaces.ReviewsInfoService;
import com.spring.security.request.AdminUserInfoRequest;
import com.spring.security.request.BlogInfoRequest;
import com.spring.security.request.CategoryInfoRequest;
import com.spring.security.request.CategoryReq;
import com.spring.security.request.ContactInfoRequest;
import com.spring.security.request.ReviewInfoRequest;
import com.spring.security.request.SubscribeInfoRequest;
import com.spring.security.utility.CommonUtility;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.constraints.NotNull;

@RestController
@RequestMapping("/api")
public class RestControllers {

	private static final Logger logger = LogManager.getLogger(RestControllers.class);

	private final BlogInfoService blogService;	
	private final AdminUserInfoService infoService;
	private final ReviewsInfoService infoReviews;
	private final CategoryInfoService categoryService;
	private final ContactInfoService contactInfoService;

	public RestControllers(AdminUserInfoService infoService, ReviewsInfoService infoReviews,
			ContactInfoService contactInfoService, CategoryInfoService categoryService, BlogInfoService blogService) {
		this.blogService = blogService;
		this.infoService = infoService;
		this.infoReviews = infoReviews;
		this.categoryService = categoryService;
		this.contactInfoService = contactInfoService;
	}
	
	@GetMapping("/getLatestBlog")
	public ResponseEntity<Object> getLatestBlog() {
	    List<BlogInfoRequest> infoRequest = blogService.getLatestBlog();
	    return new ResponseEntity<>(infoRequest, HttpStatus.OK);
	}

	@PostMapping(value = "/addAdminUser")
	public ResponseEntity<Object> addAdminUser(@RequestBody AdminUserInfoRequest request) {
		try {
			return this.infoService.addAdminUser(request);
		} catch (Exception e) {
			logger.error("Error adding admin user: {}", e.getMessage());
			return ResponseEntity.status(500).body("Error adding admin user");
		}
	}

	@PostMapping("/addNewReviews")
	public ResponseEntity<Object> addNewReviews(@RequestBody ReviewInfoRequest infoRequest) {
	    try {
	        return this.infoReviews.addNewReviews(infoRequest);
	    } catch (Exception e) {
	        logger.error("Error add reviews: {}", e.getMessage());
	        String errorMessage = String.format("Error add reviews: %s", e.getMessage());
	        return ResponseEntity.badRequest().body(errorMessage);
	    }
	}

	
	@GetMapping(value = "/getAllReviews")
	public ResponseEntity<List<ReviewInfoRequest>> getAllReviews(HttpServletRequest request) {
		ResponseEntity<List<ReviewInfoRequest>> allReviews = null;
		try {
			allReviews = this.infoReviews.getAllReviews(request);
			return allReviews;
		} catch (Exception e) {
			logger.error("Error fetching all reviews: {}", e.getMessage());
			return allReviews; // Return an empty list on error
		}
	}

	@GetMapping(value = "/getReviewsByReviewUrl/{reviewUrl}")
	public ResponseEntity<List<ReviewInfoRequest>> getReviewsByReviewUrl(@PathVariable String reviewUrl) {
		try {
			return this.infoReviews.getReviewsByReviewUrl(reviewUrl);
		} catch (Exception e) {
			logger.error("Error fetching reviews by URL '{}': {}", reviewUrl, e.getMessage());
			return ResponseEntity.status(500).body(List.of()); // Return an empty list on error
		}
	}
	
	@GetMapping(value = "/findByIdReviews/{id}")
	public ResponseEntity<ReviewInfoRequest> findByIdReviews(@PathVariable String id) {
		try {
			return this.infoReviews.findByIdReviews(id);
		} catch (Exception e) {
			logger.error("Error add reviews: {}", e.getMessage());
	        return ResponseEntity.badRequest().body(null);
		}
	}
	
	@PostMapping(value = "/updateReviewsByStatus/{id}/{value}")
	public ResponseEntity<Boolean> updateReviewsByStatus(@PathVariable String id, @PathVariable String value) {
		try {
			return this.infoReviews.updateReviewsByStatus(id, value);
		} catch (Exception e) {
			logger.error("Error updating status for contact info ID '{}': {}", id, e.getMessage());
			return ResponseEntity.status(500).body(false);
		}
	}

	@PostMapping(value = "/submitContactInfo")
	public ResponseEntity<String> submitContact(@RequestBody ContactInfoRequest request) {
		try {
			return this.contactInfoService.submitContact(request);
		} catch (Exception e) {
			return ResponseEntity.status(500).body(e.getMessage());
		}
	}

	@GetMapping(value = "/findByIdContactUs/{id}")
	public ResponseEntity<ContactInfoRequest> findByIdContactUs(@PathVariable String id) {
		try {
			return this.contactInfoService.findByIdContactUs(id);
		} catch (Exception e) {
			logger.error("Error finding contact info by ID '{}': {}", id, e.getMessage());
			return ResponseEntity.status(500).body(null);
		}
	}

	@GetMapping(value = "/getAllCategory")
	public List<CategoryInfoRequest> getAllCategory() {
		try {
			return this.categoryService.findAllCategoryByStatus();
		} catch (Exception e) {
			logger.error("Error fetching categories: {}", e.getMessage());
			return List.of(); // Return an empty list on error
		}
	}

	@PostMapping(value = "/updateStatusOfContactUsByStatus/{id}/{value}")
	public ResponseEntity<Boolean> updateStatusOfContactUsByStatus(@PathVariable String id,
			@PathVariable String value) {
		try {
			return this.contactInfoService.updateStatusOfContactUsByStatus(id, value);
		} catch (Exception e) {
			logger.error("Error updating status for contact info ID '{}': {}", id, e.getMessage());
			return ResponseEntity.status(500).body(false);
		}
	}

	@PostMapping(value = "/addCategory")
	public ResponseEntity<String> addCategory(@RequestBody @NotNull CategoryReq request) {
		try {
			return this.categoryService.addCategory(request);
		} catch (Exception e) {
			logger.error("Error adding category: {}", e.getMessage());
			return ResponseEntity.status(500).body("Error adding category");
		}
	}

	@GetMapping(value = "/getCategory")
	public List<CategoryReq> getAllInfoCategory() {
		try {
			return this.categoryService.getAllInfoCategory();
		} catch (Exception e) {
			logger.error("Error fetching category info: {}", e.getMessage());
			return List.of(); // Return an empty list on error
		}
	}

	@GetMapping("/deleteCategory/{id}")
	public String deleteCategory(@NotNull @PathVariable String id, Model model, HttpServletRequest request) {
		try {
			CommonUtility.userRole(request, model);
			ResponseEntity<String> deleteCategory = this.categoryService.deleteCategory(id);
			return deleteCategory.getBody();
		} catch (Exception e) {
			logger.error("Error deleting category with ID '{}': {}", id, e.getMessage());
			return "Error deleting category";
		}
	}
	
	@PostMapping(value = "/subscribe")
	public ResponseEntity<String> subscribe(@RequestBody @NotNull SubscribeInfoRequest request) {
		try {
			return this.contactInfoService.subscribe(request);
		} catch (Exception e) {
			logger.error("Error subscribe: {}", e.getMessage());
			return ResponseEntity.status(500).body("Error subscribe");
		}
	}
	
	@PostMapping(value = "/updateStatusOfSubscribeInfoByStatus/{id}/{value}")
	public ResponseEntity<Boolean> updateStatusOfSubscribeInfoByStatus(@PathVariable String id,@PathVariable String value) {
		try {
			return this.contactInfoService.updateStatusOfSubscribeInfoByStatus(id, value);
		} catch (Exception e) {
			logger.error("Error updating status for Subscribe info ID '{}': {}", id, e.getMessage());
			return ResponseEntity.status(500).body(false);
		}
	}
}
