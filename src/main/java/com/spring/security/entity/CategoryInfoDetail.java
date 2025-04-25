package com.spring.security.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "CategoryInfoDetail")
public class CategoryInfoDetail {

	@Id
	private String id;
	private String categoryName;
	private String categoryUrl;
	private String metaTitle;
	private String metaKeywords;
	private String metaDescription;
	private String contant;
	private String status;
	private String imgUrl;
	private String createdBy;
	private String updatedBy;
	private long createdAt;
	private long updatedAt;
	private String categoryType;
	private boolean isFeatured;

}
