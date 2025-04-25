package com.spring.security.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.spring.security.entity.CategoryInfoDetail;

@Repository
public interface CategoryInfoRepository extends MongoRepository<CategoryInfoDetail, String> {

	CategoryInfoDetail findBycategoryUrl(String categoryUrl);

	List<CategoryInfoDetail> findByStatus(String string);

	List<CategoryInfoDetail> findByStatusAndCategoryUrl(String string, String categoryUrl);

}
