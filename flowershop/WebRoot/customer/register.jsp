<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'register.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
    <meta http-equiv="content-type" content="text/html; charset=UTF-8" />

  <link rel="stylesheet" href="<%=basePath%>/css/mystyle.css" type="text/css"></link>
  <script type="text/javascript" src="<%=basePath%>/js/calendar.js"></script>
 <script type="text/javascript" src="<%=basePath%>/js/jquery-1.8.0.js"></script>
 
 <script type="text/javascript" src="<%=basePath%>/js/myjs.js"></script>
  <script type="text/javascript" src="<%=basePath%>/js/provinces.js"></script>
  <script type="text/javascript" src="<%=basePath%>/js/city.js"></script>
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
  
  <body>
      <jsp:include page="top.jsp"></jsp:include>
      <div id="content">
          <jsp:include page="left.jsp"></jsp:include>
           <hr class="hrline">
         <div class="right">
          <div class="register">
                <h2>会员注册---</h2>
              <form action="UserServlet?flag=register" method="post" name="form1" onsubmit="return regiter()">
                <table class="regisTb">                   
                  <tr>
                     <th>昵称</th> 
                     <td><input type="text" name="uname" value="用户名" id="uname"class="instyle" onblur="checkuser()" onfocus="clearVal(this)" onkeypress="clearMsg(this)" ></td>
                     <td><span id="unameMsg"  class="desc">*</span></td>
                   </tr>
                  <tr>
                     <th> 密 码</th> 
                     <td><input type="password" name="psw" id="psw" class="instyle" onblur="checkpsw()" onfocus="clearVal(this)" onkeypress="clearMsg(this)"></td>
                     <td><span id="pswMsg" class="desc">*</span></td>
                  </tr>
                  <tr>
                     <th>姓名</th> 
                     <td><input type="text" name="truename" id="truename" class="instyle" onblur="checktruename()" onfocus="clearVal(this)" onkeypress="clearMsg(this)"></td>
                     <td><span id="truenameMsg" class="desc"></span></td>
				</tr>
                 <tr>
                     <th>性别:</th>
                     <td><input type="radio" value="0" name="xb" checked="checked">男     
             	          <input type="radio" value="1" name="xb">女  </td>
             	      <td><span id="sexMsg" class="desc">*</span></td>
             	 </tr>
                 <tr>
                 	 <th>出生日期： </th>
                	 <td><input type="text" name="csrq" class="instyle" onClick="SelectDate(this,'yyyy-MM-dd')" size=15 readonly="readonly"  onfocus="clearVal(this)" onkeypress="clearMsg(this)"></td>
                     <td><span id="csrqMsg" class="desc"></span></td>    
                 </tr>          
                <tr>
                	<th> 手机号: </th>
                    <td> <input type="text" name="phone" id="phone" class="instyle" onblur="checkphone()" onfocus="clearVal(this)" onkeypress="clearMsg(this)"></td>
                    <td><span id="phoneMsg">*</span></td>
                </tr>
                <tr> 
                	<th>邮箱:</th>
                	<td><input type="text" name="email" id="email" class="instyle" onblur="checkEmail()"onfocus="clearVal(this)" onkeypress="clearMsg(this)"></td>
                	<td><span id="emailMsg"></span></td>
               </tr>
               <tr>
                   <th>地址:</th>
                   <td><select id="s1" name="province" onchange="getCity()"> </select>
					   <select id="s2" name="city"></select> <br>
					   街道/镇<input type="text" size=25 name="zone" id="zone"  onblur="checkZone()"onfocus="clearVal(this)" onkeypress="clearMsg(this)"/>
                    </td>
				  <td><span id="zoneMsg">*</span></td>
				</tr>
				<tr>
				  <th>验证码:</th>
				  <td><input type="text" name="codeIn" id="code" onblur="checkCode()" onfocus="clearVal(this)" onkeypress="clearMsg(this)">
				      <img alt="验证码" src="CodeServlet" id="imgcode" onclick="changeCode()"/>
				  </td>
				  <td><span id="codeInMsg"></span></td>
				</tr>
			  <tr>
			    <td>&nbsp;</td>
			    <td><input type="hidden" name="role" value="customer"></td>
			    <td><input type="submit" value="注册" id="sub"></td>
			    </tr>  
                 </table>                    
              </form>
          
          </div>
           
      </div>
      
      
      </div>
  </body>
</html>
