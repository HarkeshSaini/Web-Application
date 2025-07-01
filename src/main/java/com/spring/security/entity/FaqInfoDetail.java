package com.spring.security.entity;

import jakarta.persistence.Id;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
@Entity
public class FaqInfoDetail {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String pageType;
	private String faQues;
	private String faqAns;
	private String url;
	private String faqStatus;
	private String modifyBy;
	public long postTime;
	public long updateTime;
	private String lang_code;
}
