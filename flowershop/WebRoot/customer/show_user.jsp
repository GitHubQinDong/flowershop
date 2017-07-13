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
    
    <title>My JSP 'show_user.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<link rel="stylesheet" href="../css/mystyle.css" type="text/css"></link>
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
         <div class="right"  >
             <c:if test="${!empty sessionScope.user }">
               <h3>信息修改，请点击左侧链接--《《《 </h3>
             </c:if>
             <c:if test="${empty sessionScope.user }">
                  <h3>亲，您还没有登录，快快去登陆吧---》》》<a href="<%=basePath%>/customer/login.jsp">前往登录</a></h3>
              </c:if>
         </div>
     </div>
   
    
  </body>
</html>
