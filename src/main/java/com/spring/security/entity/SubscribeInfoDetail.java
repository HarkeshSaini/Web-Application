package com.spring.security.entity;

import jakarta.persistence.Id;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
@Entity
public class SubscribeInfoDetail {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	public long postTime;
	public long updateTime;
	private String status;
	private String email;

}
