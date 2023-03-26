package com.bitwin.bangbang.serviceManagement.domain;

import java.util.Date;

import lombok.Data;

@Data
public class faqVO {
	
	private int fqidx;
	private String title;
	private String content;
	private Date regdate;
	private Date updatedate;
	
}
