package com.spring.security.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.security.entity.SubscribeInfoDetail;

@Repository
public interface SubscribeRepository extends JpaRepository<SubscribeInfoDetail, Integer> {

}
