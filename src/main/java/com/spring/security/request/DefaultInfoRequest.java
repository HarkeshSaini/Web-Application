package com.spring.security.request;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class DefaultInfoRequest {

	public String id;
	public String content;
	public String description;
	public String keywords;
	public Timestamp postTime;
	public Timestamp updateTime;
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
