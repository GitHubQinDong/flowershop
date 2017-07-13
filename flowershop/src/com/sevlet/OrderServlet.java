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
		//1.从session中获取用户信息
		  HttpSession session=request.getSession();
		  UserInfo user=(UserInfo)session.getAttribute("user");
		  if(user!=null){
			  //2。通过用户id查询订单
			 OrderDao dao=new  OrderDao();
			 List<OrderInfo> orderlist= dao.selectOrdersByUserId(user.getUserid());
			 //3.根据结果转发到不同页面
			 if(orderlist!=null){
			 request.setAttribute("orderList", orderlist);
			 request.getRequestDispatcher("customer/historyOrder.jsp").forward(request, response);
			 }
		  }
	}
	public void confirmOrder(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//1.获取购物车和用户信息
		  HttpSession session=request.getSession();
		  ShoppingCartDao cart= (ShoppingCartDao)session.getAttribute("cart");
		  UserInfo user=(UserInfo)session.getAttribute("user");
		 if(cart!=null&&user!=null){
			    //2.获取前台提交的订单flowerids
				String flowerids=request.getParameter("flowerids");
				String[] flowerids_=flowerids.split(",");//去掉拼接的，号，并且分割为字符串数组
				String totalprice=request.getParameter("totalprice");
				 float totalprice_=0f;
				 totalprice_=Float.parseFloat(totalprice);
				//3.创建order对象
			 OrderInfo order=new OrderInfo();
			 order.setTotalprice(totalprice_);
			 order.setOrderdate(new Timestamp(new Date().getTime()));
			 order.setUserid(user.getUserid());
			 List<OrderItemInfo> orderitems=new ArrayList<OrderItemInfo>();
			 //通过key获得购物车中每一下订单项
			 for (int i = 0; i < flowerids_.length; i++) {
				 int id=Integer.parseInt(flowerids_[i]);
				 OrderItemInfo orderitem= cart.getItems().get(id);
				 cart.getItems().remove(id);//移除购物车内提交的订单项。
				 orderitems.add(orderitem);// List<OrderItemInfo>赋值
			 }
			 order.setOrderitems(orderitems);	
			 //4.保存订单
				OrderDao dao=new OrderDao();
				int orderid=dao.addOrder(order);
				if(orderid!=0){
					order.setOrderid(orderid);
					request.setAttribute("order", order);
					session.setAttribute("totalprice", cart.getTotalPrice());//session重新赋值
					 request.setAttribute("msg", "订单成功！");
					request.getRequestDispatcher("customer/orderSuccess.jsp").forward(request, response);
				}
		 }
	}
}
