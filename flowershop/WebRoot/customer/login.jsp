<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'login.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
      <link rel="stylesheet" href="<%=basePath%>/css/mystyle.css" type="text/css"></link>
  <script type="text/javascript" src="<%=basePath%>/js/jquery-1.8.0.js"></script>
  <script type="text/javascript" src="<%=basePath%>/js/myjs.js"  charset="UTF-8"></script>

  </head>
  
  <body>
     <jsp:include page="top.jsp"></jsp:include>
      <div id="content">
          <jsp:include page="left.jsp"></jsp:include>
           <hr class="hrline">
      <div class="right">
          <div class="register">
                <h2>会员登录---</h2>
              <form action="UserServlet?flag=login" method="post" onsubmit="return login()">
                <table>                   
                  <tr>
                     <th>昵称</th> 
                     <td><input type="text" name="uname" placeholder="用户名" id="uname" onfocus="empty()" class="instyle"></td>
                     <td><span id="ndesc"  class="desc"></span></td>
                   </tr>
                  <tr>
                     <th> 密 码</th> 
                     <td><input type="password" name="psw" id="password" class="instyle" placeholder="welcome to here"></td>
                     <td><span id="pswdesc" class="desc"></span></td>
                  </tr>
				<tr>
				  <th>验证码:</th>
				  <td><input type="text" name="codeIn" id="code" onblur="checkCode()" ><img alt="验证码" src="CodeServlet" id="imgcode" onclick="changeCode()"/></td>
				  <td><span id="codeInMsg"></span></td>
				</tr>
			  <tr>
			    <td>&nbsp;</td>
			    <td>&nbsp;</td>
			    <td><input type="submit" value="登录" id="sub"></td>
			    </tr>  
                 </table>                    
              </form>
          
          </div>
          </div>
      </div>
  </body>
</html>
