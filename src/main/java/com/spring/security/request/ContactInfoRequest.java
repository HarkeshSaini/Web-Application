package com.spring.security.request;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class ContactInfoRequest {

	private int id;
	private String name;
	private String email;
	private String subject;
	private String message;
	private String phone;
	public Timestamp postTime;
	public Timestamp updateTime;
	private String lang_code;
}