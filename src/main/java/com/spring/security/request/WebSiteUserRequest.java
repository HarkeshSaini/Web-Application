package com.spring.security.request;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class WebSiteUserRequest {

	private String id;
	private String fullName;
	private String email;
	private String password;
	private String phone;
	private String username;
	private String status;
	private String dateOfBirth;
	private String gender;
	private String role;
	private String imgUrl;
	private String address;
	private Timestamp createdAt;
	private Timestamp updatedAt;
}
