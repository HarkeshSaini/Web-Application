package com.spring.security.request;


import java.sql.Timestamp;

import lombok.Data;

@Data
public class SubscribeInfoRequest {
	
	private String id;
	private Timestamp postTime;
	private Timestamp updateTime;
	private String Status;
	private String email;

}
