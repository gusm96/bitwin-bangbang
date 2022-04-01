package com.bitwin.bangbang.store.domain;

public class StoreEditRequest {
	private int sridx;
	private int sidx;
	private String sname;
	private String sphone;
	private String address;
	private String oemail;
	private Store curInfo;

	public int getSridx() {
		return sridx;
	}

	public void setSridx(int sridx) {
		this.sridx = sridx;
	}

	public int getSidx() {
		return sidx;
	}

	public void setSidx(int sidx) {
		this.sidx = sidx;
	}

	public String getSname() {
		return sname;
	}

	public void setSname(String sname) {
		this.sname = sname;
	}

	public String getSphone() {
		return sphone;
	}

	public void setSphone(String sphone) {
		this.sphone = sphone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	

	public String getOemail() {
		return oemail;
	}

	public void setOemail(String oemail) {
		this.oemail = oemail;
	}

	public Store getCurInfo() {
		return curInfo;
	}

	public void setCurInfo(Store curInfo) {
		this.curInfo = curInfo;
	}

	@Override
	public String toString() {
		return "StoreEditRequest [sridx=" + sridx + ", sidx=" + sidx + ", sname=" + sname + ", sphone=" + sphone
				+ ", address=" + address + ", oemail=" + oemail + ", curInfo=" + curInfo + "]";
	}

	

}
