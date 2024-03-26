package com.bitwin.bangbang.member.domain;

public class PasswordResetRequestDto {
	private String email;
	private String userid;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}
}
