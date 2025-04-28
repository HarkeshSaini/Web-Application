package com.spring.security.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "WebSiteUserDetail")
public class WebSiteUserDetail {

	@Id
	private String id;
	private String fullName;
	private String email;
	private String password;
	private String status;
	private String role;
	private String phoneNumber;
	private String username;
	private String dateOfBirth;
	private String gender;
	private String imgUrl;
	private String address;
	private long createdAt;
	private long updatedAt;
}
