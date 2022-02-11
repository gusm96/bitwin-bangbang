package com.bitwin.bangbang.domain;

import org.springframework.web.multipart.MultipartFile;

public class GeneralRegRequest {
	private String userid;
	private String pw;
	private String username;
	private String birth;
	private String phonenum;
	private String email;
	private boolean enotify;
	private boolean mnotify;
	private boolean snotify;
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getBirth() {
		return birth;
	}
	public void setBirth(String birth) {
		this.birth = birth;
	}
	public String getPhonenum() {
		return phonenum;
	}
	public void setPhonenum(String phonenum) {
		this.phonenum = phonenum;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public boolean isEnotify() {
		return enotify;
	}
	public void setEnotify(boolean enotify) {
		this.enotify = enotify;
	}
	public boolean isMnotify() {
		return mnotify;
	}
	public void setMnotify(boolean mnotify) {
		this.mnotify = mnotify;
	}
	public boolean isSnotify() {
		return snotify;
	}
	public void setSnotify(boolean snotify) {
		this.snotify = snotify;
	}
	@Override
	public String toString() {
		return "MemberRegRequest [userid=" + userid + ", pw=" + pw + ", username=" + username + ", birth=" + birth
				+ ", phonenum=" + phonenum + ", email=" + email + ", enotify=" + enotify + ", mnotify=" + mnotify
				+ ", snotify=" + snotify + "]";
	}

	

}
