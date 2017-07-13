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
      <jsp:include page="top.jsp"></jsp:include>
      <div id="content">
          <jsp:include page="left.jsp"></jsp:include>
          <div class="right">
                <h2>欢迎${sessionScope.user.username }</h2>
               	 您的订单已下达！<br>
               	  订单号：${order.orderid },订单总额：${order.totalprice },待支付…
               	  <hr>
               	  订单明细： <br>
               	   <c:forEach var="orderitem" items="${order.orderitems }">
               	                       鲜花名：${orderitem.flower.flowername}  单价：${orderitem.flower.price} 
               	                       数量:${orderitem.quantity}   小计:${orderitem.flower.price*orderitem.quantity}<br>
               	   </c:forEach>
          </div>
         </div>
  </body>
</html>
