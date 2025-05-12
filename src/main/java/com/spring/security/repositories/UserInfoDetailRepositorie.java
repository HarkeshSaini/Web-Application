package com.spring.security.repositories;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.spring.security.entity.AdminUserInfoDetail;

@Repository
public interface UserInfoDetailRepositorie extends MongoRepository<AdminUserInfoDetail, String> {

	Optional<AdminUserInfoDetail> findByEmail(String emailID);
}
