package com.spring.security.request;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CategoryReq {

	private int id;
	private String name;
	private String url;
	public String imgUrl;
	public long postTime;
	public long updateTime;
	public String status;

}
