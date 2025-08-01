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
public class ContactInfoDetail {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private String email;
	@Column(columnDefinition = "LONGTEXT")
	private String subject;
	@Column(columnDefinition = "LONGTEXT")
	private String message;
	private String status;
	private String phone;
	public long postTime;
	public long updateTime;
	private String lang_code;
}
