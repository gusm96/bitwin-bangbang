package com.bitwin.bangbang.store.domain;

public class StoreSearchPassword {
	private String email;
	private String storeId;
	private String bpw;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getStoreId() {
		return storeId;
	}

	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}

	public String getBpw() {
		return bpw;
	}

	public void setBpw(String bpw) {
		this.bpw = bpw;
	}

	@Override
	public String toString() {
		return "StoreSearchPassword [email=" + email + ", storeId=" + storeId + ", bpw=" + bpw + "]";
	}

}
