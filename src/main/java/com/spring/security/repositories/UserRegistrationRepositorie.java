package com.spring.security.repositories;

import com.spring.security.entity.UserRegistration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRegistrationRepositorie extends JpaRepository<UserRegistration,Integer> {
    UserRegistration findByUserName(String username);
}
