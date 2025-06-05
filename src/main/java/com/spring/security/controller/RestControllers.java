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
		return ResponseEntity.ok(blogService.getLatestBlog(3));
	}

	@PostMapping("/addAdminUser")
	public ResponseEntity<Object> addAdminUser(@RequestBody AdminUserInfoRequest request) {
		return handleServiceCall(() -> infoService.addAdminUser(request));
	}

	@PostMapping("/addNewReviews")
	public ResponseEntity<Object> addNewReviews(@RequestParam("userName") String userName, @RequestParam("userEmail") String userEmail, @RequestParam("reviewMessage") String reviewMessage, @RequestParam("rating") String rating, @RequestParam("reviewUrl") String reviewUrl,@RequestParam(value = "file", required = false) MultipartFile file) {
		ReviewInfoRequest infoRequest=new ReviewInfoRequest();
		infoRequest.setUserName(userName);
		infoRequest.setUserEmail(userEmail);
		infoRequest.setReviewMessage(reviewMessage);
		int rattings=!StringUtils.hasText(rating)? 1:Integer.valueOf(rating);
		infoRequest.setReviewRating(rattings);
		infoRequest.setReviewUrl(reviewUrl);
		return handleServiceCall(() -> infoReviews.addNewReviews(infoRequest ,file));
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
	public ResponseEntity<ReviewInfoRequest> findByIdReviews(@PathVariable String id) {
		return handleServiceCall(() -> infoReviews.findByIdReviews(id));
	}

	@PostMapping("/updateReviewsByStatus/{id}/{value}")
	public ResponseEntity<Boolean> updateReviewsByStatus(@PathVariable String id, @PathVariable String value) {
		return handleServiceCall(() -> infoReviews.updateReviewsByStatus(id, value));
	}

	@PostMapping("/submitContactInfo")
	public ResponseEntity<String> submitContact(@RequestBody ContactInfoRequest request) {
		return handleServiceCall(() -> contactInfoService.submitContact(request));
	}

	@GetMapping("/findByIdContactUs/{id}")
	public ResponseEntity<ContactInfoRequest> findByIdContactUs(@PathVariable String id) {
		return handleServiceCall(() -> contactInfoService.findByIdContactUs(id));
	}

	@GetMapping("/getAllCategory")
	public ResponseEntity<List<CategoryInfoRequest>> getAllCategory() {
		return ResponseEntity.ok(categoryService.findAllCategoryByStatus());
	}

	@PostMapping("/updateStatusOfContactUsByStatus/{id}/{value}")
	public ResponseEntity<Boolean> updateStatusOfContactUsByStatus(@PathVariable String id, @PathVariable String value) {
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
	public String deleteCategory(@NotNull @PathVariable String id, HttpServletRequest request) {
		return categoryService.deleteCategory(id).getBody();
	}

	@PostMapping("/subscribe")
	public ResponseEntity<String> subscribe(@RequestBody @NotNull SubscribeInfoRequest request) {
		return handleServiceCall(() -> contactInfoService.subscribe(request));
	}

	@PostMapping("/updateStatusOfSubscribeInfoByStatus/{id}/{value}")
	public ResponseEntity<Boolean> updateStatusOfSubscribeInfoByStatus(@PathVariable String id, @PathVariable String value) {
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