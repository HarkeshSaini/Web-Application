package com.spring.security.entity;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "ContactInfoDetail")
public class ContactInfoDetail {

	private String id;
	private String name;
	private String email;
	private String subject;
	private String message;
	private String phone;
	private String status;
	public long postTime;
	public long updateTime;
	private String lang_code;
}
