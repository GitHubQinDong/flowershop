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
        width: 220px;
        height: 240px;
        border: 2px solid goldenrod;
        background-color:lightgrey;
        position: relative;/* 相对定位 */
        left:200px;
        overflow: hidden;
        z-index:1;
    }
   #box img{
        top:0px;
        left:0px;
        position: absolute;
        z-index:2;
    }
#box #ul1{
      position:absolute;/* 绝对定位 ，必须要指定坐标   (left,top)或者(right,bottom)  */
      bottom:0px; 
      right:0px;
      list-style:none; /* 去掉点    */
	  border:1px solid #000;
	  z-index:3;/* Z轴 ：立体层次 效果  值越大居于最上    */
      margin:0;
      padding:0;
}
#box #ul1 li { 
		padding:0px 8px;
		float:left;
		color:#FFF;
		border:#e5eaff 1px solid;
		background:#6f4f67;
		cursor:pointer;/* 鼠标悬浮时 出现手指的图标  */
	}
#box #ul1  li.on { background:#900}

</style>

</head>

  <body>
     <div><jsp:include page="top.jsp"></jsp:include></div>
     <div id="content">
	      <jsp:include page="left.jsp"></jsp:include> 
	      <div class="right"><h3 align=center>最新鲜花</h3><hr>
	    
	      <%
	         FlowerDao flowerdao=new FlowerDao();
	         List<FlowerInfo> flowerlist= flowerdao.getNewFlowers();
	         request.setAttribute("flowerList", flowerlist);
	      
	       %>

	      <div id="box">
	    
	         <c:forEach var="flower" items="${flowerList }">
	             <img src="<%=basePath%>/images/${flower.picture }" ></img> 
             </c:forEach>
               <ul id="ul1">
				<li class="on">1</li>
				<li>2</li>
				<li>3</li>
				<li>4</li>
				</ul>
	      </div>
	      
	      
	  
	      </div> 
     </div>
  </body>
  <script>
    var t = n =0, count;//全局变量  t:记录定时器，n记录图片的索引 ，count记录图片的总数
   
	$(function(){ 
			count=$("#box img").length;
			$("#box img:not(:first-child)").hide();//隐藏除第一个之外的图片
			//点击顺序标号事件
			$("#ul1 li").click(function() {
				var i = $(this).text() -1;//获取Li元素内的文本值，分别减一即索引
				n = i;
				$("#box img").filter(":visible").fadeOut(500);//当前显示的图片半秒后淡出
				$("#box img").eq(i).fadeIn(1000);//被选定图片1秒后淡入
				$(this).toggleClass("on");//切换当前选中的li的class值
				$(this).siblings().removeAttr("class");//去除除当前选中的li之外的所有li的class属性
			});
			 t=setInterval("showAuto", 2000);//每隔2秒钟自动切换图片
			 
			$("#box").hover(//鼠标悬浮图片上，取消图片切换回调函数；鼠标离开图片时，继续切换图片
			        function(){t=clearInterval(t);},
			        function(){t=setInterval("showAuto()", 2000);});
	});
	
	function showAuto(){
	   if(n>=count-1){n=0;}
	   else{ ++n;}
	   $("#ul1 li").eq(n).trigger('click');//在每一个匹配的元素上触发某类事件
	}
    </script>
  
  
  
</html>
