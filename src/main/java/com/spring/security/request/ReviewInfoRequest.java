package com.spring.security.request;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
public class ReviewInfoRequest {

	private int id;
	private String userName;
	private String userEmail;
	private String userPhone;
	private String imgUrl;
	private String reviewMessage;
	private Timestamp reviewDate;
	private int reviewRating;
	private String reviewRpyId;
	private String reviewStatus;
	private String reviewUrl;
	public Timestamp postTime;
	public Timestamp updateTime;
	private String lang_code;
}
