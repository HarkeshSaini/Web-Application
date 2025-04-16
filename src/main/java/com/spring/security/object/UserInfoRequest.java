package com.spring.security.object;

import lombok.Data;

@Data
public class UserInfoRequest {

	private String id;
	private String name;
	private String email;
	private String password;
	private String phone;
	private long dateOfBirth;
	private long createDate;
	private long updateDate;
	private String role;
	private int age;
	private String comment;
	private String status;
	private boolean emailVerified;
	private String imgUrl;
}
