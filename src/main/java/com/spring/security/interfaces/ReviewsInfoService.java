package com.spring.security.interfaces;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.spring.security.entity.ReviewInfoDetail;

import jakarta.servlet.http.HttpServletRequest;

public interface ReviewsInfoService {

	ResponseEntity<List<ReviewInfoDetail>> getAllReviews(HttpServletRequest request);

}
