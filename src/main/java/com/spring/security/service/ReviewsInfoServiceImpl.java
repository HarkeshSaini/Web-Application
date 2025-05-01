package com.spring.security.service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.spring.security.entity.ReviewInfoDetail;
import com.spring.security.interfaces.ReviewsInfoService;
import com.spring.security.repositories.ReviewsInfoRepository;
import com.spring.security.request.ReviewInfoRequest;

import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class ReviewsInfoServiceImpl implements ReviewsInfoService {

	private final ModelMapper modelMapper;
	private final ReviewsInfoRepository reviewsInfoRepository;

	public ReviewsInfoServiceImpl(ReviewsInfoRepository reviewsInfoRepository, ModelMapper modelMapper) {
		this.modelMapper = modelMapper;
		this.reviewsInfoRepository = reviewsInfoRepository;
	}

	@Override
	public ResponseEntity<List<ReviewInfoRequest>> getAllReviews(HttpServletRequest request) {
		try {
			List<ReviewInfoDetail> findAll = reviewsInfoRepository.findAll();
			if (ObjectUtils.isEmpty(findAll)) {
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null); // No content found
			}
			List<ReviewInfoRequest> listData = findAll.stream().map(x -> modelMapper.map(x, ReviewInfoRequest.class))
					.toList();
			return ResponseEntity.ok().body(listData);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}

	@Override
	public ResponseEntity<List<ReviewInfoRequest>> getReviewsByReviewUrl(String reviewUrl) {
		try {
			List<ReviewInfoDetail> findAll = reviewsInfoRepository.findByReviewUrl(reviewUrl);
			if (ObjectUtils.isEmpty(findAll)) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); // Not found
			}
			List<ReviewInfoRequest> listData = findAll.stream().map(x -> modelMapper.map(x, ReviewInfoRequest.class))
					.toList();
			return ResponseEntity.ok().body(listData);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}
}
