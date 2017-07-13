package com.beans;

public class UserDetailInfo {

	private int userid;
	private int xb;
	private String truename;
	private String csrq;
	private String phone;
	private String email;
	private String address;
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public int getXb() {
		return xb;
	}
	public void setXb(int xb) {
		this.xb = xb;
	}
	public String getTruename() {
		return truename;
	}
	public void setTruename(String truename) {
		this.truename = truename;
	}
	public String getCsrq() {
		return csrq;
	}
	public void setCsrq(String csrq) {
		this.csrq = csrq;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public UserDetailInfo(int userid, int xb, String truename, String csrq,
			String phone, String email, String address) {
		super();
		this.userid = userid;
		this.xb = xb;
		this.truename = truename;
		this.csrq = csrq;
		this.phone = phone;
		this.email = email;
		this.address = address;
	}
	public UserDetailInfo() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "UserDetailInfo [userid=" + userid + ", xb=" + xb
				+ ", truename=" + truename + ", csrq=" + csrq + ", phone="
				+ phone + ", email=" + email + ", address=" + address + "]";
	}
	
	

}
