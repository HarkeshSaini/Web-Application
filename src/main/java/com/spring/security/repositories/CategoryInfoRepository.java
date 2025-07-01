package com.spring.security.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.security.entity.CategoryInfoDetail;

@Repository
public interface CategoryInfoRepository extends JpaRepository<CategoryInfoDetail, Integer> {

	List<CategoryInfoDetail> findByStatus(String string);

	List<CategoryInfoDetail> findByStatusAndCategoryUrl(String string, String categoryUrl);

	List<CategoryInfoDetail> findByCategoryUrlAndPageUrl(String url, String categoryUrl);

	CategoryInfoDetail findByPageUrl(String pageUrl);

}
