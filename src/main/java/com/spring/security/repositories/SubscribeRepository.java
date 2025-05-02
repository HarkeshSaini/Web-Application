package com.spring.security.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.spring.security.entity.SubscribeInfoDetail;

@Repository
public interface SubscribeRepository extends MongoRepository<SubscribeInfoDetail, String> {

}
