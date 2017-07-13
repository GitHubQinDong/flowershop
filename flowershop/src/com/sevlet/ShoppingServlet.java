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
		request.setCharacterEncoding("utf-8");//post��������������
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
		request.setCharacterEncoding("utf-8");//post��������������
		//1.��ȡǰ̨form������
		String quantity=request.getParameter("quantity");
		String flowerid=request.getParameter("flowerid");
		//����ת��
		int quantity_=0;
		  if(quantity!=null){
			  quantity_=Integer.parseInt(quantity);
		  }
		int flowerid_=0;
		  if(flowerid!=null){
			  flowerid_=Integer.parseInt(flowerid);
		  }
		//2.��֤�û��Ƿ��¼
		  HttpSession session=request.getSession();//���session����
		  if(session.getAttribute("user")!=null){
		
				//3.���ɶ��������
				  //1��ͨ��flowerid��ѯ�ʻ�����
				  FlowerDao dao=new FlowerDao();
				  FlowerInfo  flower=dao.getFlowerByFlowerId(flowerid_);
				  //2)�������ֵ
					OrderItemInfo oderitem=new OrderItemInfo();
					oderitem.setFlower(flower);
					oderitem.setQuantity(quantity_);
				//4.��ӵ����ﳵ :  ���Ȼ�ȡ���ﳵ����ʷ��������Ϣ���ߴ������ﳵ
				ShoppingCartDao cart=(ShoppingCartDao)session.getAttribute("cart");
				if(cart==null){cart=new ShoppingCartDao();}//�������ﳵ
			    boolean flag=cart.addToCart(flowerid_,oderitem);
			    //5.�����ﳵ��Ϣ��ӵ�session��
				if(flag){
					session.setAttribute("cart", cart);
					session.setAttribute("totalprice", cart.getTotalPrice());
					request.setAttribute("msg", "��ӳɹ���");
					request.getRequestDispatcher("customer/success.jsp").forward(request, response);
				}
		  }else{
			  
			  request.getRequestDispatcher("customer/login.jsp").forward(request, response);
		  }
	}
		public void updateQuantity(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			
			//1����ȡ���ﳵ��Ϣ ����session�
			HttpSession session=  request.getSession();
			ShoppingCartDao cart=(ShoppingCartDao)session.getAttribute("cart");
			if(cart!=null){
			//2.��ȡform�����ݲ���������ת��
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
			 //3.���ﳵ�����޸�
				cart.updateFlower(flowerid_, quantity_);
			 //4.�Ѹ��ĺ���������·���session��
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
		    //1.��ȡǰ̨���� flowerid
			String flowerid=request.getParameter("flowerid");
			int flowerid_=0;
			if(flowerid!=null||!"".equals(flowerid)){
				flowerid_=Integer.parseInt(flowerid);
			}
			//2.��ȡ���ﳵ��Ϣ
			  HttpSession session=request.getSession();
			  ShoppingCartDao cart= (ShoppingCartDao)session.getAttribute("cart");
			 if(cart!=null){
				 //3.ɾ��������
				 cart.delOrderItem(flowerid_);
				 //4.�Ѹ��ĺ���������·���session��
					session.setAttribute("cart", cart);
					session.setAttribute("totalprice", cart.getTotalPrice());
					request.getRequestDispatcher("customer/shopping_cart.jsp").forward(request, response);
			 } 
				}
		private void calcuMoney(HttpServletRequest request,HttpServletResponse response) 
				throws ServletException, IOException{
			 //1.��ȡǰ̨���� flowerid
			String flowerids=request.getParameter("flowerids");
			String[] flowerids_=flowerids.split(",");//ȥ��ƴ�ӵģ��ţ����ҷָ�Ϊ�ַ�������
			
			//2.��ȡ���ﳵ��Ϣ
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
