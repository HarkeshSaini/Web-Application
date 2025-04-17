package com.spring.security.entity;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "BlogInfoDetail")
public class BlogInfoDetail {

	public String id;
	public String title;
	public String titleUrl;
	public String content;
	public String description;
	public String keywords;
	public long postTime;
	public long updateTime;
	public String status;
	public String heading;
	public String imgUrl;
	public String category;
	public String extarTag;
	public String tfnHeader;
	public String tfnFooter;
	public String tfnPopup;
	public String lang_code;
}
