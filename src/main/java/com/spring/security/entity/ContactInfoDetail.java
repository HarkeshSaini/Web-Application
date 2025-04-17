package com.spring.security.entity;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "ContactInfoDetail")
public class ContactInfoDetail {

	private int id;
	private String name;
	private String email;
	private String subject;
	private String message;
	private String phone;
	public long postTime;
	public long updateTime;
	private String lang_code;
}
