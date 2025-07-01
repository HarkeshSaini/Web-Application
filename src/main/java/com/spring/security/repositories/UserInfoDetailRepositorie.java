package com.spring.security.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.security.entity.AdminUserInfoDetail;

@Repository
public interface UserInfoDetailRepositorie extends JpaRepository<AdminUserInfoDetail, Integer> {

	Optional<AdminUserInfoDetail> findByEmail(String emailID);
}
