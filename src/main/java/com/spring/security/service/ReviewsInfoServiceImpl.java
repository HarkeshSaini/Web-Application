package com.spring.security.service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.domain.*;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.spring.security.entity.ReviewInfoDetail;
import com.spring.security.interfaces.ReviewsInfoService;
import com.spring.security.repositories.ReviewsInfoRepository;
import com.spring.security.request.ReviewInfoRequest;
import com.spring.security.utility.CommonUtility;

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
			List<ReviewInfoDetail> findAll = reviewsInfoRepository.findFirst6ByReviewStatusOrderByPostTimeAsc("Active");
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
			List<ReviewInfoRequest> listData = findAll.stream().map(x -> modelMapper.map(x, ReviewInfoRequest.class)).toList();
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
	public ResponseEntity<Object> addNewReviews(ReviewInfoRequest infoRequest,MultipartFile file) {
		try {
			infoRequest.setImgUrl(CommonUtility.uploadFile(file));
			infoRequest.setUserPhone(String.valueOf(System.currentTimeMillis()));
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
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal server error.");
		}
	}

	@Override
	public void deleteReviewInfo(@NotNull int id) {
		reviewsInfoRepository.deleteById(id);
	}

	@Override
	public ResponseEntity<Boolean> updateReviewsByStatus(int id, String value) {
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
	public ResponseEntity<ReviewInfoRequest> findByIdReviews(int id) {
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

	@Override
	public ResponseEntity<List<ReviewInfoRequest>> getReviewsWithPaginate(String strValue, HttpServletRequest request) {
	    Pageable pageable = null;
	    int page = 0;
	    int size = 6;  
	    try {
	        if (request.getSession().getAttribute("strValue") == null) {
	            request.getSession().setAttribute("page", 0);  
	            request.getSession().setAttribute("size", size);  
	            request.getSession().setAttribute("strValue", strValue);  
	        }
	        page = (int) request.getSession().getAttribute("page");
	        if (strValue.equals("right")) {
	            page += 1;  
	        } else if (strValue.equals("left")) {
	            if (page > 0) {
	                page -= 1;
	            }
	        }
	        if (page < 0) {
	            page = 0;  
	        }
	        request.getSession().setAttribute("page", page);
	        request.getSession().setAttribute("size", size);
	        
	        pageable = PageRequest.of(page, size, Sort.by("postTime").ascending());
	        Page<ReviewInfoDetail> findAll = reviewsInfoRepository.findAll(pageable);
	        
	        if (findAll == null || findAll.isEmpty()) {
	            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
	        }
	        
	        List<ReviewInfoRequest> listData = findAll.stream().map(x -> modelMapper.map(x, ReviewInfoRequest.class)).collect(Collectors.toList());
	        List<ReviewInfoRequest> activeListData = listData.stream().filter(x->x.getReviewStatus().equals("Active")).toList();
	        return ResponseEntity.ok().body(activeListData);
	        
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
	    }
	}


}
