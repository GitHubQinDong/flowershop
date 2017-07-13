<%@ page language="java" import="java.util.*,com.beans.*,com.dao.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'updateDetail.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">  
	  <link rel="stylesheet" href="<%=basePath%>/css/mystyle.css" type="text/css">  
  <script type="text/javascript" src="<%=basePath%>/js/calendar.js"></script>
    <script type="text/javascript" src="<%=basePath%>/js/provinces.js"></script>
  <script type="text/javascript" src="<%=basePath%>/js/city.js"></script>
  
 <script type="text/javascript" src="<%=basePath%>/js/jquery-1.8.0.js"></script>
 <script type="text/javascript" src="<%=basePath%>/js/myjs.js"></script>
 <script type="text/javascript">
    $(document).ready(function(){
        var s1 = $("#s1");
        var s2 = $("#s2");
         s1.empty();
         s2.empty();
         //给设置两个下拉列表默认
         s1.append($("<option value=''>请选择省份--</option>"));
         $("<option value=''>请选择城市--</option>").appendTo(s2);
         //读取json文件赋值给省份下拉列表
        for(var i=0;i<pr.head.length;i++){
            $("<option value='" + pr.head[i].id + "'>" + pr.head[i].text + "</option>").appendTo(s1);
        }
    });
    function getCity(){
        var s1 = $("#s1").val();
        var s2 = $("#s2");
        s2.empty();
        for(var i=0;i<city.head.length;i++){
            if(s1==city.head[i].pid){
                $("<option value='" + city.head[i].id + "'>" + city.head[i].text + "</option>").appendTo(s2);
            }
        }
    }
    </script>
  </head>
  <%  //查询 详细信息 
     UserInfo user=(UserInfo)session.getAttribute("user") ;
       int id=user.getUserid();;
       UserDao dao=new UserDao();
       UserDetailInfo udetail= dao.getUserDetailByUserId(id);
       request.setAttribute("udetail", udetail);
       %>
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
         <h2>个人信息修改---</h2>
              <form action="UserServlet?flag=saveDetail" method="post" onsubmit="return updatedetail()">
                <table>                   
                  <tr>
                     <th>昵称</th> 
                     <td><input type="text" name="uname" value="${user.username}" readonly="readonly" class="instyle"></td>
                     <td><input type="hidden" name="uid" value="${user.userid}"></td>
                   </tr>
                   <tr>
                    <tr>
                     <th>姓名</th> 
                     <td><input type="text" name="truename" id="username"  value="${udetail.truename }"class="instyle"></td>
                     <td><span id="tndesc" class="desc"></span></td>
				</tr>
                 <tr>
                     <th>性别:</th>
                     <td> 
                          <c:if test="${!empty udetail.xb }" >
                            <input type="radio" value="0" ${udetail.xb=="0"?"checked":""}   name="xb" >男     
             	            <input type="radio" value="1" ${udetail.xb=="1"?"checked":""}    name="xb">女   
             	             </td>
                        </c:if> 
             	      <td>&nbsp;</td>
             	 </tr>
                 <tr>
                 	 <th>出生日期： </th>
                	 <td><input type="text" name="csrq" class="instyle" value="${udetail.csrq }" onClick="SelectDate(this,'yyyy-MM-dd')" size=15 readonly="readonly"></td>
                     <td>&nbsp;</td>    
                 </tr>          
                <tr>
                	<th> 手机号: </th>
                    <td> <input type="text" name="phone" id="phone" value="${udetail.phone }" class="instyle"></td>
                    <td><span id="phdesc"></span></td>
                </tr>
                <tr> 
                	<th>邮箱:</th>
                	<td><input type="text" name="email" id="email" value="${udetail.email }" class="instyle"></td>
                	<td><span id="maildesc"></span></td>
               </tr>
               <tr>
                   <th>地址:</th>
                   <td><select id="s1" name="province" onchange="getCity()"></select>省
					   <select id="s2" name="city"> </select> 市
					  <input type="text" name="zone" id="address" class="instyle" value="${udetail.address }"></td>
				  <td><span id="addrdesc"></span></td>
				</tr>
				<tr>
			  <tr>
			    <td>&nbsp;</td>
			    <td>&nbsp;</td>
			    <td><input type="submit" value="保存" id="sub" ></td>
			    </tr>  
                 </table>                    
              </form>
              <span>${msg }</span>
      </div>
  </div>
  </body>
</html>
