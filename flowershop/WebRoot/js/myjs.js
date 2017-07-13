/*用ajax验证昵称是否可用*/
function checkuser(){
	    var pass = true;
	    var msg = $('#unameMsg');
		//昵称：以字母开头，由数字字母下划线组成的6位
		 var re=/^[a-zA-Z]\w{5,}$/;
	    if ($('#uname').val() == "") {
	    	msg.css("color","red");
	        msg.html("*必填项") ;
	        pass = false;
	    } 
	    else {
	         if(!re.test($('#uname').val())){
	        	 msg.css("color","red");
	 	         msg.html("昵称：以字母开头，由数字字母下划线组成，长度6位以上") ;
	 	         pass = false;
	         }
	         else{
	        	 $.ajax({
	 				type:"post",
	 				url:"UserServlet?flag=checkuser",
	 				data:{uname:$('#uname').val()},
	 				success:function(data){
	 					if(data=="0"){
	 						$("#unameMsg").css("color","red");
	 						$("#unameMsg").html("昵称已占用");
	 						 pass = false;
	 					}else{
	 						$("#unameMsg").css("color","green");
	 						$("#unameMsg").html("可用");
	 					}
	 				}
	 			});
	         }
	     }
	    return  pass ;
}
/*验证密码*/
function checkpsw() {
    var pass = true;
    var msg = $('#pswMsg');
    //密码要求(6-20位的数字)
    var re=/^[0-9]{6,20}$/;
    if ($('#psw').val() == "") {
    	msg.css("color","red");
        msg.html("*必填项") ;
        pass = false;
    } else {
		    if (!re.test($('#psw').val())) {
		    	    msg.css("color","red");
		            msg.html("密码:6-20位的数字") ;
		            pass = false;
		        } 
		    else {
		        	 msg.css("color","green");
		        	 msg.html("ok") ;
		    }
    }
    return pass;
}
/*验证姓名*/
function checktruename(){
	 var pass = true;
	    var msg = $('#truenameMsg');
	     //真实姓名 ：2到4位的中文
		 var re=/[\u4e00-\u9fa5]{2,4}/;
	    if (!re.test($('#truename').val())) {
	    	    msg.css("color","red");
	            msg.html("姓名 ：2到4位的中文") ;
	            pass = false;
	        } else {
	        	 msg.css("color","green");
	        	 msg.html("ok") ;
	    }
	    return pass;
}
/*验证手机号*/
function checkphone(){
	 var pass = true;
	    var msg = $('#phoneMsg');
	     //手机号：以13或15或189开头的11位的数字
		  var re=/^13(\d{9})$|^15(\d{9})$|^189(\d{8})$/;
		  if ($('#phone').val() == "") {
		    	msg.css("color","red");
		        msg.html("*必填项") ;
		        pass = false;
		    } else {
				    if (!re.test($('#phone').val())) {
				    	    msg.css("color","red");
				            msg.html("手机号：以13或15或189开头的11位的数字") ;
				            pass = false;
				        } else {
				        	 msg.css("color","green");
				        	 msg.html("ok") ;
				    } 
	    }
	    return pass;
		  
}

/*定义正则：符合邮箱格式*/
function isEmail(email) {
    if (email.length > 50) {
        return false;
    }
    var re = /^(([0-9a-zA-Z]+)|([0-9a-zA-Z]+[_.0-9a-zA-Z-]*[0-9a-zA-Z]+))@([a-zA-Z0-9-]+[.])+([a-zA-Z]{2}|net|NET|com|COM|gov|GOV|mil|MIL|org|ORG|edu|EDU|int|INT)$/;
    if (email.search(re) != -1) {
        return true;
    }
    return false;
}
/*验证邮箱*/
function checkEmail() {
    var pass = true;
    var msg = $('#emailMsg');
    if (!isEmail($('#email').val())) {
    	    msg.css("color","red");
            msg.html("请输入有效的Email") ;
            pass = false;
        } else {
        	 msg.css("color","green");
        	 msg.html("ok") ;
    }
    return pass;
}
/*验证街道/镇*/
function checkzone(){
	 var pass = true;
	    var msg = $('#zoneMsg');
	     //地址:不超过50位
		  var re=/[a-zA-Z\u4e00-\u9fa5]{1,50}$/;
		  if ($('#zone').val() == "") {
		    	msg.css("color","red");
		        msg.html("*必填项") ;
		        pass = false;
		    } else {
				    if (!re.test($('#zone').val())) {
				    	    msg.css("color","red");
				            msg.html("地址:不超过50位") ;
				            pass = false;
				        } else {
				        	 msg.css("color","green");
				        	 msg.html("ok") ;
				    } 
	    }
	    return pass;
		  
}
/*验证输入的验证码是否正确*/
function checkCode(){
	 var pass = true;
	 var msg = $('#codeInMsg');
	if($("#code").val()==""){
		  msg.html("请填写验证码") ;
		  pass = false;
	}else{
		$.ajax({
			type:"get",
			url:"CheckCodeServlert",
			data:{codeIn:$('#code').val()},
			success:function(data){
				if(data=="1"){
					$("#codeInMsg").css("color","green");
					$("#codeInMsg").html("ok");
				}else{
					$("#codeInMsg").css("color","red");
					$("#codeInMsg").html("验证码错误");
					 pass = false;
				}
			}
		});
	}
	return pass;
}
/*刷新验证码*/
function changeCode(){
	$("#imgcode").attr("src","CodeServlet?t="+new Date());	
}
/*获得焦点时清空信息*/
function clearVal(obj){
	obj.value="";
}
/*键盘按下时清空提示信息*/
function clearMsg(obj) {
    var msg = document.getElementById(obj.name + "Msg");
    msg.className = "";
    msg.setAttribute("class", "");
    msg.innerHTML = "";
}
/*提交时验证*/
function regiter(){
	  var flag=true;
	  if (!checkuser()) {
	        $("#uname").focus();
	        flag= false;
	    }
	   else if (!checkpsw()) {
	    	 $("#psw").focus();
	        flag= false;
	    }
	    else if (!checktruename()) {
	    	$("#truename").focus();
	        flag= false;
	    }
	    else if (!checkphone()) {
	    	$("#phone").focus();
	        flag= false;
	    }
	    else if (!checkEmail()) {
	    	$("#email").focus();
	        flag= false;
	    }
	    else if (!checkzone()) {
	    	$("#zone").focus();
	        flag= false;
	    }
	    else  if (!checkCode()) {
	    	$("#code").focus();
	        flag= false;
	    }
	    else if(confirm("提交吗？")==false){
		    flag=false;
	   }
	  return flag;

}

function login(){
	var flag=true;
	  //昵称：以字母开头，由数字字母下划线组成的6位
	  var rench=/^[a-zA-Z]\w{5,}$/;
	  //密码：6-20位的数字
	  var repsw=/^[0-9]{6,20}$/;
	  if(!rench.test($("#uname").val())){

		  $("#ndesc").html("以字母开头，由数字字母下划线组成的6位" ); 
		  $("#ndesc").css("color","red");
		  flag=false;
	  }else if(repsw.test($("#password").val())==false){
		  $("#pswdesc").html("6-8位的数字!" ); 
		  $("#pswdesc").css("color","red");
		  flag=false;
	  }else if($("#ajaxdesc").html()=="no"){
		  $("#imgcode").attr("src","CodeServlet?t="+new Date());
		  flag=false;
	  }
	  return flag;
}

function checkAll(){
	 var aa=$(".cb");
     aa.attr("checked",true);
     confirmsome();
}
function checkNo(){
	 var aa=$(".cb");
    aa.attr("checked",false);
    getMoney();
}
function checkAllOrNo(){
	var cbs=document.getElementsByName("cb");
	 for(var i=0;i<cbs.length;i++){
		
		 if(cbs[i].checked==true) 
			  cbs[i].checked=false;   
		  else 
			  cbs[i].checked=true;
		}
	 getMoney();
	 }
function checkAllOrNo2(){
	 var aa=$(".cb");
	 if( aa.attr("checked")){
        aa.attr("checked",false);}
     else {
		 aa.attr("checked",false); }
	 getMoney();
	 }

//拼接checkbox的值
function getflowerids(){
	var checklen = document.getElementsByName("cb");
	var all = "";//拼接checkbox的值
	for ( var j = 0; j < checklen.length; j++) {
		if (checklen[j].checked == true) {
			all+=checklen[j].value+",";
		}
	}
	return all;
}
//通过ajax动态获得选中的订单项的总金额
function getMoney(){
	var ids=getflowerids();
	if(ids!=""&&ids!=null){
		ids=ids.substring(0,ids.length-1);
		$.ajax({
			url:"ShoppingServlet",
			data:{flowerids:ids,flag:"calcuMoney"}	,
			type:"post",
			success:function(data){
				$("#money").html(data);
			}
		});
	}
}	
//提交订单前验证
function checkOrder(){
	var flag=true;
	var ids=getflowerids();
	if(ids==""||ids==null){
		alert("请至少选择一项！");
		flag=false;
	}else if(confirm("确认提交订单？")){
		$("#selflowerids").val(ids);//获取选中的flowerid
		$("#total").val($("#totalprice").html());
		flag=true;
	}
	return flag;
}
//修改密码，提交前验证
function updatepassword(){
	var flag=true;
	if($("#psw2").val()!=$("#psw").val()){
		$("#pswdesc2").css("color","red");
		$("#pswdesc2").html("两次输入不一致");
		flag=false;
	}
	if(confirm("确认修改？")==false){
		flag=false;
	}
	return flag;
}
//两次输入密码是否一致
function checkpswIsSame(){
	if($("#psw2").val()!=$("#psw").val()){
		$("#pswdesc2").css("color","red");
		$("#pswdesc2").html("两次输入不一致");
        $("#sub").attr("disabled",true);
	}else{
		 $("#sub").attr("disabled",false);
	}

}
//购物数目框
$(function(){
    $(".add").click(function(){
    	var t=$(this).parent().find('input[class*=count-input]');
          t.val(parseInt(t.val())+1) ;
          setTotal(); 
    });
    $(".reduce").click(function(){
    	var t=$(this).parent().find('input[class*=count-input]');
         t.val(parseInt(t.val())-1);
			if(parseInt(t.val())<1){
			    t.val(1);
           } 
		setTotal(); 
    });

function setTotal(){
	var s=0;
	$(".imagetable tr:not(:first)").each(function(){
	s+=parseInt($(this).find('input[class*=count-input]').val())*parseFloat($(this).find('span[class*=price]').text());
	});
	$("#totalprice").html(s.toFixed(2));
	 $.ajax({
		 type:"get",
		 data:{flag:"updateQt",quantity:$(".count-input").val(),flowerid:$(".fid").val()},
		 url:"ShoppingServlet",
		 sunccess:function(data){
			 $("#totalprice").html(data);
		 }
	 });
	}
setTotal();
});