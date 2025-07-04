package com.spring.security.controller;

import com.spring.security.interfaces.*;
import com.spring.security.request.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.constraints.NotNull;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api")
public class RestControllers {

	private static final Logger logger = LogManager.getLogger(RestControllers.class);

	private final BlogInfoService blogService;
	private final AdminUserInfoService infoService;
	private final ReviewsInfoService infoReviews;
	private final CategoryInfoService categoryService;
	private final ContactInfoService contactInfoService;

	public RestControllers(AdminUserInfoService infoService, ReviewsInfoService infoReviews, ContactInfoService contactInfoService, CategoryInfoService categoryService, BlogInfoService blogService) {
		this.blogService = blogService;
		this.infoService = infoService;
		this.infoReviews = infoReviews;
		this.categoryService = categoryService;
		this.contactInfoService = contactInfoService;
	}

	@GetMapping("/getLatestBlog")
	public ResponseEntity<List<BlogInfoRequest>> getLatestBlog() {
		return ResponseEntity.ok(blogService.getLatestBlog(6));
	}

	@PostMapping("/addAdminUser")
	public ResponseEntity<Object> addAdminUser(@RequestBody AdminUserInfoRequest request) {
		return handleServiceCall(() -> infoService.addAdminUser(request));
	}

	@PostMapping("/addNewReviews")
	public ResponseEntity<?> addNewReviews(@RequestParam String userName, @RequestParam String userEmail, @RequestParam String reviewMessage, @RequestParam String rating, @RequestParam String reviewUrl,@RequestParam(required = false) MultipartFile file) {
		boolean status = !StringUtils.hasText(userEmail) || !StringUtils.hasText(userName);
		String messages="Please provide your name and email to submit.";
		ReviewInfoRequest infoRequest=new ReviewInfoRequest();
		infoRequest.setUserName(userName);
		infoRequest.setUserEmail(userEmail);
		infoRequest.setReviewMessage(reviewMessage);
		infoRequest.setReviewRating(Integer.valueOf(rating));
		infoRequest.setReviewUrl(reviewUrl);
		return status ? ResponseEntity.ok(messages): handleServiceCall(() -> infoReviews.addNewReviews(infoRequest ,file));
	}

	@GetMapping("/getAllReviews")
	public ResponseEntity<List<ReviewInfoRequest>> getAllReviews(HttpServletRequest request) {
		return handleServiceCall(() -> infoReviews.getAllReviews(request));
	}

	@GetMapping("/getReviewsWithPaginate/{strValue}")
	public ResponseEntity<List<ReviewInfoRequest>> getReviewsWithPaginate(@PathVariable String strValue, HttpServletRequest request) {
		return handleServiceCall(() -> infoReviews.getReviewsWithPaginate(strValue, request));
	}

	@GetMapping("/getReviewsByReviewUrl/{reviewUrl}")
	public ResponseEntity<List<ReviewInfoRequest>> getReviewsByReviewUrl(@PathVariable String reviewUrl) {
		return handleServiceCall(() -> infoReviews.getReviewsByReviewUrl(reviewUrl));
	}

	@GetMapping("/findByIdReviews/{id}")
	public ResponseEntity<ReviewInfoRequest> findByIdReviews(@PathVariable int id) {
		return handleServiceCall(() -> infoReviews.findByIdReviews(id));
	}
// data 
	@PostMapping("/updateReviewsByStatus/{id}/{value}")
	public ResponseEntity<Boolean> updateReviewsByStatus(@PathVariable int id, @PathVariable String value) {
		return handleServiceCall(() -> infoReviews.updateReviewsByStatus(id, value));
	}

	@PostMapping("/submitContactInfo")
	public ResponseEntity<?> submitContact(@RequestBody ContactInfoRequest request) {
		boolean status = !StringUtils.hasText(request.getEmail()) || !StringUtils.hasText(request.getName());
		String messages="Please provide your name and email to submit.";
		return status ? ResponseEntity.ok(messages): handleServiceCall(() -> contactInfoService.submitContact(request));
	}
		 

	@GetMapping("/findByIdContactUs/{id}")
	public ResponseEntity<ContactInfoRequest> findByIdContactUs(@PathVariable int id) {
		return handleServiceCall(() -> contactInfoService.findByIdContactUs(id));
	}

	@GetMapping("/getAllCategory")
	public ResponseEntity<List<CategoryInfoRequest>> getAllCategory() {
		return ResponseEntity.ok(categoryService.findAllCategoryByStatus());
	}

	@PostMapping("/updateStatusOfContactUsByStatus/{id}/{value}")
	public ResponseEntity<Boolean> updateStatusOfContactUsByStatus(@PathVariable int id, @PathVariable String value) {
		return handleServiceCall(() -> contactInfoService.updateStatusOfContactUsByStatus(id, value));
	}

	@PostMapping("/addCategory")
	public ResponseEntity<String> addCategory(@RequestBody @NotNull CategoryReq request) {
		return handleServiceCall(() -> categoryService.addCategory(request));
	}

	@GetMapping("/getCategory")
	public ResponseEntity<List<CategoryReq>> getAllInfoCategory() {
		return ResponseEntity.ok(categoryService.getAllInfoCategory());
	}

	@GetMapping("/deleteCategory/{id}")
	public String deleteCategory(@NotNull @PathVariable int id, HttpServletRequest request) {
		return categoryService.deleteCategory(id).getBody();
	}

	@PostMapping("/subscribe")
	public ResponseEntity<String> subscribe(@RequestBody @NotNull SubscribeInfoRequest request) {
		return handleServiceCall(() -> contactInfoService.subscribe(request));
	}

	@PostMapping("/updateStatusOfSubscribeInfoByStatus/{id}/{value}")
	public ResponseEntity<Boolean> updateStatusOfSubscribeInfoByStatus(@PathVariable int id, @PathVariable String value) {
		return handleServiceCall(() -> contactInfoService.updateStatusOfSubscribeInfoByStatus(id, value));
	}

	private <T> ResponseEntity<T> handleServiceCall(ServiceCall<ResponseEntity<T>> serviceCall) {
		try {
			return serviceCall.execute();
		} catch (Exception e) {
			logger.error("Error: {}", e.getMessage(), e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@FunctionalInterface
	private interface ServiceCall<T> {
		T execute() throws Exception;
	}
}