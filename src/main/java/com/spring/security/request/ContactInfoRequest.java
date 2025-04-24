package com.spring.security.request;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ContactInfoRequest {

	private String id;
	private String name;
	private String email;
	private String subject;
	private String message;
	private String status;
	private String phone;
	public Timestamp postTime;
	public Timestamp updateTime;
	private String lang_code;
}