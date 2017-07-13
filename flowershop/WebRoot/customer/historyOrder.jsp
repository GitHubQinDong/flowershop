<%@page import="java.text.*"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'orderSuccess.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    

  </head>
  
  <body>
        <div><jsp:include page="top.jsp"></jsp:include></div>
	     <div id="content">
	        <div class="left">
		         <ul class="leftul">
	           		<li><a href="customer/updatepassword.jsp" target="_self">修改密码</a></li>
	           		<li><a href="customer/updateDetail.jsp" target="_self" >修改详细信息</a></li>
	           		<li><a href="OrderServlet?flag=selectOrders" target="_self">查询历史订单</a></li>
	           </ul>
	        </div>
          <div class="right">
                
               	<h3>您的 历史 订单明细： </h3>
               	   <c:forEach var="order" items="${orderList}">
               	                         订单号：${order.orderid }, 总金额：￥${order.totalprice }元,下单时间：${order.orderdate }<br> 
               	   </c:forEach>
    
          </div>
         </div>
  </body>
</html>
