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
    
    <title>My JSP 'us.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<link rel="stylesheet" href="../css/mystyle.css" type="text/css"></link>
    <style>
      .right #jm{font-size:30px; text-align:center; border-bottom:2px dotted orange}
      .right .tj{font-size:20px;color:#77AA33;clear:both;font-weight: 900;}
      .right .cr{color:red}
      .right #tel{display: inline-block;vertical-align: middle; margin-right:10px;width:25px; height:25px;background-image:url("<%=basePath%>/images/common_z.png") ;background-position:-384px -50px;}
      .right #kf{display: inline-block;vertical-align: middle; margin-right:10px;width:25px; height:25px;background-image:url("<%=basePath%>/images/common_z.png");background-position: -297px -133px}
    </style>
  </head>
  
  <body>
 
    <div><jsp:include page="top.jsp"></jsp:include></div>
     <div id="content">
        <div class="left">
        
             <jsp:include page="left.jsp"></jsp:include>
        </div>
         <hr class="hrline">
         <div class="right"  >
					<p class="tj" id="jm">鲜花店加盟申请表</p>            
					<p class="tj">鲜花店加盟] 加盟条件</p>
	<pre>
1，<span class="cr">自有花店，经营3年以上</span>，区位优势，从业人员在3人以上，且经营状况良好；
2，花艺优秀,有专业花艺师；
3，有电脑，可以接入互联网或备有传真机；
4，有强烈的合作愿望，注重服务和信誉；
加盟负责人：胡小姐，电话：0755-26668820，QQ：2355363581
重要提醒：不接受新开业花店、准备开店者的加盟合作，此类情况，请勿咨询。
	</pre>
	   <span id="tel"></span>8888888
	   <span id="kf"></span><a>在线客服</a>
         </div>
     </div>
   
    
  </body>
</html>
