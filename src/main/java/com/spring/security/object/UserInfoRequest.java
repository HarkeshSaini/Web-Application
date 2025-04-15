package com.spring.security.object;

import lombok.Data;

@Data
public class UserInfoRequest {

	private int id;
	private String name;
	private String email;
	private String password;
	private String phone;
	private long dateOfBirth;
	private long createDate;
	private long updateDate;
	private String role;
	private int age;
	private int totalUser;
	private String address;
	private int numOfRoom;
	private String comment;
	private String status;
	private boolean emailVerified;
	private String another_phone;
	private String imgUrl;
	private long aadhar_number;
	private String gender;
	private String country;
	private String state;
	private String zipCode;

}
