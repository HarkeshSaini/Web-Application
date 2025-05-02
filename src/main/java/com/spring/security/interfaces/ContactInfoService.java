package com.spring.security.interfaces;

import java.util.List;

import org.jetbrains.annotations.NotNull;
import org.springframework.http.ResponseEntity;

import com.spring.security.request.ContactInfoRequest;

public interface ContactInfoService {

	List<ContactInfoRequest> showAllContactInfo();

	ResponseEntity<Void> deleteById(@NotNull String id);

	ResponseEntity<String> submitContact(ContactInfoRequest request);

	ResponseEntity<ContactInfoRequest> findByIdContactUs(String id);

	ResponseEntity<Boolean> updateStatusOfContactUsByStatus(String id, String value);

}
