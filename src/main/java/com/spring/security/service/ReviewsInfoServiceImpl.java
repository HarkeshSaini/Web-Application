package com.spring.security.service;

import java.util.List;

import org.modelmapper.ModelMapper;
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
	
	private final ModelMapper modelMapper;
	
	private final ReviewsInfoRepository reviewsInfoRepository;

	public ReviewsInfoServiceImpl(ReviewsInfoRepository reviewsInfoRepository, ModelMapper modelMapper) {
		this.modelMapper = modelMapper;
		this.reviewsInfoRepository = reviewsInfoRepository;
	}

	@Override
	public ResponseEntity<List<ReviewInfoRequest>> getAllReviews(HttpServletRequest request) {
		List<ReviewInfoDetail> findAll = reviewsInfoRepository.findAll();
		if(ObjectUtils.isEmpty(findAll)) {
			return ResponseEntity.badRequest().body(null);
		}
		List<ReviewInfoRequest> listData = findAll.stream().map(x-> modelMapper.map(x, ReviewInfoRequest.class)).toList();
		return ResponseEntity.ok().body(listData);
	}

	@Override
	public ResponseEntity<List<ReviewInfoRequest>> getReviewsByReviewUrl(String reviewUrl) {
		List<ReviewInfoDetail> findAll = reviewsInfoRepository.findByReviewUrl(reviewUrl);
		if(ObjectUtils.isEmpty(findAll)) {
			return ResponseEntity.badRequest().body(null);
		}
		List<ReviewInfoRequest> listData = findAll.stream().map(x-> modelMapper.map(x, ReviewInfoRequest.class)).toList();
		return ResponseEntity.ok().body(listData);
	}

}
