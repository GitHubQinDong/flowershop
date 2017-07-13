package com.dao;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.beans.FlowerInfo;
import com.beans.OrderItemInfo;

public class ShoppingCartDao {
	private Map<Integer, OrderItemInfo> items;//��������Ϣ
	public ShoppingCartDao() {
		if(items==null){
			items=new HashMap<Integer,OrderItemInfo>();//Integer����ʻ�id,OrderItemInfo������
		}
	}
	
//	�����ﳵ����ʻ� �������ﳵ���Ѿ���ͬһ���ʻ�����������˾��ۼ��������������ھ���Ӹ��ʻ���
	public boolean addToCart(int flowerid, OrderItemInfo orderitem) {
		boolean flag=false;
		if(items.containsKey(flowerid)){
			OrderItemInfo oldOrderItem=(OrderItemInfo)items.get(flowerid);//�ɵĶ�����
			int newQuantity=oldOrderItem.getQuantity()+orderitem.getQuantity();
			orderitem.setQuantity(newQuantity);//��ͬ�ʻ�������-���¸�ֵ
			items.put(flowerid, orderitem);
			flag=true;
		}else{
			items.put(flowerid, orderitem);
			flag=true;
		}
		return flag;
		
	}
	
//	�޸Ĺ��ﳵ�ʻ�����
	public void updateFlower(int flowerid ,int quantity){
		OrderItemInfo orderitem=(OrderItemInfo)items.get(flowerid);
		orderitem.setQuantity(quantity);
		items.put(flowerid, orderitem);
	}
//	���㹺�ﳵ�����ܼ�
	public int getTotalPrice() {
		int totalPrice=0;//���ﳵ�ﶩ�����ܽ��
		for(Iterator its=items.values().iterator();its.hasNext();){
			 //items.values().iterator()��˼��ȡ���еĶ���
			OrderItemInfo orderitem=(OrderItemInfo)its.next();
			FlowerInfo flower=orderitem.getFlower();
			int quantity=orderitem.getQuantity();
			totalPrice+=flower.getPrice()*quantity;
			}
		return totalPrice;
	}

//ɾ�����ﳵ��Ϣ
	public void delOrderItem(int flowerid_) {
		items.remove(flowerid_);
		
	}
//	���㹺�ﳵѡ�������ܼ�
	public int getSomePrice(int i) {
		int somePrice=0;//���ﳵ��ѡ���������ܽ��
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
