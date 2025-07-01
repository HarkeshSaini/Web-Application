package com.spring.security.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.security.entity.WebSiteUserDetail;

@Repository
public interface WebSiteUserRepository extends JpaRepository<WebSiteUserDetail, Integer> {

	WebSiteUserDetail findByEmail(String email);

	WebSiteUserDetail findByEmailAndDateOfBirthAndUsername(String email, String dateOfBirth, String username);

	WebSiteUserDetail findByUsername(String emailId);

}
