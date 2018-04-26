 <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta content="yes" name="apple-mobile-web-app-capable">
    <meta content="black" name="apple-mobile-web-app-status-bar-style">
    <meta content="telephone=no" name="format-detection">
    <meta content="email=no" name="format-detection">
    <meta content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0,user-scalable=no" name="viewport" id="viewport" />
	<meta name="msapplication-tap-highlight" content="no" />
	<meta name="robots" content="noindex" />
	<meta http-equiv="X-UA-Compatible" content="IE=Edge,chrome=1" />
	<meta name="renderer" content="webkit" />
	<link rel="stylesheet" type="text/css" href="../common/css/bootstrap.min.css" />
	<link rel="stylesheet" type="text/css" href="../common/css/common.css" />
	<link rel="stylesheet" type="text/css" href="../common/css/jquery.datetimepicker.css" />
	<link rel="stylesheet" type="text/css" href="../report/css/userCount.css" />
	<title>业绩统计</title>	
</head>
<body ng-app="resource" ng-controller="resourceCtrl">
	<input class="userid" type="hidden" value="${sessionScope.login_user.userid}" id="userid" />
	<input class="roleid" type="hidden" value="${sessionScope.login_user.roleid}" id="roleid" />
	<input class="deptid" type="hidden" value="${sessionScope.login_user.deptid}" id="deptid" />
	<div class="reportWrap">
		<div class="list">
			<div id="findTeacher">
			    <!-- 排行详细 -->
			    <div id="ThreeRanking">
			        <div class="ranking manyRanking">
			            <div class="ranktitle">
			                <span><i class="icon fa fa-tv"></i>非AC本月业绩</span>
			            </div>
			            <div class="rankContent">
			                <div class="rest">
			                    <div class="wrap">
				                    <div class="item" ng-repeat="infor in listtodaymothnotAC ">
					                   <span class="sort" ng-bind="($index+1)"></span>
					                   <div class="favicon">
					                       <a href="javascriot:;"  class="avatar">
					                           <span class="glyphicon glyphicon-user userrest"></span>
					                       </a>
					                   </div>
					                   <div class="info">
					                       <div class="row2">
					                           <div class="wrap">
					                               <a href="javascript:;" ng-bind="infor.username"></a>
					                               <span class="index" ng-bind="infor.sumMoney"></span>
					                           </div>
					                       </div>
					                   </div>
						            </div>
			                    </div>
			                </div>
			            </div>
			        </div>
			        
			    </div>
			</div>
		</div>
	</div>	
<script type="text/javascript" src="../common/js/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="../common/js/angular.min.js"></script>
<script type="text/javascript" src="../common/js/jquery.datetimepicker.js"></script>
<script type="text/javascript" src="../report/js/performance.js"></script>
<script src="http://res.wx.qq.com/open/js/jweixin-1.1.0.js"></script>

<script type=""text/javascript"">
           function onBridgeReady(){
WeixinJSBridge.call('hideOptionMenu');
}

if (typeof WeixinJSBridge == "undefined"){
if( document.addEventListener ){
document.addEventListener('WeixinJSBridgeReady', onBridgeReady, false);
}else if (document.attachEvent){
document.attachEvent('WeixinJSBridgeReady', onBridgeReady); 
document.attachEvent('onWeixinJSBridgeReady', onBridgeReady);
}
}else{
onBridgeReady();
}
        </script>
</body>
</html>