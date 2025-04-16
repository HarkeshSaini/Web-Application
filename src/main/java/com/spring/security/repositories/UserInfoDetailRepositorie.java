package com.spring.security.repositories;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.spring.security.entity.UserInfoDetail;

@Repository
public interface UserInfoDetailRepositorie extends MongoRepository<UserInfoDetail, String> {

	Optional<UserInfoDetail> findByEmail(String emailID);
}
