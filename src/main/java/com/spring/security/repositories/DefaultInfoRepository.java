package com.spring.security.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.security.entity.DefaultInfoDetail;

@Repository
public interface DefaultInfoRepository extends JpaRepository<DefaultInfoDetail, Integer>{

	DefaultInfoDetail findByCategory(String category);

	List<DefaultInfoDetail> findByStatus(String string);

	List<DefaultInfoDetail> findByStatusAndCategory(String string, String category);

}
