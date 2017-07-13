package com.beans;

public class FlowerInfo {
	private int flowerid;
	private String flowername;
	private int price;
	private String picture;
	private int catalogid;
	public int getFlowerid() {
		return flowerid;
	}
	public void setFlowerid(int flowerid) {
		this.flowerid = flowerid;
	}
	public String getFlowername() {
		return flowername;
	}
	public void setFlowername(String flowername) {
		this.flowername = flowername;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	public int getCatalogid() {
		return catalogid;
	}
	public void setCatalogid(int catalogid) {
		this.catalogid = catalogid;
	}
	public FlowerInfo(int flowerid, String flowername, int price,
			String picture, int catalogid) {
		super();
		this.flowerid = flowerid;
		this.flowername = flowername;
		this.price = price;
		this.picture = picture;
		this.catalogid = catalogid;
	}
	public FlowerInfo() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "FlowerInfo [flowerid=" + flowerid + ", flowername="
				+ flowername + ", price=" + price + ", picture=" + picture
				+ ", catalogid=" + catalogid + "]";
	}
	

}
