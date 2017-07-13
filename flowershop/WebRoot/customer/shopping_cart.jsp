<%@ page language="java" import="java.util.*,com.dao.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'shopping_cart.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<link rel="stylesheet" href="<%=basePath%>/css/mystyle.css" type="text/css"></link>
  <script type="text/javascript" src="<%=basePath%>/js/jquery-1.8.0.js"></script>
  
  <script type="text/javascript" src="<%=basePath%>/js/myjs.js"></script></head>
  
  <body><jsp:include page="top.jsp"></jsp:include>
      <div id="content">
          <jsp:include page="left.jsp"></jsp:include>
          <div class="right">
           <c:if test="${empty  sessionScope.user }">
               <h3>亲，您还没有登录，快快去登陆吧---》》》<a href="<%=basePath%>/customer/login.jsp">前往登录</a></h3>
            </c:if>
            <c:if test="${!empty  sessionScope.user }">
            <c:if test="${empty  sessionScope.cart.items }">
             		<h3>购物车空空如也，快快选购吧~~</h3>
              </c:if>
              <c:if test="${!empty  sessionScope.cart.items }">
		           <table class="imagetable">
		             <tr><th>★</th><th>图片<th>花名</th><th>价格</th><th>订单数量</th><th>操作</th></tr>
		             <c:forEach var="cartitem" items="${sessionScope.cart.items }">
		               <tr>
		        			<td><input type="checkbox" class="cb" name="cb" value="${cartitem.key }" onchange="getMoney()">  ${cartitem.key }  </td>
		        			<td><img src="<%=basePath%>/images/${cartitem.value.flower.picture}" class="cartp"></td>
		               		<td>[${cartitem.value.flower.flowername}]
		               		     <input class="fid" type="hidden" value="${cartitem.value.flower.flowerid}"> 
		               		 </td>
		                	<td><span class="price">${cartitem.value.flower.price}</span>元 </td>
		           	     	<td>
		           	     	   <div class="count">
					             <span class="reduce">-</span>
					             <input class="count-input" type="text" value="${cartitem.value.quantity}" name="quantity" />
					             <span class="add">+</span>
					            </div>
		           	     	</td>
		           	     	<td>
		           	     	   <a href="ShoppingServlet?flag=delItem&flowerid=${cartitem.key }">删除</a>
		           	     	</td>
		             </c:forEach>
		          </table>
		          <input type="button"  value="全选" onclick="checkAll()">
		          <input type="button"  value="全不选" onclick="checkNo()">
		          <input type="button"  value="反选" onclick="checkAllOrNo()"><br>                    
		          <hr>  
		                                  总金额： ￥<span id="totalprice"></span>
		               <form action="OrderServlet?flag=confirmOrder" method="post" onsubmit="return checkOrder()">
		                 <input type="hidden" name="flowerids" id="selflowerids" >
		                 <input type="hidden" name="totalprice" id="total" >
		                 <input type="submit" value="提交订单">
		               </form>
		          
		        </c:if>  
              </c:if>
          </div>
         </div>
  </body>
</html>
