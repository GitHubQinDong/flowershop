<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'success.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="refresh" content="5;url=${url }">
	
  <script type="text/javascript" src="<%=basePath%>/js/jquery-1.8.0.js"></script>
<!--   <script type="text/javascript">
  var i=5;
     $(function(){
        var t;
        
          t=setInterval(fun, 1000);
        if(i==0){
           $("#desc").html("跳转到");
           t=clearInterval(t);
         }
     });
     function fun(){
        $("#desc").html(i);
         i--;
     }
  
  </script> -->
  
  
  </head>
  
  <body>
  <jsp:include page="top.jsp"></jsp:include>
      <div id="content">
          <jsp:include page="left.jsp"></jsp:include>
        <h2>${requestScope.msg }</h2>  
        <span id="desc"></span>
        </div>
  </body>
</html>
