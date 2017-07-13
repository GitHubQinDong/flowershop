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
    
    <title>My JSP 'flower.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    

  <link rel="stylesheet" href="<%=basePath%>/css/mystyle.css" type="text/css"></link>
   <script type="text/javascript" src="<%=basePath%>/js/jquery-1.8.0.js"></script>
  <script type="text/javascript" src="<%=basePath%>/js/myjs.js"></script>

 </head>
  <body>
     <div><jsp:include page="top.jsp"></jsp:include></div>
  
     <div id="content">
         <jsp:include page="left.jsp"></jsp:include> 
	     <div class="right">
	         <h3>你当前搜索"<c:if test="${empty catalogname}">${searchcondition }</c:if><c:if test="${empty searchcondition}">${catalogname }</c:if>"的鲜花</h3>
		     <table class="tb"> 
			      <tr>
			         <c:forEach var="flower" items="${flowerlist }">
			           <td>
			              <form action="ShoppingServlet?flag=addcart" method="post"   >
					          <img src="<%=basePath%>/images/${flower.picture }"></img> 
					           ${flower.flowername } ${flower.price }元  <br>
					                              数量
					            <div class="count">
					             <span class="reduce">-</span>
					             <input class="count-input" type="text" value="1" name="quantity" readonly="readonly"/>
					             <span class="add">+</span>
					            </div>  
					              <input type="hidden" name="flowerid" value="${flower.flowerid }">               
					             <input type="submit" value="加入购物车"> 
			              </form> 
			           </td> 
			         </c:forEach>
			      </tr>
			 </table>
			 <br/>
			 <div>
				当前是第 ${page.currentPage } 页 ,总共 ${page.pageCount } 页, 总共 ${page.rowCount }条记录 
				<a href="FlowerServlet?currentPage=1&cid=${catalogid }&searchcondition=${searchcondition }">首页</a>
				<a href="FlowerServlet?currentPage=${page.currentPage-1 }&cid=${catalogid }&searchcondition=${searchcondition }">上一页</a>
				<a href="FlowerServlet?currentPage=${page.currentPage+1 }&cid=${catalogid }&searchcondition=${searchcondition }">下一页</a>
				<a href="FlowerServlet?currentPage=${page.pageCount }&cid=${catalogid }&searchcondition=${searchcondition }">尾页</a>&nbsp;
				<form action="FlowerServlet" >
				  <input type="text" size="8" name="currentPage" value="${page.currentPage }">
				  <input type="hidden" value="${catalogid }" name="cid">
				  <input type="hidden" value="${searchcondition }" name="searchcondition">
				  <input type="submit" value="转到">
		        </form>
	        </div>
	  </div>
  </div>
   
  </body>
</html>
