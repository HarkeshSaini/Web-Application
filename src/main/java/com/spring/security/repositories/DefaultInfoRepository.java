package com.spring.security.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.spring.security.entity.DefaultInfoDetail;

@Repository
public interface DefaultInfoRepository extends MongoRepository<DefaultInfoDetail, String>{

	DefaultInfoDetail findByCategory(String category);

	List<DefaultInfoDetail> findByStatus(String string);

	List<DefaultInfoDetail> findByStatusAndCategory(String string, String category);

}
