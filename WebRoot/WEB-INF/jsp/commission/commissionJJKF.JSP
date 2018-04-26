<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
pageContext.setAttribute("PATH", request.getContextPath());
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
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
	<link rel="stylesheet" type="text/css" href="../common/css/common.css" />
	<link rel="stylesheet" type="text/css" href="../common/css/winTip.css" />	
	<link rel="stylesheet" type="text/css" href="../system/css/userMang.css" />
	<title>提成总表(基金-客服)</title>
</head>
<body ng-app="app" ng-controller="listCtrl">
	
	<div class="meun-btn">
       	<ul class="nav navbar-nav">
			<form class="navbar-form navbar-left" role="search">
				<div class="form-group">
					<input type="text" value="" id="arriveStartTime" placeholder="开始时间" name="arriveStartTime" class="chioce_time">
					<span class="until">-</span>
					<input type="text" value="" id="arriveEndTime" placeholder="结束时间" name="arriveEndTime" class="chioce_time">
				</div>
				<a href="javascript:;"  class="btn" style="background: #4c7cba;">查询</a>
			</form>
		</ul>
	</div>
    <div class="bs-example table-responsive" data-example-id="simple-table"> 
    	<div class="coursePanel">
		  <span class="glyphicon glyphicon-th-list"></span>提成发放【基金-客服】	  
		</div>              
     	<table class="table table-bordered table-hover table-striped">                    
               <tr class="henglan">
                   <td class="text-primary"><input type="checkbox" class="all" /></td>
                   <td class="text-primary">市场专员</td>
                   <td class="text-primary">2%基础金额</td>
                   <td class="text-primary">2%总金额</td>
                   <td class="text-primary">3%基础金额</td>
                   <td class="text-primary">3%总金额</td>
                   <td class="text-primary">4%基础金额</td>
                   <td class="text-primary">4%总金额</td>
                   <td class="text-primary">5%基础金额</td>
                   <td class="text-primary">5%总金额</td>
                   <td class="text-primary">7%基础金额</td>
                   <td class="text-primary">7%总金额</td>
                   <td class="text-primary">8%基础金额</td>
                   <td class="text-primary">8%总金额</td>
                   <td class="text-primary">9%基础金额</td>
                   <td class="text-primary">9%总金额</td>
                   <td class="text-primary">10%基础金额</td>
                   <td class="text-primary">10%总金额</td>
                   <td class="text-primary">12%基础金额</td>
                   <td class="text-primary">12%总金额</td>
                   <td class="text-primary">15%基础金额</td>
                   <td class="text-primary">15%总金额</td>
                   <td class="text-primary">20%基础金额</td>
                   <td class="text-primary">20%总金额</td>
                   <td class="text-primary">总销售额</td>
                   <td class="text-primary">应发总金额</td>
                   <td class="text-primary">实发总金额</td>
                   <td class="text-primary">备注</td>
                   
               </tr> 
               <tr ng-repeat="infor in list | filter:query | orderBy:col:desc track by $index" data-id="{{infor.userid}}">
                   <td><input type="checkbox" class="{{infor.userid}}" /></td>
                   <td ng-bind="{{(currentPage-1)*pageSize+($index+1)}}"></td>
                   <td ng-bind="infor.username" class="username"></td>
                   <td ng-bind="infor.email"></td>
                   <td ng-bind="infor.password"></td>
                   <td ng-bind="infor.phone"></td>
                   <td ng-bind="infor.mobile"></td>
                   <td ng-bind="infor.deptgroup"></td>
                   <td ng-bind="infor.deptname" data-id="{{infor.deptid}}"></td>
                   <td ng-bind="infor.rolename"></td>
                   <td ng-bind="infor.userphoto"></td>
                   <td ng-bind="sex(infor.sex)"></td>
                   <td ng-bind="infor.create_time"></td>
                   <td ng-bind="state(infor.state)" ng-style="setColor(infor.state)"></td>
                   <td ng-bind="infor.note" data-toggle="tooltip" data-placement="bottom" title="{{infor.note}}" style="overflow:hidden;white-space:nowrap;text-overflow:ellipsis;max-width: 250px;"></td>
               </tr>
		</table>
        <!-- 分页 -->
	 	<nav>
	      <ul class="pagination">
	          <li ng-class="{true:'disabled'}[currentPage==1]"><a href="javascript:void(0);" ng-click="firstPage()">首页</a></li>
	          <li ng-class="{true:'active'}[currentPage==page]" ng-repeat="page in pages"><a href="javascript:void(0);" ng-click="loadPage(page)">{{ page }}</a></li>
	          <li ng-class="{true:'disabled'}[currentPage==pageTotal]"><a href="javascript:void(0);" ng-click="lastPage()">尾页</a></li>
	      </ul>
	      <span style="vertical-align: 30px;">&nbsp;&nbsp;共：{{count}} 条</span>
	      <span style="vertical-align: 30px;">&nbsp;&nbsp;共：{{pageTotal}} 页</span>
	  	</nav>
    </div>
	
	<!-- 提示 -->
    <div class="themisWrap" style="display:none;" >
      <div class="themisGray"></div>
        <div class="themis" style="top:30%;">
           <h3 class="themistit"><span class="themisTipPic" style="float: left;padding-top: 17px;padding-left: 10px;margin-right: 10px;"><img class="pic" src="../system/img/tishi.png" height="25" width="25" alt="" /></span>友情提示</h3>
           <div class="themispay">
                <div class="themistip" style="margin-bottom: 20px; color:red; font-size:14px;">确定删除这些信息吗!</div>
                <button class="btn navbar-right" id="quxiao" >取消</button>
                <button class="btn navbar-right" id="queding" style="background: #4c7cba;" onclick="del()">确定</button>                    
                                    
           </div>
        </div>
      </div>
<script type="text/javascript" src="../common/js/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="../common/js/angular.min.js"></script>
<script type="text/javascript" src="../common/js/winTip.js"></script>
<script type="text/javascript" src="../common/js/boot.js"></script>
<script type="text/javascript" src="../system/js/login.js"></script>
<script type="text/javascript" src="../system/js/userMang.js"></script>
</body>
</html>