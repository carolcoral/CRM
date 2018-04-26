 <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

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
	<link rel="stylesheet" type="text/css" href="../common/css/common.css" />
	<link rel="stylesheet" type="text/css" href="../common/css/jquery.datetimepicker.css" />	
	<title>金考注册用户</title>
	<style>
		.caidan {
			margin: 20px 20%;
		}
		.chioce_time {
		    width: 120px;
		    height: 34px;
		    border: 1px solid #ccc;
		    border-radius: 4px;
		    padding: 0 12px;
		    -moz-box-sizing: border-box;  
		    -webkit-box-sizing: border-box; 
		    -o-box-sizing: border-box; 
		    -ms-box-sizing: border-box; 
		    box-sizing: border-box;
		}
	</style>
</head>
<body style="height:auto;">
	<div class="content">
		<div class="caidan">
			<form action="/user/jinkaouser.do" id="form">
				<input type="text" value="" id="startTime" placeholder="开始时间" name="startTime" class="chioce_time">
				<span class="until">-</span>
				<input type="text" value="" id="endTime" placeholder="结束时间" name="endTime" class="chioce_time">
				<input type="submit" value ="提交" class="btn filterder">
					<font>共有注册用户:${list.size() }</font>	
			</form>		
				
		</div> 
		<div class="table-responsive" data-example-id="simple-table">  
			<table class="table table-bordered table-hover table-striped able-condensed" style="width:80%; margin:0 auto;">
				<tr>
				    <td>昵称</td>					
					<td>姓名</td>
					<td>手机号</td>
					<td>注册时间</td>
					<td>终端</td>
					<td>ip</td>
	 `  			</tr>
				<c:forEach items="${list}" var="item">
				<tr>
				    <td>${item.address}</td>				
					<td>${item.resourceName}</td>
					<td>${item.phone}</td>
					<td>${item.createStartTime}</td>
					<td>${item.belongName}</td>  
					<td>${item.courseName}</td> 
				</tr>
				</c:forEach>
			</table>
		</div> 
<script type="text/javascript" src="../common/js/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="../common/js/jquery.datetimepicker.js"></script>
<script type="text/javascript">
/* 初始化时间插件*/
$('#startTime').datetimepicker({
	lang: 'ch',
	format:"Y-m-d", //格式化日期
	validateOnBlur:false //删除选中的时间
});
$('#endTime').datetimepicker({
	lang: 'ch',
	format:"Y-m-d", //格式化日期
	validateOnBlur:false //删除选中的时间
});

function timequery(){
	$('#form').submit();
}

//定时检查页面长度
function reinitIframe() {          
    var iframe = $(window.parent.document).find("#iframepage");          
    try {               
           var bHeight =document.body.scrollHeight;               
           var dHeight =document.documentElement.scrollHeight;              
           var height = Math.max(bHeight, dHeight);
           iframe.height(bHeight);
       } catch (ex) { }       
}       
window.setInterval("reinitIframe()", 200);
</script>
</body>
</html>