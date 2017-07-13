package com.sevlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.beans.FlowerInfo;
import com.beans.OrderItemInfo;
import com.dao.FlowerDao;
import com.dao.ShoppingCartDao;

public class ShoppingServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");//post请求中文乱码解决
	   String flag=	request.getParameter("flag");
		if("addcart".equals(flag)){
			addCart(request,response);
			}
		else if("updateQt".equals(flag)){
			updateQuantity(request,response);
		}else if("delItem".equals(flag)){
			delOrederItem(request,response);
		}else if("calcuMoney".equals(flag)){
			calcuMoney(request,response);
		}
		
	}
	public void addCart(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");//post请求中文乱码解决
		//1.获取前台form表单数据
		String quantity=request.getParameter("quantity");
		String flowerid=request.getParameter("flowerid");
		//类型转换
		int quantity_=0;
		  if(quantity!=null){
			  quantity_=Integer.parseInt(quantity);
		  }
		int flowerid_=0;
		  if(flowerid!=null){
			  flowerid_=Integer.parseInt(flowerid);
		  }
		//2.验证用户是否登录
		  HttpSession session=request.getSession();//获得session对象
		  if(session.getAttribute("user")!=null){
		
				//3.生成订单项对象
				  //1）通过flowerid查询鲜花对象
				  FlowerDao dao=new FlowerDao();
				  FlowerInfo  flower=dao.getFlowerByFlowerId(flowerid_);
				  //2)给订单项赋值
					OrderItemInfo oderitem=new OrderItemInfo();
					oderitem.setFlower(flower);
					oderitem.setQuantity(quantity_);
				//4.添加到购物车 :  首先获取购物车中历史订单项信息或者创建购物车
				ShoppingCartDao cart=(ShoppingCartDao)session.getAttribute("cart");
				if(cart==null){cart=new ShoppingCartDao();}//创建购物车
			    boolean flag=cart.addToCart(flowerid_,oderitem);
			    //5.将购物车信息添加到session中
				if(flag){
					session.setAttribute("cart", cart);
					session.setAttribute("totalprice", cart.getTotalPrice());
					request.setAttribute("msg", "添加成功！");
					request.getRequestDispatcher("customer/success.jsp").forward(request, response);
				}
		  }else{
			  
			  request.getRequestDispatcher("customer/login.jsp").forward(request, response);
		  }
	}
		public void updateQuantity(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			
			//1、获取购物车信息 （在session里）
			HttpSession session=  request.getSession();
			ShoppingCartDao cart=(ShoppingCartDao)session.getAttribute("cart");
			if(cart!=null){
			//2.获取form表单数据并进行类型转换
			 String flowerid=request.getParameter("flowerid");
			 int flowerid_=0;
			 if(flowerid!=null||!"".equals(flowerid)){
				 flowerid_=Integer.parseInt(flowerid); 
			 }
			 String quantity=request.getParameter("quantity");
			 int quantity_=0;
			 if(quantity!=null||!"".equals(quantity)){
				 quantity_=Integer.parseInt(quantity); 
			 }
			 //3.购物车数量修改
				cart.updateFlower(flowerid_, quantity_);
			 //4.把更改后的内容重新放在session里
				session.setAttribute("cart", cart);
				 int total=cart.getTotalPrice();
				session.setAttribute("totalprice", total);
				PrintWriter  out=response.getWriter();
				out.print(total);
				request.getRequestDispatcher("customer/shopping_cart.jsp").forward(request, response);
			}
			
			}
		public void delOrederItem(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
		    //1.获取前台参数 flowerid
			String flowerid=request.getParameter("flowerid");
			int flowerid_=0;
			if(flowerid!=null||!"".equals(flowerid)){
				flowerid_=Integer.parseInt(flowerid);
			}
			//2.获取购物车信息
			  HttpSession session=request.getSession();
			  ShoppingCartDao cart= (ShoppingCartDao)session.getAttribute("cart");
			 if(cart!=null){
				 //3.删除订单项
				 cart.delOrderItem(flowerid_);
				 //4.把更改后的内容重新放在session里
					session.setAttribute("cart", cart);
					session.setAttribute("totalprice", cart.getTotalPrice());
					request.getRequestDispatcher("customer/shopping_cart.jsp").forward(request, response);
			 } 
				}
		private void calcuMoney(HttpServletRequest request,HttpServletResponse response) 
				throws ServletException, IOException{
			 //1.获取前台参数 flowerid
			String flowerids=request.getParameter("flowerids");
			String[] flowerids_=flowerids.split(",");//去掉拼接的，号，并且分割为字符串数组
			
			//2.获取购物车信息
			  HttpSession session=request.getSession();
			  ShoppingCartDao cart= (ShoppingCartDao)session.getAttribute("cart");
			 if(cart!=null){
				 int somemoney=0;
				 for (int i = 0; i < flowerids_.length; i++) {
					 somemoney+= cart.getSomePrice(Integer.parseInt(flowerids_[i]));
				}
			   response.setContentType("text/html;charset=utf-8");
			     PrintWriter out= response.getWriter();
			     out.print(somemoney);
			 }
			
		}
}
