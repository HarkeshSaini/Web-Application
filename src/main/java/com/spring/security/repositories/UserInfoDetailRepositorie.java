package com.spring.security.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.security.entity.UserInfoDetail;

@Repository
public interface UserInfoDetailRepositorie extends JpaRepository<UserInfoDetail, Integer> {

	Optional<UserInfoDetail> findByEmail(String emailID);
}
