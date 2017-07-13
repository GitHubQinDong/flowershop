package com.beans;

import java.sql.Timestamp;
import java.util.List;

public class OrderInfo {
	private int orderid;
	private Timestamp  orderdate;//Ê±¼ä´Á
	private int userid;
	private float totalprice;
	private List<OrderItemInfo> orderitems;
	public int getOrderid() {
		return orderid;
	}
	public void setOrderid(int orderid) {
		this.orderid = orderid;
	}
	public Timestamp getOrderdate() {
		return orderdate;
	}
	public void setOrderdate(Timestamp orderdate) {
		this.orderdate = orderdate;
	}
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	
	public float getTotalprice() {
		return totalprice;
	}
	public void setTotalprice(float totalprice) {
		this.totalprice = totalprice;
	}
	public List<OrderItemInfo> getOrderitems() {
		return orderitems;
	}
	public void setOrderitems(List<OrderItemInfo> orderitems) {
		this.orderitems = orderitems;
	}
	public OrderInfo(int orderid, Timestamp orderdate, int userid) {
		super();
		this.orderid = orderid;
		this.orderdate = orderdate;
		this.userid = userid;
	}
	public OrderInfo() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "OrderInfo [orderid=" + orderid + ", orderdate=" + orderdate
				+ ", userid=" + userid + "]";
	}
	
}
