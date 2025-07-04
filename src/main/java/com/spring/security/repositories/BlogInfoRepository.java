package com.spring.security.repositories;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.security.entity.BlogInfoDetail;

@Repository
public interface BlogInfoRepository extends JpaRepository<BlogInfoDetail, Integer> {

	List<BlogInfoDetail> totalPage = null;

	BlogInfoDetail findByTitleUrl(String titleUrl);

	List<BlogInfoDetail> findByStatusAndTitleUrl(String string, String pageUrl);
	
	List<BlogInfoDetail> findTopByStatusOrderByPostTimeDesc(String string, PageRequest of);

	List<BlogInfoDetail> findByStatusOrderByPostTimeDesc(String string);

}
