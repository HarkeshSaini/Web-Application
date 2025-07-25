package com.spring.security.request;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
public class AdminUserInfoRequest {

	private int id;
	private String name;
	private String email;
	private String password;
	private String phone;
	private Timestamp dateOfBirth;
	private Timestamp createDate;
	private Timestamp updateDate;
	private String role;
	private int age;
	private String comment;
	private String status;
	private boolean emailVerified;
	private String imgUrl;
	
	
}
