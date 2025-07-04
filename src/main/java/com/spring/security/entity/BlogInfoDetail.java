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
public class BlogInfoDetail {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int id;
	public String title;
	public String titleUrl;
	@Column(columnDefinition = "LONGTEXT")
	public String content;
	public String description;
	public String keywords;
	public long postTime;
	public long updateTime;
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
