package com.spring.security.request;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CategoryInfoRequest {

	private int id;
	private String heading;
	private String pageUrl;
	private String categoryUrl;
	private String metaTitle;
	private String metaKeywords;
	private String metaDescription;
	private String content;
	private String status;
	private String imgUrl;
	private String createdBy;
	private String updatedBy;
	private Timestamp createdAt;
	private Timestamp updatedAt;
	private String categoryType;
	private boolean isFeatured;

}
