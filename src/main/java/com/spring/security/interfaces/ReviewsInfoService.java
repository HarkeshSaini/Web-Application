package com.spring.security.interfaces;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import com.spring.security.request.ReviewInfoRequest;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.constraints.NotNull;

public interface ReviewsInfoService {

	ResponseEntity<List<ReviewInfoRequest>> getAllReviews(HttpServletRequest request);

	ResponseEntity<List<ReviewInfoRequest>> getReviewsByReviewUrl(String reviewUrl);

	List<ReviewInfoRequest> reviewsInfoService();

	ResponseEntity<Object> addNewReviews(ReviewInfoRequest infoRequest, MultipartFile file);

	void deleteReviewInfo(@NotNull int id);

	ResponseEntity<Boolean> updateReviewsByStatus(int id, String value);

	ResponseEntity<ReviewInfoRequest> findByIdReviews(int id);

	ResponseEntity<List<ReviewInfoRequest>> getReviewsWithPaginate(String strValue, HttpServletRequest request);

}
