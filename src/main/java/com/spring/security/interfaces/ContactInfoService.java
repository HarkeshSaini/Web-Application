package com.spring.security.interfaces;

import java.util.List;

import org.jetbrains.annotations.NotNull;
import org.springframework.http.ResponseEntity;

import com.spring.security.request.ContactInfoRequest;
import com.spring.security.request.SubscribeInfoRequest;

public interface ContactInfoService {

	List<ContactInfoRequest> showAllContactInfo();

	ResponseEntity<Void> deleteById(@NotNull int id);

	ResponseEntity<String> submitContact(ContactInfoRequest request);

	ResponseEntity<ContactInfoRequest> findByIdContactUs(int id);

	ResponseEntity<Boolean> updateStatusOfContactUsByStatus(int id, String value);

	ResponseEntity<String> subscribe(@NotNull SubscribeInfoRequest request);

	ResponseEntity<Boolean> updateStatusOfSubscribeInfoByStatus(int id, String value);

	List<SubscribeInfoRequest> showAllSubscribeInfo();

	ResponseEntity<Object> deleteSubscribeInfoById(int id);

}
