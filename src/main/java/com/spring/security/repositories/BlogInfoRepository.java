package com.spring.security.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.spring.security.entity.BlogInfoDetail;

@Repository
public interface BlogInfoRepository extends MongoRepository<BlogInfoDetail, String> {

	BlogInfoDetail findByTitleUrl(String titleUrl);

}
