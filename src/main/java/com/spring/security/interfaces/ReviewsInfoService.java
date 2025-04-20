package com.spring.security.interfaces;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.spring.security.request.ReviewInfoRequest;

import jakarta.servlet.http.HttpServletRequest;

public interface ReviewsInfoService {

	ResponseEntity<List<ReviewInfoRequest>> getAllReviews(HttpServletRequest request);

	ResponseEntity<List<ReviewInfoRequest>> getReviewsByReviewUrl(String reviewUrl);

}
