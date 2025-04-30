package com.spring.security.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.spring.security.interfaces.CategoryInfoService;
import com.spring.security.interfaces.ContactInfoService;
import com.spring.security.interfaces.ReviewsInfoService;
import com.spring.security.interfaces.UserInfoService;
import com.spring.security.request.*;
import com.spring.security.utility.CommanUtility;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.constraints.NotNull;

@RestController
@RequestMapping("/api")
public class RestControllers {

    private static final Logger logger = LogManager.getLogger(RestControllers.class);

    private final UserInfoService infoService;
    private final ReviewsInfoService infoReviews;
    private final CategoryInfoService categoryService;
    private final ContactInfoService contactInfoService;

    public RestControllers(UserInfoService infoService, ReviewsInfoService infoReviews, 
                           ContactInfoService contactInfoService, CategoryInfoService categoryService) {
        this.infoService = infoService;
        this.infoReviews = infoReviews;
        this.categoryService = categoryService;
        this.contactInfoService = contactInfoService;
    }

    @PostMapping(value = "/addAdminUser")
    public ResponseEntity<Object> addAdminUser(@RequestBody UserInfoRequest request) {
        try {
            return this.infoService.addAdminUser(request);
        } catch (Exception e) {
            logger.error("Error adding admin user: {}", e.getMessage());
            return ResponseEntity.status(500).body("Error adding admin user");
        }
    }

    @GetMapping(value = "/getAllReviews")
    public List<ReviewInfoRequest> getAllReviews(HttpServletRequest request) {
        try {
            ResponseEntity<List<ReviewInfoRequest>> allReviews = this.infoReviews.getAllReviews(request);
            return allReviews.getBody();
        } catch (Exception e) {
            logger.error("Error fetching all reviews: {}", e.getMessage());
            return List.of(); // Return an empty list on error
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

    @PostMapping(value = "/submitContactInfo")
    public ResponseEntity<ContactInfoRequest> submitContact(@RequestBody ContactInfoRequest request) {
        try {
            return this.contactInfoService.submitContact(request);
        } catch (Exception e) {
            logger.error("Error submitting contact info: {}", e.getMessage());
            return ResponseEntity.status(500).body(null);
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

    @PutMapping(value = "/updateStatusOfContactUsByStatus/{id}/{value}")
    public ResponseEntity<Boolean> updateStatusOfContactUsByStatus(@PathVariable String id, @PathVariable String value) {
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

    @DeleteMapping("/deleteCategory/{id}")
    public String deleteCategory(@NotNull @PathVariable String id, Model model, HttpServletRequest request) {
        try {
            CommanUtility.userRole(request, model);
            ResponseEntity<String> deleteCategory = this.categoryService.deleteCategory(id);
            return deleteCategory.getBody();
        } catch (Exception e) {
            logger.error("Error deleting category with ID '{}': {}", id, e.getMessage());
            return "Error deleting category";
        }
    }
}
