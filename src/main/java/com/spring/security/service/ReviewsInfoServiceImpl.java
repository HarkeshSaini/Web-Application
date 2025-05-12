package com.spring.security.service;

import java.sql.Timestamp;
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
import jakarta.validation.constraints.NotNull;

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
			List<ReviewInfoDetail> findAll = reviewsInfoRepository.findFirst6ByReviewStatusOrderByPostTimeDesc("Active");
			if (ObjectUtils.isEmpty(findAll)) {
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null); // No content found
			}
			List<ReviewInfoRequest> listData = findAll.stream().map(x -> modelMapper.map(x, ReviewInfoRequest.class)).toList();
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

	@Override
	public List<ReviewInfoRequest> reviewsInfoService() {
		List<ReviewInfoDetail> findAll = reviewsInfoRepository.findAll();
		return findAll.stream().map(x-> modelMapper.map(x, ReviewInfoRequest.class)).toList();
	}

	@Override
	public ResponseEntity<Object> addNewReviews(ReviewInfoRequest infoRequest) {
		try {
			infoRequest.setLang_code("en");
			infoRequest.setPostTime(new Timestamp(System.currentTimeMillis()));
			infoRequest.setReviewDate(new Timestamp(System.currentTimeMillis()));
			infoRequest.setReviewStatus("InActive");
			ReviewInfoDetail mapData = modelMapper.map(infoRequest, ReviewInfoDetail.class);
			ReviewInfoDetail save = reviewsInfoRepository.save(mapData);
			if (ObjectUtils.isEmpty(save)) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Something went wrong. Please try again.");
			}
			return ResponseEntity.status(HttpStatus.CREATED).body("Thank you for Reviews us!");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}

	@Override
	public void deleteReviewInfo(@NotNull String id) {
		reviewsInfoRepository.deleteById(id);
	}

	@Override
	public ResponseEntity<Boolean> updateReviewsByStatus(String id, String value) {
		Optional<ReviewInfoDetail> findById = reviewsInfoRepository.findById(id);
		if (findById.isPresent()) {
			ReviewInfoDetail contactInfoDetail = findById.get();
			String status = value.equals("1") ? "Active" : "InActive";
			contactInfoDetail.setReviewStatus(status);
			contactInfoDetail.setUpdateTime(System.currentTimeMillis());
			reviewsInfoRepository.save(contactInfoDetail);
			return ResponseEntity.status(HttpStatus.OK).body(value.equals("1"));
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(false);
		}
	}

	@Override
	public ResponseEntity<ReviewInfoRequest> findByIdReviews(String id) {
		try {
			Optional<ReviewInfoDetail> findAll = reviewsInfoRepository.findById(id);
			if (!findAll.isPresent()) {
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null); // No content found
			}
			ReviewInfoRequest mapData = modelMapper.map(findAll.orElse(null), ReviewInfoRequest.class);
			return ResponseEntity.ok().body(mapData);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}
}
