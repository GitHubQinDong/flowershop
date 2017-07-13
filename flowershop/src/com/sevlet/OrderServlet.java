package com.sevlet;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.beans.OrderInfo;
import com.beans.OrderItemInfo;
import com.beans.UserInfo;
import com.dao.OrderDao;
import com.dao.ShoppingCartDao;

public class OrderServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		 doPost(request, response);
		
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
       String flag= request.getParameter("flag");
       if("confirmOrder".equals(flag)){
    	   confirmOrder(request,response);
       }else if("selectOrders".equals(flag)){
    	   selectOrders(request,response);
       }
	}
	private void selectOrders(HttpServletRequest request,HttpServletResponse response) 
			throws ServletException, IOException{
		//1.��session�л�ȡ�û���Ϣ
		  HttpSession session=request.getSession();
		  UserInfo user=(UserInfo)session.getAttribute("user");
		  if(user!=null){
			  //2��ͨ���û�id��ѯ����
			 OrderDao dao=new  OrderDao();
			 List<OrderInfo> orderlist= dao.selectOrdersByUserId(user.getUserid());
			 //3.���ݽ��ת������ͬҳ��
			 if(orderlist!=null){
			 request.setAttribute("orderList", orderlist);
			 request.getRequestDispatcher("customer/historyOrder.jsp").forward(request, response);
			 }
		  }
	}
	public void confirmOrder(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//1.��ȡ���ﳵ���û���Ϣ
		  HttpSession session=request.getSession();
		  ShoppingCartDao cart= (ShoppingCartDao)session.getAttribute("cart");
		  UserInfo user=(UserInfo)session.getAttribute("user");
		 if(cart!=null&&user!=null){
			    //2.��ȡǰ̨�ύ�Ķ���flowerids
				String flowerids=request.getParameter("flowerids");
				String[] flowerids_=flowerids.split(",");//ȥ��ƴ�ӵģ��ţ����ҷָ�Ϊ�ַ�������
				String totalprice=request.getParameter("totalprice");
				 float totalprice_=0f;
				 totalprice_=Float.parseFloat(totalprice);
				//3.����order����
			 OrderInfo order=new OrderInfo();
			 order.setTotalprice(totalprice_);
			 order.setOrderdate(new Timestamp(new Date().getTime()));
			 order.setUserid(user.getUserid());
			 List<OrderItemInfo> orderitems=new ArrayList<OrderItemInfo>();
			 //ͨ��key��ù��ﳵ��ÿһ�¶�����
			 for (int i = 0; i < flowerids_.length; i++) {
				 int id=Integer.parseInt(flowerids_[i]);
				 OrderItemInfo orderitem= cart.getItems().get(id);
				 cart.getItems().remove(id);//�Ƴ����ﳵ���ύ�Ķ����
				 orderitems.add(orderitem);// List<OrderItemInfo>��ֵ
			 }
			 order.setOrderitems(orderitems);	
			 //4.���涩��
				OrderDao dao=new OrderDao();
				int orderid=dao.addOrder(order);
				if(orderid!=0){
					order.setOrderid(orderid);
					request.setAttribute("order", order);
					session.setAttribute("totalprice", cart.getTotalPrice());//session���¸�ֵ
					 request.setAttribute("msg", "�����ɹ���");
					request.getRequestDispatcher("customer/orderSuccess.jsp").forward(request, response);
				}
		 }
	}
}
