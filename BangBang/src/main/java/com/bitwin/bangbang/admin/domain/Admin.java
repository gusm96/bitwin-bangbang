package com.bitwin.bangbang.admin.domain;

public class Admin {
	private int aidx;
	private String aid;
	private String apw;
	
	public Admin() {}

	public Admin(int aidx, String aid, String apw) {
		this.aidx = aidx;
		this.aid = aid;
		this.apw = apw;
	}

	public int getAidx() {
		return aidx;
	}
	public String getAid() {
		return aid;
	}
	public String getApw() {
		return apw;
	}
	@Override
	public String toString() {
		return "Admin [aidx=" + aidx + ", aid=" + aid + ", apw=" + apw + "]";
	}
	
	
}
