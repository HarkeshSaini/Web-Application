package com.spring.security.entity;

import java.sql.Timestamp;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "ContactInfoDetail")
public class ContactInfoDetail {

	@Id
	private String id;
	private String name;
	private String email;
	private String subject;
	private String message;
	private String status;
	private String phone;
	public long postTime;
	public long updateTime;
	private String lang_code;
}
