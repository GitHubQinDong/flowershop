<%@ page language="java" import="java.util.*,com.dao.*,com.beans.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    

  <link rel="stylesheet" href="<%=basePath%>/css/mystyle.css" type="text/css"></link>
  <script type="text/javascript" src="<%=basePath%>/js/jquery-1.8.0.js"></script>
  <style>
    #box{
        margin: 0 auto;
        width: 220px;
        height: 240px;
        overflow: hidden;/*多余的部分隐藏*/
        border: 2px solid goldenrod;
        background-color:lightgrey;
        position: relative;
      
    }
   #box img{
        height: 240px;
        width: 220px;
        top:0px;
        left:0px;
        position: absolute;
        opacity: 0;/*透明度 【0-1】0代表不显示   1代表完全显示，此处不显示*/
    }
    #box img.pic{
        opacity: 1;/*透明度 1 显示*/
    }


</style>

</head>

  <body>
     <div><jsp:include page="top.jsp"></jsp:include></div>
     <div id="content">
	      <jsp:include page="left.jsp"></jsp:include> 
	      <hr class="hrline">
	      <div class="right"><h3 align=center>新品推荐</h3>	      
	  
	      <%
	         FlowerDao flowerdao=new FlowerDao();
	         List<FlowerInfo> flowerlist= flowerdao.getNewFlowers();
	         request.setAttribute("flowerList", flowerlist);
	      
	       %>
<%-- 	<table  class="tb"> 
	      <tr>
	      <c:forEach var="flower" items="${flowerList }">
	        
	          <td>
	          <form action="ShoppingServlet?flag=addcart" method="post">
			          <img src="<%=basePath%>/images/${flower.picture }"></img> 
			           ${flower.flowername } ${flower.price }元  <br>
			                              数量<input type="text" size="10" name="quantity">
			               <input type="hidden" name="flowerid" value="${flower.flowerid }">               
			             <input type="submit" value="加入购物车"> 
	              </form>                  
	           </td>
	       
	      
	      </c:forEach>
	      </tr>
	      </table> --%>
	      <div id="box">
	         
	            <c:forEach var="flower" items="${flowerList }">
	             <img src="<%=basePath%>/images/${flower.picture }" ></img> 
                 </c:forEach>
	      </div>
	       <div class="prod_det_box">
	            <span class="special_icon"><img src="<%=basePath%>/images/special_icon.gif" alt="" title="" /></span>
                	<div class="box_top"></div>
                    <div class="box_center">
                    <div class="prod_title">Product name</div>
                    <p class="details">Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation.</p>
                    <a href="details.html" class="more">- more details -</a>
                    <div class="clear"></div>
                    </div>
                    
                    <div class="box_bottom"></div>
	      </div>
	  
	      </div> 
     </div>
  </body>
  <script>
        $(function(){
             t= setInterval(forwardplay,1000);
            $("#box img").mouseover(function(){//鼠标悬浮取消定时器
              t= clearInterval(t);
            });
            $("#box img").mouseout(function(){//鼠标移开回复定时器调用
               t= setInterval(forwardplay,1000);
            });
        });
        function forwardplay(){
               var dangqt=$("#box img.pic");
               var nextt;
            if(dangqt.next("img").length==0) {nextt=$("#box img:first");}//当图片切换到最后一张时，下一张切换为第一张图片
            else{
                nextt=dangqt.next("img");
            }               
            dangqt.animate({opacity:0},500,function(){//animate( {样式}，时间，动画完毕之后处理程序)
                dangqt.removeClass("pic");
            });
            nextt.animate({opacity:1},500,function(){
                nextt.addClass("pic");
            });
        }
        
    </script>
  
</html>
