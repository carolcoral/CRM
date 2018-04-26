<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
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
	<link rel="stylesheet" type="text/css" href="../common/css/jquery.datetimepicker.css" />
	<link rel="stylesheet" type="text/css" href="../operate/css/resourcesMang.css" />
	<title>转移记录</title>
</head>
<body>
	<input class="userid" type="hidden" value="${sessionScope.login_user.userid}" id="userid" />
	<input class="roleid" type="hidden" value="${sessionScope.login_user.roleid}" id="roleid" />
	<input class="deptid" type="hidden" value="${sessionScope.login_user.deptid}" id="deptid" />
	<div class="meun-btn">
       	<ul class="nav navbar-nav">
       		<li onclick="wuorder.ShowDiv('filter','fade')">
				<a href="javascript:;" class="create"><span class="glyphicon glyphicon-sort-by-attributes"></span>筛选</a>
			</li>										
			<li onclick="excelExport()">
				<a href="javascript:;" class="create"><span class="glyphicon glyphicon-level-up"></span>导出</a>
			</li>
			<form class="navbar-form navbar-left" role="search">
				<div class="form-group">
					<input type="text" name="phone" id="phone" class="form-control check_tiaoj" placeholder="输入手机/座机搜索" ng-model="query">
				</div>
				<a href="javascript:;"  class="btn" style="background: #4c7cba;" onclick="query()">查询</a>
			</form>
		</ul>
	</div>
    <div class="bs-example table-responsive" data-example-id="simple-table"> 
     	<table class="table table-bordered table-hover table-striped" id="transferTable">                    
               <tr class="henglan">
                   <td class="text-primary">序号</td>
                   <td class="text-primary">转移时间</td>
                   <td class="text-primary">操作人</td>
                   <td class="text-primary">手机号</td>
                   <td class="text-primary">座机</td>
                   <td class="text-primary">转移前归属者</td>
                   <td class="text-primary">转移后归属者</td>
                   <td class="text-primary">销售界面资源状态</td>
                   <td class="text-primary">转移前资源等级</td>
                   <td class="text-primary">转移后资源等级</td>
               </tr> 
               <!-- <tr ng-repeat="infor in list | filter:query | orderBy:col:desc track by $index" data-id="{{infor.userid}}">
                   <td ng-bind="{{(currentPage-1)*pageSize+($index+1)}}"></td>
                   <td ng-bind="infor.resourceName"></td>
                   <td ng-bind="infor.phone"></td>
                   <td ng-bind="infor.sourceName"></td>
                   <td ng-bind="infor.belongName"></td>
                   <td ng-bind="infor.state"></td>
               </tr> -->
		</table>
        <!-- 分页 -->
	 	<!-- <nav>
	      <ul class="pagination">
	          <li ng-class="{true:'disabled'}[currentPage==1]"><a href="javascript:void(0);" ng-click="firstPage()">首页</a></li>
	          <li ng-class="{true:'active'}[currentPage==page]" ng-repeat="page in pages"><a href="javascript:void(0);" ng-click="loadPage(page)">{{ page }}</a></li>
	          <li ng-class="{true:'disabled'}[currentPage==pageTotal]"><a href="javascript:void(0);" ng-click="lastPage()">尾页</a></li>
	      </ul>
	      <span style="vertical-align: 30px;">&nbsp;&nbsp;共：{{count}} 条</span>
	      <span style="vertical-align: 30px;">&nbsp;&nbsp;共：{{pageTotal}} 页</span>
	  	</nav> -->
	  	<div id="pagination"></div>
    </div>
    <!-- 筛选 -->
	<div id="filter"  class="ui-wrap">
		<div class="addnew-wrap">
			<h2 class="wrap-tit"><b class="addnewTit">学员管理_筛选</b><span class="close closebtn"  onclick="wuorder.CloseDiv('filter','fade')">×</span></h2>
			
			<form class="form-horizontal add_form" id="excel">
				<div class="form-group">
					<label for="inputEmail3" class="col-md-3 control-label">转移时间</label>
					<div class="col-md-9">
						<input type="text" value="" id="createStarttime" placeholder="开始时间" name="createStarttime" class="chioce_time">
							<span class="until">-</span>
						<input type="text" value="" id="createEndtime" placeholder="结束时间" name="createEndtime"  class="chioce_time">						
					</div>
				</div>				
				<div class="form-group">
					<label for="inputPassword3" class="col-md-3 control-label">操作人</label>
					<div class="col-md-9">
						<select class="form-control" id="sourceName" name="sourceName">
							<option value="">请选择</option>
						</select>
					</div>
				</div>
				
				<div class="form-group">
					<div class="col-md-offset-4 col-md-6">
						<a href="javascript:;" class="btn addsave" onclick="filter()">筛选</a>						
						<a href="javascript:;"  class="btn"  onclick="clearFilter()" style="background: #4c7cba;">取消</a>
					</div>
				</div>
			</form>
		</div>
	</div>
<script type="text/javascript" src="../common/js/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="../common/js/angular.min.js"></script>
<script type="text/javascript" src="../common/js/jquery.datetimepicker.js"></script>
<script type="text/javascript" src="../common/js/winTip.js"></script>
<script type="text/javascript" src="../common/js/boot.js"></script>
<script type="text/javascript" src="../system/js/login.js"></script>
<script type="text/javascript" src="../common/js/js-extend.js"></script>
<script type="text/javascript" src="../common/js/ajaxPage.js"></script>	
<script type="text/javascript" src="../operate/js/transfer.js"></script>
</body>
</html>