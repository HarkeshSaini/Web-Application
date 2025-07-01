package com.spring.security.request;


import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
public class SubscribeInfoRequest {
	
	private int id;
	private Timestamp postTime;
	private Timestamp updateTime;
	private String status;
	private String email;

}
