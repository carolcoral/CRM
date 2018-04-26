<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	pageContext.setAttribute("PATH", request.getContextPath());
			
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
	<meta name="msapplication-tap-highlight" content="no" />
	<meta name="robots" content="noindex" />
	<meta http-equiv="X-UA-Compatible" content="IE=Edge,chrome=1" />
	<meta name="renderer" content="webkit" />	
	<link rel="stylesheet" type="text/css" href="../common/css/bootstrap.min.css" />
	<link rel="stylesheet" type="text/css" href="../common/css/winTip.css" />	
	<link rel="stylesheet" type="text/css" href="../system/css/resetPwd.css" />
	<title>发送邮件</title>	
</head>
<body>
	<div class="header">
        <div class="head">
            <a href="${PATH }/login.do" class="login">登录</a>
        </div>
    </div>
    <div class="emailWrap">        
        <h2 class="tit">找回密码</h1>
        <h3 class="prossess"><img src="../system/img/send.jpg"></h3> 
        <div class="input-group">
	       	<label>请输入邮箱：</label>
	        <input type="text" class="text" id="email" name="email">
	        <span class="huajin">@qq.com</span>	        
	    </div>
	    <p class="error"></p> 
	    <p class="send"><a href="javascript:;" onclick="sendEmail()">确认提交</a></p>  
    </div>
    <div class="footer">
	   <p>Copyright 易第优（北京）教育咨询股份有限公司 2006 - 2017 Edu Inc. 京ICP备11018177号 京公网安备11011402000177</p>
	</div>	
	<!-- 提示 -->
    <div class="themisWrap" style="display:none;" id="loginTip">
      <div class="themisGray"></div>
        <div class="themis" style="top:30%;">
           <h3 class="themistit"><span class="themisTipPic" style="float: left;padding-top: 17px;padding-left: 10px;margin-right: 10px;"><img class="pic" src="../system/img/tishi.png" height="25" width="25" alt="" /></span>友情提示</h3>
           <div class="themispay">
                <div class="themistip" style="margin-bottom: 20px; color:red; font-size:14px;">邮件已发送成功，请查收邮件</div>
                <button onclick="wuorder.CloseDiv('loginTip','fade')" class="btn btn-default navbar-right"  style="margin-left:10px; margin-right: 0px;">取消</button>
                <a href="/login.do" class="btn btn-primary navbar-right">确定</a> 
           </div>
        </div>
      </div>
<script type="text/javascript" src="../common/js/jquery-1.11.1.min.js"></script>
<script type="text/javascript">	
	function sendEmail(){
		var email = $('#email').val()+"@qq.com";		
		if (email == "" || email == null) {
			$('.error').html("内容不能为空");
			return false; 
		}
		$.ajax({        			
		   type: 'POST',
		   data: {
			  email:email
		   },
		   url: "../forget/sendEmail.do",		   
		   success: function(data) {
		   	    console.log(data);
		   		if(data=="success"){
		   			$('#loginTip').css('display','block');		   			
		   		}else if (data=="1"){
		   			$('.error').html("用户不存在");
		   		}		   
		 	},
			error: function (data){
				console.log(data);
			}
		})		
		$('.send a').html("正在提交...")		
		$('.send a').removeAttr("onclick");
	}
	
</script>
</body>
</html>