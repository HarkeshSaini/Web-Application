package com.spring.security.request;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class ReviewInfoRequest {

	private String id;
	private String userName;
	private String userEmail;
	private String userPhone;
	private String reviewMessage;
	private String reviewDate;
	private String reviewRating;
	private String reviewRpyId;
	private String reviewStatus;
	private String reviewUrl;
	public Timestamp postTime;
	public Timestamp updateTime;
	private String lang_code;
}
