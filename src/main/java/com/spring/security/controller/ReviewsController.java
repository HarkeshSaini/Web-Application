package com.spring.security.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.spring.security.entity.ReviewInfoDetail;
import com.spring.security.interfaces.ReviewsInfoService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class ReviewsController {

	private final ReviewsInfoService infoReviews;

	public ReviewsController(ReviewsInfoService infoReviews) {
		this.infoReviews = infoReviews;
	}

	@GetMapping("/getReviews")
	private String getReviews(HttpServletRequest request, Model model) {
		ResponseEntity<List<ReviewInfoDetail>> userInfo = this.infoReviews.getAllReviews(request);
		model.addAttribute("reviewInfo", userInfo.getBody());
		return "reviews";
	}

}
