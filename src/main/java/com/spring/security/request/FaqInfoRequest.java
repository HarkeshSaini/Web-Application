package com.spring.security.request;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class FaqInfoRequest {
	
	private String id;
	private String pageType;
	private String faQues;
	private String faqAns;
	private String url;
	private String faqStatus;
	private String modifyBy;
	public Timestamp postTime;
	public Timestamp updateTime;
	private String lang_code;
}
