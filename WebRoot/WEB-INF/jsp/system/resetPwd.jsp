<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
	<meta name="msapplication-tap-highlight" content="no" />
	<meta name="robots" content="noindex" />
	<meta http-equiv="X-UA-Compatible" content="IE=Edge,chrome=1" />
	<meta name="renderer" content="webkit" />	
	<link rel="stylesheet" type="text/css" href="../system/css/resetPwd.css" />
	<title>重置密码</title>	
</head>
<body>
	<div class="header">
        <div class="head">
            <h1 class="logo"><img src="/system/img/reset.png"></h1>
            <a href="/login.do" class="login">登录</a>
        </div>
    </div>
    <div class="emailWrap">        
        <h2 class="tit">找回密码</h1>
        <div class="sendPwd">
        	<h3 class="prossess"><img src="/system/img/resetPwd.jpg"></h3> 
	        <div class="input-group">
		       	<label>请输入新密码：</label>
		        <input type="password" class="text" id="newpassword">	        	        
		    </div>
		    <p class="pwderror error"></p> 
		    <div class="input-group">
		       	<label>请确认新密码：</label>
		        <input type="password" class="text" id="repassword">	        	        
		    </div>
		    <p class="repwderror error"></p> 
		    <p class="send"><a href="javascript:;" onclick="sendPassword()">确认提交</a></p> 
        </div>
        <div class="finish" style="display:none">
        	<h3 class="prossess"><img src="/system/img/finishpwd.jpg"></h3> 
	        <p class="finishTip">新密码已修改完成</p>
		    <p class="send"><a href="/login.do">登录</a></p> 
        </div>
    </div>
    <div class="footer">
	   <p>Copyright 易第优（北京）教育咨询股份有限公司 2006 - 2017 Edu Inc. 京ICP备11018177号 京公网安备11011402000177</p>
	</div>			
<script type="text/javascript" src="../common/js/jquery-1.11.1.min.js"></script>
<script type="text/javascript">	
	
	function GetUrlString(name){
	     var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
	     var r = window.location.search.substr(1).match(reg);
	     if(r!=null)return  unescape(r[2]); return null;
	}
	var email=GetUrlString("email");	
	function sendPassword(){
		var newpassword = $('#newpassword').val();
		var repassword = $('#repassword').val();			
		if (resetPwd(newpassword,repassword)){
			$.ajax({        			
			   type: 'POST',
			   data: {
			   		email:email,
			   		newPassword:repassword
			   },
			   url: "/forget/resetPassword.do",		   
			   success: function(data) {
			   		console.log(data);
			   	   $('.sendPwd').css('display','none');		   
				   $('.finish').css('display','block')
			 	},
				error: function (data){
					console.log(data);
				}
			})
		}
	}
	function resetPwd(newpwd,repwd) {
		var flag=true;
		if (newpwd == "" || newpwd == null) {
			$('.pwderror').html("新密码不能为空");
			flag=false;
		}
		if (repwd == "" || repwd == null) {		
			$('.repwderror').html("确认密码不能为空");			
			flag=false;
		}5
		if (repwd != newpwd) {
			$('.repwderror').html("两次密码不一致");
			flag=false;
		}	
		return flag;
	}
</script>
</body>
</html>