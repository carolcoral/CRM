 <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%  
		String loginName = session.getAttribute("loginName").toString();
 %>
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
			        <div class="ranking fireRanking">
			            <div class="ranktitle">
			                <span><i class="icon fa fa-comments-o"></i>课程业绩</span>
			            </div>
		                <span class="indicates" style="width: 201px;margin: 10px auto;display: block;">
		                	<select id="selectmoth" style="width: 120px;">
		                		<option value="">请选择</option>
		                		<option value="1,2">AFP</option>
		                		<option value="3,4">CFP</option>
		                		<option value="6,7,8,9,19">基金</option>
		                		<option value="14">证券从业</option>
		                		<option value="5">银行从业</option>
		                		<option value="10">中级经济师</option>
		                		<option value="13">期货从业</option>
		                		<option value="11">会计从业</option>
		                		<option value="12">初级会计</option>
		                	</select>
	                    	<a class="btn filterder" ng-click="course('/report/queryPerformanceCourse.do','tag')">提交</a>  
		                </span>
			            <div class="rankContent">
			                <div class="rest">
			                    <div class="wrap">
				                    <div class="item" ng-repeat="infor in listcourse ">
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
</body>
</html>