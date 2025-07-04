package com.spring.security.entity;

import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
@Entity
public class CategoryInfoDetail {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String heading;
	private String pageUrl;
	private String categoryUrl;
	private String metaTitle;
	private String metaKeywords;
	private String metaDescription;
	@Column(columnDefinition = "LONGTEXT")
	private String content;
	private String status;
	private String imgUrl;
	private String createdBy;
	private String updatedBy;
	private long createdAt;
	private long updatedAt;
	private String categoryType;
	private boolean isFeatured;

}
