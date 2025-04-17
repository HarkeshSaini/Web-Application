package com.spring.security.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.spring.security.entity.ReviewInfoDetail;

@Repository
public interface ReviewsInfoRepository extends MongoRepository<ReviewInfoDetail, String> {

}
