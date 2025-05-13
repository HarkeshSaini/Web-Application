package com.spring.security.interfaces;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.spring.security.request.ReviewInfoRequest;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.constraints.NotNull;

public interface ReviewsInfoService {

	ResponseEntity<List<ReviewInfoRequest>> getAllReviews(HttpServletRequest request);

	ResponseEntity<List<ReviewInfoRequest>> getReviewsByReviewUrl(String reviewUrl);

	List<ReviewInfoRequest> reviewsInfoService();

	ResponseEntity<Object> addNewReviews(ReviewInfoRequest infoRequest);

	void deleteReviewInfo(@NotNull String id);

	ResponseEntity<Boolean> updateReviewsByStatus(String id, String value);

	ResponseEntity<ReviewInfoRequest> findByIdReviews(String id);

	ResponseEntity<List<ReviewInfoRequest>> getReviewsWithPaginate(String strValue, HttpServletRequest request);

}
