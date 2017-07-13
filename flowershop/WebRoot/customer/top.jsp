<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'top.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    

	<link rel="stylesheet" type="text/css" href="css/mystyle.css">


  </head>

  <body>
        <div id="top">
             <div id="logo">
                     <img src="<%=basePath%>/images/logo.png"></img>
                     <form action="FlowerServlet?currentPage=1" id="searchform">
                         <input type="text" id="searchin" name="searchcondition">
                         <input type="submit" value="搜索" id="search">
                     </form>
                      <c:if test="${not empty sessionScope.user }">
		                  <span class="desc">欢迎${sessionScope.user.username }</span>
		              </c:if> 
		              <c:if test="${empty sessionScope.user }">
		                  <span class="desc">请登录</span>
		              </c:if> 
                     
             </div>
		       <ul id="menu">
		         <li><a href="customer/index.jsp">首页</a></li>    
		       <c:if test="${empty sessionScope.user }">
		              <li><a href="customer/login.jsp">登录</a></li>
		              <li><a href="customer/register.jsp">注册</a></li>
		               
		       </c:if>  
		        <c:if test="${! empty sessionScope.user }">
		            <li><a href="customer/shopping_cart.jsp">购物车</a></li>
		            <li><a href="customer/show_user.jsp">个人信息</a></li>
		            <li><a href="UserServlet?flag=logOut">退出</a></li>
		          </c:if>  
		           <li><a href="customer/us.jsp">联系我们</a></li>
		      </ul>
      
      </div>
  </body>
</html>
