<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'updatepassword.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">  
	  <link rel="stylesheet" href="<%=basePath%>/css/mystyle.css" type="text/css">  
  <script type="text/javascript" src="<%=basePath%>/js/jquery-1.8.0.js"></script>
  <script type="text/javascript" src="<%=basePath%>/js/myjs.js"></script>
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
       <div class="register">
         <h2>密码修改---</h2>
              <form action="UserServlet?flag=updatepassword" method="post" onsubmit="return updatepassword()">
                <table>                   
                  <tr>
                     <th>昵称</th> 
                     <td><input type="text" name="uname" value="${user.username}" readonly="readonly" class="instyle"></td>
                     <td><input type="hidden" name="uid" value="${user.userid}"></td>
                   </tr>
                   <tr>
                  <tr>
                     <th> 新密 码</th> 
                     <td><input type="password" name="psw"   id="psw" class="instyle"></td>
                     <td><span id="pswdesc" class="desc"></span></td>
                  </tr>
				<tr>
				  <th>确认密 码:</th>
				  <td><input type="password" name="psw2" id="psw2"  class="instyle" onblur="checkpswIsSame()"></td>
				  <td><span id="pswdesc2"></span></td>
				</tr>
			  <tr>
			    <td>&nbsp;</td>
			    <td>&nbsp;</td>
			    <td><input type="submit" value="保存" id="sub" ></td>
			    </tr>  
                 </table>                    
              </form>
              <span></span>
      </div>
  </div>
  </body>
</html>
