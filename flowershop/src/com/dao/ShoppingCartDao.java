package com.dao;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.beans.FlowerInfo;
import com.beans.OrderItemInfo;

public class ShoppingCartDao {
	private Map<Integer, OrderItemInfo> items;//订单项信息
	public ShoppingCartDao() {
		if(items==null){
			items=new HashMap<Integer,OrderItemInfo>();//Integer存放鲜花id,OrderItemInfo订单项
		}
	}
	
//	给购物车添加鲜花 （当购物车里已经有同一款鲜花，如果存在了就累计数量，若不存在就添加该鲜花）
	public boolean addToCart(int flowerid, OrderItemInfo orderitem) {
		boolean flag=false;
		if(items.containsKey(flowerid)){
			OrderItemInfo oldOrderItem=(OrderItemInfo)items.get(flowerid);//旧的订单项
			int newQuantity=oldOrderItem.getQuantity()+orderitem.getQuantity();
			orderitem.setQuantity(newQuantity);//相同鲜花的数量-重新赋值
			items.put(flowerid, orderitem);
			flag=true;
		}else{
			items.put(flowerid, orderitem);
			flag=true;
		}
		return flag;
		
	}
	
//	修改购物车鲜花数量
	public void updateFlower(int flowerid ,int quantity){
		OrderItemInfo orderitem=(OrderItemInfo)items.get(flowerid);
		orderitem.setQuantity(quantity);
		items.put(flowerid, orderitem);
	}
//	结算购物车订单总价
	public int getTotalPrice() {
		int totalPrice=0;//购物车里订单项总金额
		for(Iterator its=items.values().iterator();its.hasNext();){
			 //items.values().iterator()意思是取所有的订单
			OrderItemInfo orderitem=(OrderItemInfo)its.next();
			FlowerInfo flower=orderitem.getFlower();
			int quantity=orderitem.getQuantity();
			totalPrice+=flower.getPrice()*quantity;
			}
		return totalPrice;
	}

//删除购物车信息
	public void delOrderItem(int flowerid_) {
		items.remove(flowerid_);
		
	}
//	结算购物车选定订单总价
	public int getSomePrice(int i) {
		int somePrice=0;//购物车里选定订单项总金额
			if(items.containsKey(i)){
				OrderItemInfo orderitem=(OrderItemInfo)items.get(i);
				FlowerInfo flower=orderitem.getFlower();
				int quantity=orderitem.getQuantity();
				somePrice+=flower.getPrice()*quantity;
		}	
		return somePrice;
	}	
	
	public Map<Integer, OrderItemInfo> getItems() {
		return items;
	}

	public void setItems(Map<Integer, OrderItemInfo> items) {
		this.items = items;
	}


}
