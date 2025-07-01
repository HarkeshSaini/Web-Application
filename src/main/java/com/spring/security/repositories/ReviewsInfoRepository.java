package com.spring.security.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.security.entity.ReviewInfoDetail;

@Repository
public interface ReviewsInfoRepository extends JpaRepository<ReviewInfoDetail, Integer> {

	List<ReviewInfoDetail> findByReviewUrl(String reviewUrl);

	List<ReviewInfoDetail> findFirst6ByReviewStatusOrderByPostTimeAsc(String string);

}
