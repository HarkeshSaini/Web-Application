package com.spring.security.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.spring.security.entity.ContactInfoDetail;

@Repository
public interface ContactInfoRepository extends MongoRepository<ContactInfoDetail, String> {

}
