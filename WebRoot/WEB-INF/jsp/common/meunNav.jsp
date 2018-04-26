<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%  
	String path = request.getContextPath();
	pageContext.setAttribute("PATH", request.getContextPath());
	String loginName = session.getAttribute("loginName").toString();
 %>
	<nav class="left">
	    <div class="logo">
	        <img src="../system/img/headelogo.jpg"/>
	    </div>
	    <c:forEach items="${menus}" var="item">
		    <div class="nav_meun">
		        <p class="biaoti">
		        	<span class="glyphicon glyphicon-cog" aria-hidden="true"></span>
		        		${item.menuname}
		        	<span class="glyphicon glyphicon-menu-down silde arrow" aria-hidden="true"></span>
		        	<span class="home glyphicon glyphicon-user" title="${item.menuname}"></span>
		        </p>
		        <ul class="bianji">
		        	<c:forEach items="${item.children}" var="list">
		          		<li><a href="./${list.menuurl}" class="active">${list.menuname}</a></li>
		          	</c:forEach>		          	         
		        </ul>
		    </div>
	    </c:forEach>	                
	</nav>	
	<section class="right">            
	    <div class="con">
	        <div class="basemian">
	        	<iframe frameborder="0"  width="100%" height="100%" name="iframe"></iframe>
			</div>        
	    </div>
	</section> 
	<!-- 登录 -->
	<div class="login">
	    <div class="login_con">
	    	<span class="name" id="${sessionScope.login_user.userid}">欢迎您：<b ><%=loginName%></b></span>
	        <p class="navbar-text navbar-right login_btn">
	        	
	            <a href="${PATH }/logout.do" target="_top" class="btn" style="background: #4c7cba;">退出</a>                     
	            <a href="javascript:;" target="right" class="btn" onclick="changePwd()">修改密码</a>
	        </p>
	    </div>
	</div> 
	<!-- 修改密码 -->
	<div id="updpwd">
		<div class="updpwd">
			<span class="close closebtn" onclick="wuorder.CloseDiv('updpwd','fade')" style="margin: 5px 10px  0 0;background: #5cb85c;">×</span>			
			
			<form role="form" class="ui_form">
			  <div class="form-group">
			    <label for="exampleInputPassword1">旧密码</label>
			    <input type="password" class="form-control"  id="oldPassword" name="oldPassword" placeholder="请输入旧密码">
			    <p class="errorinfo1" ></p>
			  </div>
			  <div class="form-group">
			    <label for="exampleInputEmail1">新密码</label>
			    <input type="password" class="form-control"  id="newPassword" name="newPassword" placeholder="请输入新密码">
			    <p class="errorinfo2"></p>
			  </div>			  
			  <div class="form-group">
			    <label for="exampleInputPassword1">确认密码</label>
			    <input type="password" class="form-control"  id="repeatPassword" name="repeatPassword" placeholder="确认密码">
			    <p class="errorinfo3"></p>
			  </div>
			  
			  <a href="javascript:;"  class="btn btn-success"  onclick="save()">保存</a>
			  
			</form>
		</div>
	</div>
<script type="text/javascript" src="../system/js/jquery-1.11.1.min.js"></script>
<script type="text/javascript">
	
	
	$("#oldPassword").blur(function(){
          var param=$("#oldPassword").val();  
          console.log(param);        
          $.ajax({
              url:"/crm/system/editPassword.do",
              type: 'POST',
              data:{oldPassword:param},                 
              success:function(data){
              console.log(data);
                if(data == 0){
					$('.errorinfo1').html("内容不能为空！");
				}else if(data == 3){
					$('.errorinfo1').html("旧密码不匹配！");
				}else if(data == 1){
					$('.errorinfo1').html("用户不存在！");
				}else {
					
				}
              }                 
          });
     });
	
	function savePwd(newpwd,oldpwd,repeatpwd,id) {
		var flag=true;
			
		if (oldpwd == "" || oldpwd == null) {
			$('.errorinfo1').html("旧密码不能为空");
			flag=false; 
		}
		if (newpwd == "" || newpwd == null) {
			$('.errorinfo2').html("新密码不能为空");
			flag=false;
		}
		if (repeatpwd == "" || repeatpwd == null) {		
			$('.errorinfo3').html("确认密码不能为空");			
			flag=false;
		}
		if (repeatpwd != newpwd) {
			$('.errorinfo3').html("两次密码不一致");
			flag=false;
		}	
		 console.log("两次密码不一致");
		return flag;
		if(flag){
				$.ajax({
				url: '/crm/system/editPassword.do',
				type: 'POST',
				cache: false,
				data: {
					newPassword:newPassword,
					oldPassword:oldPassword,
					userid:id
				},				
				success: function(data) {			
					if(data == 0){
						$('#errorinfo').html("内容不能为空！");
					}else if(data == 3){
						$('#errorinfo').html("旧密码不匹配！");
					}else if(data == 1){
						$('#errorinfo').html("用户不存在！");
					}else {
						$('#errorinfo').html("修改成功！");
					}
					
				},
				error: function(){
					
				}
			})	 
		}else{
		
		}
		
	}

function save() {
	var newPassword = $("#newPassword").val().trim();
	var oldPassword = $("#oldPassword").val();
	var repeatPassword = $("#repeatPassword").val();
	var nameid = $('.name').attr('id');
	console.log("222");
	savePwd(newPassword,oldPassword,repeatPassword,nameid);
}
</script>
