package com.spring.security.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.security.entity.ContactInfoDetail;

@Repository
public interface ContactInfoRepository extends JpaRepository<ContactInfoDetail, Integer> {

}
