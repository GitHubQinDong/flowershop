package com.sevlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import util.MD5;

import com.beans.UserDetailInfo;
import com.beans.UserInfo;
import com.dao.UserDao;

public class UserServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");//post��������������
		String flag=request.getParameter("flag");
		if("checkuser".equals(flag)){
			checkUser(request,response);
		}else if("register".equals(flag)){
			register(request,response);
		}else if("login".equals(flag)){
			login(request,response);
		}else if("updatepassword".equals(flag)){
			updatePassword(request,response);
		}else if("saveDetail".equals(flag)){
			saveDetail(request,response);
		}else if("logOut".equals(flag)){
			logOut(request,response);
		}
	}
	private void saveDetail(HttpServletRequest request,HttpServletResponse response)
			throws ServletException, IOException {
		//1.��ȡǰ̨form������
		String userid=request.getParameter("uid");
		String truename=request.getParameter("truename");
		String xb=request.getParameter("xb");
		String csrq=request.getParameter("csrq");
		String phone=request.getParameter("phone");
		String email=request.getParameter("email");
		String zone=request.getParameter("zone");
		String pro=request.getParameter("province");
		String city=request.getParameter("city");
		String address=pro+city+zone;
		//����ת��
		int userid_=0;
	      if(userid!=null){ 
	    	  userid_=Integer.parseInt(userid);}
		int xb_=0;
		if(xb!=null){
			xb_=Integer.parseInt(xb);
		}
		//2.��������  userdetail
		UserDetailInfo detail=new UserDetailInfo();
		//3.������ֵ
		detail.setUserid(userid_);
		detail.setTruename(truename);
		detail.setXb(xb_);
		detail.setCsrq(csrq);
		detail.setPhone(phone);
		detail.setEmail(email);
		detail.setAddress(address);
		
		//������Ϣ
		UserDao dao=new UserDao();
		int result=dao.saveDetail(detail);
       //����ִ�н��ת������ͬҳ��
		if(result==1){
			 request.setAttribute("msg", "�޸ĳɹ���");
			 request.getRequestDispatcher("customer/success.jsp").forward(request, response);
		}else{
			request.setAttribute("msg", "�޸�ʧ�ܣ�");
			request.getRequestDispatcher("customer/updateDetail.jsp").forward(request, response);
		}
		
	}
	private void logOut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		 HttpSession session=request.getSession();
		 session.removeAttribute("user");
		 request.getRequestDispatcher("customer/index.jsp").forward(request, response);
	}
	private void updatePassword(HttpServletRequest request,HttpServletResponse response)
			throws ServletException, IOException{
		//1.��ȡǰ̨form������
		String uname=request.getParameter("uname");
		String userid=request.getParameter("uid");
		String psw=request.getParameter("psw");
		   //����
		   MD5 md5=new MD5();
		   psw=  md5.getMD5ofStr(psw);
		int userid_=0;
		       userid_=Integer.parseInt(userid);
		//2.�������� user 
		UserInfo user=new UserInfo();
		user.setUserid(userid_);
		user.setUsername(uname);
		user.setPassword(psw);
		//3.�޸�����
		UserDao dao=new UserDao();
		int result=dao.updatePsw(user);
		//4.���޸�����û�����session�У������ݲ�ѯ���ת������ͬҳ��
		 HttpSession session=request.getSession();
		if(result!=0){
			session.removeAttribute("user");//���session�оɵ��û���Ϣ
			request.setAttribute("msg", "�����޸ĳɹ���");
			request.getRequestDispatcher("customer/success.jsp").forward(request, response);
		}else{
			request.setAttribute("msg", "�����޸�ʧ�ܣ�");
			request.getRequestDispatcher("customer/updatepassword.jsp").forward(request, response);
		}
		
	}
	private void login(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//1.��ȡǰ̨form������
		String uname=request.getParameter("uname");
		String psw=request.getParameter("psw");
		//����
		  MD5 md5=new MD5();
		 psw=  md5.getMD5ofStr(psw);
		//2.�������� user 
		UserInfo user=new UserInfo();
		user.setUsername(uname);
		user.setPassword(psw);
		//3.��ѯ�Ƿ��и��û�
		UserDao dao=new UserDao();
		UserInfo recvUser=dao.getUser(user);
		//4.���û�����session�У����ݲ�ѯ���ת������ͬҳ��
		 HttpSession session=request.getSession();
		if(recvUser!=null){
			session.setAttribute("user", recvUser);
			 request.setAttribute("msg", "��¼�ɹ���");
			 request.setAttribute("url", "customer/index.jsp");
			request.getRequestDispatcher("customer/success.jsp").forward(request, response);
		}else{
			request.getRequestDispatcher("customer/index.jsp").forward(request, response);
		}
		
		
	}
	private void register(HttpServletRequest request,HttpServletResponse response) 
			throws ServletException, IOException {
		//1.��ȡǰ̨form������
				String uname=request.getParameter("uname");
				String psw=request.getParameter("psw");
				System.out.println("------����ǰ"+psw);
				//����
				MD5 md5 = new MD5();
				psw=md5.getMD5ofStr(psw);
				System.out.println("------���ܺ�"+psw);
				
				String role=request.getParameter("role");
				String truename=request.getParameter("truename");
				String xb=request.getParameter("xb");
				String csrq=request.getParameter("csrq");
				String phone=request.getParameter("phone");
				String email=request.getParameter("email");
				String zone=request.getParameter("zone");
				String pro=request.getParameter("province");
				String city=request.getParameter("city");
				String address=pro+city+zone;
				//2.�������� user userdetail
				UserInfo user=new UserInfo();
				UserDetailInfo detail=new UserDetailInfo();
				//3.������ֵ
				user.setUsername(uname);
				user.setPassword(psw);
				user.setRole(role);
				//����ת��
				int xb_=0;
				if(xb!=null){
					xb_=Integer.parseInt(xb);
				}
				detail.setTruename(truename);
				detail.setXb(xb_);
				detail.setCsrq(csrq);
				detail.setPhone(phone);
				detail.setEmail(email);
				detail.setAddress(address);
				
				//������Ϣ
				UserDao dao=new UserDao();
				int result=dao.addUser(user,detail);
		       //����ִ�н��ת������ͬҳ��
				if(result==1){
					 request.setAttribute("msg", "ע��ɹ���");
					request.getRequestDispatcher("customer/success.jsp").forward(request, response);
				}else{
					request.getRequestDispatcher("customer/register.jsp").forward(request, response);
				}
		
	}
	private void checkUser(HttpServletRequest request,HttpServletResponse response)
			throws ServletException, IOException {
		//1.��ȡǰ̨form������
		String uname=request.getParameter("uname");
		//2.��֤�û��Ƿ����
		UserDao dao=new UserDao();
		UserInfo user=dao.checkUser(uname);
       //3.������֤�����Ӧ��ͬ��Ϣ
		response.setContentType("text/html;charset=utf-8");//�����Ӧ����
		PrintWriter out=response.getWriter();
		if(user!=null){
			out.print(0);
		}else{
			out.print(1);
		}
	}

}
