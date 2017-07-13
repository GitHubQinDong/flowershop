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
		request.setCharacterEncoding("utf-8");//post请求中文乱码解决
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
		//1.获取前台form表单数据
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
		//类型转化
		int userid_=0;
	      if(userid!=null){ 
	    	  userid_=Integer.parseInt(userid);}
		int xb_=0;
		if(xb!=null){
			xb_=Integer.parseInt(xb);
		}
		//2.创建对象  userdetail
		UserDetailInfo detail=new UserDetailInfo();
		//3.给对象赋值
		detail.setUserid(userid_);
		detail.setTruename(truename);
		detail.setXb(xb_);
		detail.setCsrq(csrq);
		detail.setPhone(phone);
		detail.setEmail(email);
		detail.setAddress(address);
		
		//保存信息
		UserDao dao=new UserDao();
		int result=dao.saveDetail(detail);
       //根据执行结果转发到不同页面
		if(result==1){
			 request.setAttribute("msg", "修改成功！");
			 request.getRequestDispatcher("customer/success.jsp").forward(request, response);
		}else{
			request.setAttribute("msg", "修改失败！");
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
		//1.获取前台form表单数据
		String uname=request.getParameter("uname");
		String userid=request.getParameter("uid");
		String psw=request.getParameter("psw");
		   //加密
		   MD5 md5=new MD5();
		   psw=  md5.getMD5ofStr(psw);
		int userid_=0;
		       userid_=Integer.parseInt(userid);
		//2.创建对象 user 
		UserInfo user=new UserInfo();
		user.setUserid(userid_);
		user.setUsername(uname);
		user.setPassword(psw);
		//3.修改密码
		UserDao dao=new UserDao();
		int result=dao.updatePsw(user);
		//4.将修改完的用户放在session中，并根据查询结果转发到不同页面
		 HttpSession session=request.getSession();
		if(result!=0){
			session.removeAttribute("user");//清除session中旧的用户信息
			request.setAttribute("msg", "密码修改成功！");
			request.getRequestDispatcher("customer/success.jsp").forward(request, response);
		}else{
			request.setAttribute("msg", "密码修改失败！");
			request.getRequestDispatcher("customer/updatepassword.jsp").forward(request, response);
		}
		
	}
	private void login(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//1.获取前台form表单数据
		String uname=request.getParameter("uname");
		String psw=request.getParameter("psw");
		//加密
		  MD5 md5=new MD5();
		 psw=  md5.getMD5ofStr(psw);
		//2.创建对象 user 
		UserInfo user=new UserInfo();
		user.setUsername(uname);
		user.setPassword(psw);
		//3.查询是否有该用户
		UserDao dao=new UserDao();
		UserInfo recvUser=dao.getUser(user);
		//4.将用户放在session中，根据查询结果转发到不同页面
		 HttpSession session=request.getSession();
		if(recvUser!=null){
			session.setAttribute("user", recvUser);
			 request.setAttribute("msg", "登录成功！");
			 request.setAttribute("url", "customer/index.jsp");
			request.getRequestDispatcher("customer/success.jsp").forward(request, response);
		}else{
			request.getRequestDispatcher("customer/index.jsp").forward(request, response);
		}
		
		
	}
	private void register(HttpServletRequest request,HttpServletResponse response) 
			throws ServletException, IOException {
		//1.获取前台form表单数据
				String uname=request.getParameter("uname");
				String psw=request.getParameter("psw");
				System.out.println("------加密前"+psw);
				//加密
				MD5 md5 = new MD5();
				psw=md5.getMD5ofStr(psw);
				System.out.println("------加密后"+psw);
				
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
				//2.创建对象 user userdetail
				UserInfo user=new UserInfo();
				UserDetailInfo detail=new UserDetailInfo();
				//3.给对象赋值
				user.setUsername(uname);
				user.setPassword(psw);
				user.setRole(role);
				//类型转化
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
				
				//保存信息
				UserDao dao=new UserDao();
				int result=dao.addUser(user,detail);
		       //根据执行结果转发到不同页面
				if(result==1){
					 request.setAttribute("msg", "注册成功！");
					request.getRequestDispatcher("customer/success.jsp").forward(request, response);
				}else{
					request.getRequestDispatcher("customer/register.jsp").forward(request, response);
				}
		
	}
	private void checkUser(HttpServletRequest request,HttpServletResponse response)
			throws ServletException, IOException {
		//1.获取前台form表单数据
		String uname=request.getParameter("uname");
		//2.验证用户是否可用
		UserDao dao=new UserDao();
		UserInfo user=dao.checkUser(uname);
       //3.根据验证结果响应不同信息
		response.setContentType("text/html;charset=utf-8");//解决响应乱码
		PrintWriter out=response.getWriter();
		if(user!=null){
			out.print(0);
		}else{
			out.print(1);
		}
	}

}
