package com.bitwin.bangbang.member.domain;

public class LoginInfo {
	
	private int uidx;
	private String userId;
	private String username;
	private String photo;
	
	public LoginInfo(int uidx, String userId, String username, String photo) {
		this.uidx = uidx;
		this.userId = userId;
		this.username = username;
		this.photo = photo;
	}

	public int getUidx() {
		return uidx;
	}

	public String getUserId() {
		return userId;
	}

	public String getUsername() {
		return username;
	}

	public String getPhoto() {
		return photo;
	}

	@Override
	public String toString() {
		return "LoginInfo [uidx=" + uidx + ", userId=" + userId + ", username=" + username + ", photo=" + photo + "]";
	}
	
	
	
	

}
