<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
	<link rel="stylesheet" type="text/css" href="${PATH }/common/css/bootstrap.min.css" />
	<link rel="stylesheet" type="text/css" href="${PATH }/system/css/login.css" />
	<title>登录</title>
</head>
<script type="text/javascript">
	window.onload = function(){
		//如果登录页嵌入iframe则自动刷新
		if (self.frameElement != null) {
			parent.location.reload();
	    }
	};

	
</script>


<body>
<div class="content">
	<div class="header">
	  <h1 class="ui_tit">欢迎登录教学CRM系统</h1>	  
	</div>	
    <form action="${PATH }/login.do" method="post" onsubmit="return loginValidate();" id="loginform">
	 	<div style="width:100%;margin: 0 auto;text-align: center;height: 45px;line-height: 45px;">
			<span id="loginFailInfo"></span>
		</div>
		<div class="crm-register" >		
		  	<ul>
				<li>
					<div class="form-group">
					    <div class="input-group">
					      <div class="input-group-addon"><span class="glyphicon glyphicon-user firstfold" aria-hidden="true"></span></div>
					      <input type="text" class="text" id="email" placeholder="请输入用户名" name="email" >
					    </div>
					  </div>					
					<select class="huajin" name="sign">
						<option value="0">@qq.com</option>
					</select>
				</li>
				<li>
					<div class="input-group">
				      <div class="input-group-addon"><span class="glyphicon glyphicon-lock" aria-hidden="true"></span></div>
				      <input type="password" class="text" id="password" name="password"  placeholder="请输入密码">
				    </div>
				</li>      				
		  	</ul>
	    </div>          
	    <div class="login">
	        <button type="submit"><span class=""></span> 登录</button>
	    </div>
	    <a href="${PATH }/forget/indexEmail.do" class="forget">忘记密码？</a>  
    </form>
</div>
<div class="footer">
   <p>Copyright 易第优（北京）教育咨询股份有限公司 2006 - 2017 Edu Inc. 京ICP备11018177号 京公网安备11011402000177</p>
</div>
<script type="text/javascript" src="${PATH }/common/js/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="${PATH }/common/js/angular.min.js"></script>
<script type="text/javascript">
	function loginValidate(){
		var usernameObj = $("#email").val()+"@qq.com";
		var passwordObj = $("#password");
		if($.trim(usernameObj).length == 0){
			usernameObj.tooltip("show");
			return false;
		}
		if($.trim(passwordObj).length == 0){
			passwordObj.tooltip("show");
			return false;
		}
		return true;
	}
	var msg = "${msg}";	   
	if(msg!=null&&msg!=""){
		var loginFailInfoObj = $("#loginFailInfo");
		if(msg == "1"){
			loginFailInfoObj.html("登录失败：<font color='#FF0000'>"+"用户不存在"+"</font>")
		}else if(msg == "2"){
			loginFailInfoObj.html("登录失败：<font color='#FF0000'>"+"密码错误"+"</font>")
		}else{
			loginFailInfoObj.html("登录失败：<font color='#FF0000'>"+"未知错误，请联系管理员"+"</font>")
		}
	}
</script>
</body>
</html>