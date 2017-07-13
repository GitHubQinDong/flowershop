package com.beans;

public class OrderItemInfo {
	private int orderitemid;
	private int quantity;
	private int  orderid;
	private FlowerInfo flower;
	public int getOrderitemid() {
		return orderitemid;
	}
	public void setOrderitemid(int orderitemid) {
		this.orderitemid = orderitemid;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public int getOrderid() {
		return orderid;
	}
	public void setOrderid(int orderid) {
		this.orderid = orderid;
	}
	
	public OrderItemInfo() {
		super();
		// TODO Auto-generated constructor stub
	}
	public FlowerInfo getFlower() {
		return flower;
	}
	public void setFlower(FlowerInfo flower) {
		this.flower = flower;
	}
	public OrderItemInfo(int orderitemid, int quantity, int orderid,
			FlowerInfo flower) {
		super();
		this.orderitemid = orderitemid;
		this.quantity = quantity;
		this.orderid = orderid;
		this.flower = flower;
	}
	@Override
	public String toString() {
		return "OrderItemInfo [orderitemid=" + orderitemid + ", quantity="
				+ quantity + ", orderid=" + orderid + ", flower=" + flower
				+ "]";
	}
	
}
