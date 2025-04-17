package com.spring.security.interfaces;

import org.springframework.http.ResponseEntity;

import jakarta.servlet.http.HttpServletRequest;

public interface ReviewsInfoService {

	ResponseEntity<Object> getAllReviews(HttpServletRequest request);

}
