package com.spring.security.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "SubscribeInfoDetail")
public class SubscribeInfoDetail {
	
	@Id
	private String id;
	public long postTime;
	public long updateTime;
	private String status;
	private String email;

}
