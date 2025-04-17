package com.spring.security.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.spring.security.entity.ReviewInfoDetail;
import com.spring.security.interfaces.ReviewsInfoService;
import com.spring.security.repositories.ReviewsInfoRepository;
import com.spring.security.request.ReviewInfoRequest;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class ReviewsInfoServiceImpl implements ReviewsInfoService{
	
	private final ReviewsInfoRepository reviewsInfoRepository;

	public ReviewsInfoServiceImpl(ReviewsInfoRepository reviewsInfoRepository) {
		this.reviewsInfoRepository = reviewsInfoRepository;
	}

	@Override
	public ResponseEntity<List<ReviewInfoDetail>> getAllReviews(HttpServletRequest request) {
		List<ReviewInfoDetail> findAll = reviewsInfoRepository.findAll();
		if(ObjectUtils.isEmpty(findAll)) {
			return ResponseEntity.badRequest().body(null);
		}
		return ResponseEntity.ok().body(findAll);
	}

}
