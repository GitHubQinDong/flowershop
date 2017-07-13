<%@ page language="java" import="java.util.*,com.dao.*,com.beans.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'left.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">



  <link rel="stylesheet" href="<%=basePath%>/css/mystyle.css" type="text/css"></link>
  
  </head>
  
  <body>
     <div class="left"><h3 class="h2_gb">鲜花类别---导购</h3><hr class="line">
          <%     FlowerDao dao=new FlowerDao();
			     List<CatelogInfo> catList= dao.getAllCatalogs();
			     request.setAttribute("catalogList", catList);
			     
		 %>
		 <ul class="leftul">
		 <c:forEach var="catalog" items="${catalogList }">
		   <li>
		    <a href="FlowerServlet?cid=${catalog.catalogid}&currentPage=1">${catalog.catalogname }<br></a>    
		   </li>
		 </c:forEach>
		</ul>
	</div> 
  </body>
</html>
