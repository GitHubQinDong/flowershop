package com.beans;

public class CatelogInfo {
	private int catalogid;
	private String catalogname;
	public int getCatalogid() {
		return catalogid;
	}
	public void setCatalogid(int catalogid) {
		this.catalogid = catalogid;
	}
	public String getCatalogname() {
		return catalogname;
	}
	public void setCatalogname(String catalogname) {
		this.catalogname = catalogname;
	}
	public CatelogInfo(int catalogid, String catalogname) {
		super();
		this.catalogid = catalogid;
		this.catalogname = catalogname;
	}
	public CatelogInfo() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "CatelogInfo [catalogid=" + catalogid + ", catalogname="
				+ catalogname + "]";
	}
	
	
	

}
