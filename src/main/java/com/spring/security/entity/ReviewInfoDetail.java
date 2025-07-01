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
public class ReviewInfoDetail {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String userName;
	private String userEmail;
	private String userPhone;
	private String imgUrl;
	private String reviewMessage;
	private long reviewDate;
	private int reviewRating;
	private String reviewRpyId;
	private String reviewStatus;
	private String reviewUrl;
	public long postTime;
	public long updateTime;
	private String lang_code;
}
