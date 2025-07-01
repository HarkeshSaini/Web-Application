package com.spring.security.request;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
public class FaqInfoRequest {
	
	private int id;
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
