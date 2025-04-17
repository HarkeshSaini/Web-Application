package com.spring.security.entity;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "ReviewInfoDetail")
public class ReviewInfoDetail {

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
	public long postTime;
	public long updateTime;
	private String lang_code;
}
