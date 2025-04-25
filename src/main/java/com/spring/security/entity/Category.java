package com.spring.security.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "Category")
public class Category {

	@Id
	private String id;
	private String name;
	private String url;
	public String imgUrl;
	public long postTime;
	public long updateTime;
	public String status;

}
