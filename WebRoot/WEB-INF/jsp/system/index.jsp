 <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	pageContext.setAttribute("PATH", request.getContextPath());
			
%>
<%  
		String loginName = session.getAttribute("loginName").toString();
		String ischange = session.getAttribute("ischange").toString();
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
	<link rel="stylesheet" type="text/css" href="${PATH }/common/css/common.css" />
	<link rel="stylesheet" type="text/css" href="${PATH }/common/css/winTip.css" />
	<title>首页</title>
	<script type="text/javascript">
		
	</script>	
</head>
<body>
	<nav class="left">
	 	<div class="logo">
	        <img src="${PATH }/system/img/headelogo.jpg"/>
	    </div>
	  <div class="scroll">
		    <c:forEach items="${menus}" var="item">
			    <div class="nav_meun">
			        <p class="biaoti">
			        	<span class="${item.menuimgurl}" aria-hidden="true" style="margin-right: 8px;"></span>
			        		${item.menuname}
			        	<span class="glyphicon glyphicon-menu-down silde arrow" aria-hidden="true"></span>
			        	<span class="home glyphicon glyphicon-user" title="${item.menuname}"></span>
			        </p>
			        <ul class="bianji">
			        	<c:forEach items="${item.children}" var="list">
			          		<li><a href="./${list.menuurl}" target="iframe">${list.menuname}</a></li>
			          	</c:forEach>		          	         
			        </ul>		       
			    </div>
		    </c:forEach>	
	  </div>
	                    
	</nav>
	<!-- 登录 -->
	<div class="login">
	    <div class="login_con">
	    	<ul class="heademenu">
	    		<li>
	    			<span class="glyphicon glyphicon-user name" aria-hidden="true"></span>
	    			<span class="name username" id="${sessionScope.login_user.userid}">欢迎您：<b><%=loginName%></b><b id="ischange" style="display:none"><%=ischange%></b></span>
	    		</li>
	    		<li>
	    			<span class="glyphicon glyphicon-paperclip name" aria-hidden="true"></span>
	    			<span class="name"><b class="space">今日备忘录：</b><b id="target" class="space"></b></span>	    			
	            	<span class="targetbtn" id="addTarget" onclick="addToday()">添加</span>
	            	<span class="targetbtn" id="chTarget" onclick="chToday()" >修改</span>
	    		</li>	    		
	    	</ul>
	    	<!-- 消息滚动 -->
	    	<div class="gonggao">	    		
	    		<div class="container">					 
					  <div class="marquee">
						 <ul class="marquee-content-items">
						    <li><marquee id="tishi"></marquee></li>						   			     
						 </ul>
					  </div>
				</div>
	    	</div>
	        <p class="navbar-text login_btn">	        	
	            <a href="javascript:;" class="btn btn-primary" style="background-color: #337ab7;" onclick="wuorder.ShowDiv('uotTip','fade')">退出</a>                     
	            <a href="javascript:;" class="btn btn-danger" onclick="wuorder.ShowDiv('updpwd','fade')">修改密码</a>
	        </p>
	    </div>
	</div>	
	<section class="right">            
	    <div class="con">
	        <div class="basemian">        		
	        	<iframe name="iframe" id="iframepage" frameBorder=0 scrolling=no width="100%" height="1000" allowTransparency="true"></iframe>
			</div>        
	    </div>
	</section> 
	 
	<!-- 修改密码 -->
	<div id="updpwd">
		<div class="updpwd">
			<span class="close closebtn" onclick="wuorder.CloseDiv('updpwd','fade')">×</span>			
			<form role="form" class="ui_form">
			  <div class="form-group">
			    <label for="exampleInputPassword1">旧密码</label>
			    <input type="password" class="form-control"  id="oldPassword" name="oldPassword" placeholder="请输入旧密码">
			    <p class="error oldPasswordError" ></p>
			  </div>
			  <div class="form-group">
			    <label for="exampleInputEmail1">新密码</label>
			    <input type="password" class="form-control"  id="newPassword" name="newPassword" placeholder="请输入新密码">
			    <p class="error newPasswordError"></p>
			  </div>			  
			  <div class="form-group">
			    <label for="exampleInputPassword1">确认密码</label>
			    <input type="password" class="form-control"  id="repeatPassword" name="repeatPassword" placeholder="确认密码">
			    <p class="error repeatPasswordError"></p>
			  </div>
			  <a href="javascript:;"  class="btn"  onclick="savepwd()">保存</a>			  
			</form>
		</div>
	</div>
	<!-- 提示 -->
    <div class="themisWrap" style="display:none;" id="uotTip">
      <div class="themisGray"></div>
        <div class="themis" style="top:30%;">
           <h3 class="themistit"><span class="themisTipPic" style="float: left;padding-top: 17px;padding-left: 10px;margin-right: 10px;"><img class="pic" src="./system/img/tishi.png" height="25" width="25" alt="" /></span>友情提示</h3>
           <div class="themispay">
                <div class="themistip" style="margin-bottom: 20px; color:red; font-size:14px;">确定要退出吗!</div>
                <button onclick="wuorder.CloseDiv('uotTip','fade')" class="btn btn-default navbar-right"  style="margin-left:10px; margin-right: 0px;">取消</button>
                <a href="./logout.do" class="btn btn-primary navbar-right">确定</a> 
           </div>
        </div>
      </div>
      <!-- 今日目标 -->
    <div class="themisWrap" style="display:none;" id="todayNote">
      <div class="themisGray"></div>
        <div class="themis" style="top:30%;">
           <h3 class="themistit"><span class="themisTipPic" style="float: left;padding-top: 17px;padding-left: 10px;margin-right: 10px;"><img class="pic" src="./system/img/tishi.png" height="25" width="25" alt="" /></span><b id="todayTip">友情提示</b></h3>
           <div class="themispay">
                <div class="themistip" style="margin-bottom: 20px; color:red; font-size:14px;">
                	<input type="text" class="form-control" id="todayVal"> 
                	<p class="help-block" id="todayValError"></p>
                </div>
                <div class="form-group">
					<div class="col-md-offset-4 col-md-6">
						<a href="javascript:;" class="btn" onclick="saveAdd()">保 存</a>
						<a href="javascript:;"  class="btn"  style="background: #4c7cba;" onclick="wuorder.CloseDiv('todayNote','fade')">取消</a>
					</div>
				</div>
           </div>
        </div>
      </div>
       <!-- 消息弹窗 -->        
       <div id="message">
       		<span class="closemess" onclick="wuorder.CloseDiv('message','fade')">×</span>
       		<h2 class="mess_tip">消息提醒</h2>
          	<p id="messcontent">XX时间，有新的“XX”渠道资源xx条，请回访！</p>
       </div>
<script type="text/javascript" src="${PATH }/common/js/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="${PATH }/common/js/reconnecting-websocket.js"></script>
<script type="text/javascript" src="${PATH }/common/js/boot.js"></script>
<script type="text/javascript" src="${PATH }/common/js/echarts.min.js"></script>
<script type="text/javascript" src="${PATH }/system/js/login.js"></script>
<script type="text/javascript" src="${PATH }/system/js/index.js"></script>
</body>
</html>