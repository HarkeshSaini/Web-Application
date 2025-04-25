package com.spring.security.request;

import lombok.Data;

@Data
public class CategoryReq {

	private String id;
	private String name;
	private String url;
	public String imgUrl;
	public long postTime;
	public long updateTime;
	public String status;

}
