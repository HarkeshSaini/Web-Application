package com.spring.security.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.security.interfaces.CategoryInfoService;
import com.spring.security.interfaces.ContactInfoService;
import com.spring.security.interfaces.ReviewsInfoService;
import com.spring.security.interfaces.UserInfoService;
import com.spring.security.request.CategoryInfoRequest;
import com.spring.security.request.CategoryReq;
import com.spring.security.request.ContactInfoRequest;
import com.spring.security.request.ReviewInfoRequest;
import com.spring.security.request.UserInfoRequest;
import com.spring.security.utility.CommanUtility;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.constraints.NotNull;

@RestController
@RequestMapping("/api")
public class RestControllers {

	private final UserInfoService infoService;
	
	private final ReviewsInfoService infoReviews;
	
	private final CategoryInfoService categoryService;
	
	private final ContactInfoService contactInfoService;

	public RestControllers(UserInfoService infoService, ReviewsInfoService infoReviews, ContactInfoService contactInfoService, CategoryInfoService categoryService) {
		this.infoService = infoService;
		this.infoReviews = infoReviews;
		this.categoryService = categoryService;
		this.contactInfoService = contactInfoService;
	}

	@PostMapping(value = "/addAdminUser")
	private ResponseEntity<Object> addAdminUser(@RequestBody UserInfoRequest request) {
		return this.infoService.addAdminUser(request);
	}
 
	@GetMapping(value = "/getAllReviews")
	private List<ReviewInfoRequest> getAllReviews(HttpServletRequest request){
		ResponseEntity<List<ReviewInfoRequest>> allReviews = this.infoReviews.getAllReviews(request);
		return allReviews.getBody();
	}
	
	@GetMapping(value = "/getReviewsByReviewUrl/{reviewUrl}")
	private ResponseEntity<List<ReviewInfoRequest>> getReviewsByReviewUrl(@PathVariable String reviewUrl){
		return this.infoReviews.getReviewsByReviewUrl(reviewUrl);
	}
	
	@PostMapping(value = "/submitContactInfo")
	private ResponseEntity<ContactInfoRequest> submitContact(@RequestBody ContactInfoRequest request){
		return this.contactInfoService.submitContact(request);
	}
	
	@GetMapping(value = "/findByIdContactUs/{id}")
	private ResponseEntity<ContactInfoRequest> findByIdContactUs(@PathVariable String id){
		return this.contactInfoService.findByIdContactUs(id);
	}
	
	@GetMapping(value = "/getAllCategory")
	private List<CategoryInfoRequest> findByIdContact(){
		return this.categoryService.findAllCategoryByStatus();
	}
	
	@PutMapping(value = "/updateStatusOfContactUsByStatus/{id}/{value}")
	private ResponseEntity<Boolean> updateStatusOfContactUsByStatus(@PathVariable String id,@PathVariable String value){
		return this.contactInfoService.updateStatusOfContactUsByStatus(id,value);
	}
	
	@PostMapping(value = "/addCategory")
	private ResponseEntity<String> category(@RequestBody @NotNull CategoryReq request) {
		return this.categoryService.addCategory(request);
	}
	
	@GetMapping(value = "/getCategory")
	private List<CategoryReq> getAllInfoCategory() {
		return this.categoryService.getAllInfoCategory();
	}
	
	@DeleteMapping("/deleteCategory/{id}")
	private void deleteCategory(@NotNull @PathVariable String id, Model model, HttpServletRequest request) {
		CommanUtility.userRole(request, model);
		this.categoryService.deleteCategory(id);
	}
}
