package com.spring.security.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.spring.security.entity.WebSiteUserDetail;

@Repository
public interface WebSiteUserRepository extends MongoRepository<WebSiteUserDetail, String> {

	WebSiteUserDetail findByEmail(String email);

	WebSiteUserDetail findByEmailAndDateOfBirthAndUsername(String email, String dateOfBirth, String username);

	WebSiteUserDetail findByUsername(String emailId);

}
