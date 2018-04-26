<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%  
		String loginName = session.getAttribute("loginName").toString();
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
	<link rel="stylesheet" type="text/css" href="../common/css/page.css" />
	<title>销售资源管理</title>
	<style type="text/css">
		.table>tbody>tr>td, .table>tbody>tr>th{
			padding:4px;
			vertical-align: middle;
		}
	</style>
</head>
<body ng-app="resource" ng-controller="resourceCtrl">
	<input class="userid" type="hidden" value="${sessionScope.login_user.userid}" id="userid" />
	<input class="roleid" type="hidden" value="${sessionScope.login_user.roleid}" id="roleid" />
	<input class="deptid" type="hidden" value="${sessionScope.login_user.deptid}" id="deptid" />
	<input id="backcurrent" type="hidden" value=""  data-id=""  data-visit="" data-todayvisit=""/>
	<!-- <input id="todaybackcurrent" type="hidden" data-visit=""/> -->
	<p class="shuiyin"><%=loginName%></p>
	<p class="shuiyin suiyintwo"><%=loginName%></p>
	<p class="shuiyin suiyinthree"><%=loginName%></p>
	<nav class="navbar navbar-default">
		<div class="container-fluid">			
			<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav">
					<li onclick="wuorder.ShowDiv('addresource','fade')">
						<a href="javascript:;" class="create"><span class="glyphicon glyphicon-stats"></span>新建</a>
					</li>
					<!-- <li onclick="changeResource()">
						<a href="javascript:;" class="create"><span class="glyphicon glyphicon-stats"></span>修改</a>
					</li>	 -->			
					<li onclick="wuorder.ShowDiv('filter','fade')">
						<a href="javascript:;" class="create"><span class="glyphicon glyphicon-sort-by-attributes"></span>筛选</a>
					</li>					
					<li onclick="checkassignall('#distributeTransfer')" id="zgtransfer" style="display:none">
						<a href="javascript:;" class="create"><span class="glyphicon glyphicon-retweet"></span>转移</a>
					</li>
					<!-- <li onclick="wuorder.ShowDiv('batchImport','fade')">
						<a href="javascript:;" class="create"><span class="glyphicon glyphicon-paste"></span>批量导入</a>
					</li> -->
					<li onclick="filterTransfer()">
						<a href="javascript:;" class="create"><span class="glyphicon glyphicon-sort-by-attributes"></span>筛选转移</a>
					</li>
					<form class="navbar-form navbar-left" role="search" >
						<div class="form-group">
							<input type="text" name="mobile" id="searchmobile" class="form-control check_tiaoj" placeholder="姓名/手机搜索">
						</div>
						<a class="btn" href="javascript:void(0);" onclick="phonesearch();"><span class="glyphicon glyphicon-search" aria-hidden="true"></span></a>
					</form>
				</ul>
																			
				<div class="setcolorbtn">
					<a href="javascript:;"><span class="star glyphicon glyphicon-star" style="color:#fb8c8c" onclick="setColor(this,'resourceTable',0)"></span></a>					
					<div class="delcolor" style="color:rgba(255, 255, 255, 1)" onclick="setColor(this,'resourceTable',1)"><a href="javascript:;" style="background:#4c7cba">取消颜色</a></div>
				</div>
				<div class="setcolorbtn">
					<a href="javascript:;"><span class="star glyphicon glyphicon-star" style="color:#00FF33" onclick="setWeixinColor(this,'resourceTable',1)"></span><font style="color:#CC3300">微信标记</font></a>	
					<div class="delcolor" style="color:rgba(255, 255, 255, 1)" onclick="setWeixinColor(this,'resourceTable',0)"><a href="javascript:;" style="background:#4c7cba">取消微信标记</a></div>				
				</div>	
			</div>
			<!-- /.navbar-collapse -->
		</div>
		<!-- /.container-fluid -->
	</nav>
	<nav class="navbar navbar-default" style="margin-bottom: 0;border-bottom: none;min-height: 30px;">
		<div class="container-fluid">
			<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
				<p class="navbar-text navbar-changes" >
					总量资源：<span class="text-danger" ng-bind="resourceList.total"></span>
				</p>
				<p class="navbar-text navbar-changes salefilter" onclick="xingjiFilter('1')">
					星级：<span ng-bind="resourceList.xjkhcount"></span>
				</p>
				<p class="navbar-text navbar-changes salefilter" onclick="levelFilter('A')">
					A类：<span ng-bind="resourceList.acount"></span>
				</p>
				<p class="navbar-text navbar-changes salefilter" onclick="levelFilter('B')">
					B类：<span ng-bind="resourceList.bcount"></span>
				</p>									
				<p class="navbar-text navbar-changes salefilter" onclick="levelFilter('C')">
					C类：<span ng-bind="resourceList.ccount"></span>
				</p>
				<p class="navbar-text navbar-changes salefilter" onclick="levelFilter('D')">
					D类：<span ng-bind="resourceList.dcount"></span>
				</p>
				<p class="navbar-text navbar-changes salefilter" onclick="stateFilter('1')">
					未处理资源：<span ng-bind="resourceList.wclcount"></span>
				</p>	
				<p class="navbar-text navbar-changes salefilter" onclick="sourceFilter('0')">
					自建：<span ng-bind="resourceList.zijiancount"></span>
				</p>	
				<p class="navbar-text navbar-changes salefilter" onclick="weixinFilter('1')">
					已加微信：<span ng-bind="resourceList.weixincount"></span>
				</p>
			</div>
		</div>
	</nav>
	<nav class="navbar navbar-default" style="border-top: none;min-height: 30px !important;margin-bottom:0;border-bottom: none;">
		<div class="container-fluid">
			<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
			    <p class="navbar-text navbar-changes">
					今日录入资源量：<span class="text-danger" ng-bind="resourceListtoday.todaytotal"></span>
				</p>			
				<p class="navbar-text navbar-changes salefilter" onclick="todayFilter('7')">
					在线咨询：<span ng-bind="resourceListtoday.todayzxzxcount"></span>
				</p>
				<p class="navbar-text navbar-changes salefilter" onclick="todayFilter('4')"> 
					电话咨询：<span ng-bind="resourceListtoday.todaydhzxcount"></span>
				</p>
				<p class="navbar-text navbar-changes salefilter" onclick="todayFilter('1')">
					课程注册：<span ng-bind="resourceListtoday.todaykczccount"></span>
				</p>
				<p class="navbar-text navbar-changes salefilter" onclick="todayFilter('2')">
					在线注册：<span ng-bind="resourceListtoday.todayzxzccount"></span>
				</p>
				<p class="navbar-text navbar-changes salefilter" onclick="todayFilter('3')">
					app下载注册：<span ng-bind="resourceListtoday.todayappcount"></span>
				</p>
				<p class="navbar-text navbar-changes salefilter" onclick="todayFilter('6')">
					线上渠道：<span ng-bind="resourceListtoday.todayxsqdcount"></span>
				</p>
				<p class="navbar-text navbar-changes salefilter" onclick="todayFilter('5')">
					金考网注册用户：<span ng-bind="resourceListtoday.todayjkwzccount"></span>
				</p>
				<p class="navbar-text navbar-changes salefilter" onclick="todayFilter('8')">
					大库资源：<span ng-bind="resourceListtoday.todaydkzycount"></span>
				</p>
				<p class="navbar-text navbar-changes salefilter" onclick="todayFilter('9')">
					在线购买：<span ng-bind="resourceListtoday.todayzxgmcount"></span>
				</p>
				<p class="navbar-text navbar-changes salefilter" onclick="todayFilter('10')">
					继续教育：<span ng-bind="resourceListtoday.todayjxjycount"></span>
				</p>		
				<p class="navbar-text navbar-changes salefilter" onclick="todayFilter('0')">
					自建：<span ng-bind="resourceListtoday.todayzijian"></span>
				</p>
				<p class="navbar-text navbar-changes salefilter" onclick="visitFilter()">
					今日回访：<span ng-bind="listtodayvirecordsize"></span>
				</p>
			</div>
			
		</div>
	</nav>
	<nav class="navbar navbar-default" style="margin-bottom: 0;border-bottom: none;min-height: 30px;">
		<div class="container-fluid">
			<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
				<p class="navbar-text filtercolr">
					筛选条件
				</p>
				<p class="navbar-text filtercolr">
					创建日期：<span class="text-danger cjfilter"></span>
				</p>
				<p class="navbar-text filtercolr">
					资源属性：<span class="text-danger sxfilter"></span>
				</p>
				<p class="navbar-text filtercolr">
					渠道：<span class="text-danger qdfilter"></span>
				</p>									
				<p class="navbar-text filtercolr">
					归属者：<span class="text-danger gszfilter"></span>
				</p>
				<p class="navbar-text filtercolr">
					地域：<span class="text-danger dyfilter"></span>
				</p>
				<p class="navbar-text filtercolr">
					姓名：<span class="text-danger xmfilter"></span>
				</p>
				<p class="navbar-text filtercolr">
					手机：<span class="text-danger sjfilter"></span>
				</p>
				<p class="navbar-text filtercolr">
					课程：<span class="text-danger ckfilter"></span>
				</p>
				<p class="navbar-text filtercolr">
					回访时间：<span class="text-danger sjfilter"></span>
				</p>
				<p class="navbar-text filtercolr">
					资源等级：<span class="text-danger djfilter"></span>
				</p>
				<p class="navbar-text filtercolr">
					回访次数：<span class="text-danger csfilter"></span>
				</p>
				<p class="navbar-text filtercolr">
					是否有星级客户：<span class="text-danger xjfilter"></span>
				</p>
				<a class="btn btn-info navbar-right info-set" href="javascript:;" style="background: #46b8da;margin-top: 8px;"><b id="zongs">当前共有资源：</b><span class="text-danger" id="filterTotal">0</span>条</a>				
			</div>
		</div>
	</nav>
	<div class="table-responsive" data-example-id="simple-table">
        <table class="table table-bordered table-hover table-striped able-condensed" id="resourceTable" style="width: 2100px;border:none;">              	                   
            <!-- <tr class="henglan">
				<th><input type="checkbox" class="all" /></th>
				<th class="serialnumber">序号</th>
				<th class="assignTime">分配日期</th>
				<th class="state">资源状态</th>
				<th class="resourceLevel">资源等级</th>
				<th class="firstVisitTime">最近回访时间</th>				
				<th class="source">渠道</th>
				<th class="address">地域</th>
				<th class="resourceName">姓名</th>
				<th class="phone">手机</th>
				<th class="courseName">课程</th>
				<th class="yunYingNote">运营备注</th>
				<th class="xiaoShouNote">销售备注</th>
					
            </tr> -->
        </table>
    </div>
	<!-- 分页 -->        
	<nav id="filterpagination">
	     <!-- <ul class="pagination" id="steppain">
	       	 <li id="firstPage"><a href="javascript:;">首页</a></li>
	         
	         <li id="lastPage"><a href="javascript:;">尾页</a></li>
	     </ul>
	     <span style="vertical-align: 30px;">&nbsp;&nbsp;共：<b id="count"></b> 条</span>
	    <span style="vertical-align: 30px;">&nbsp;&nbsp;共：<b id="pageTotal"></b> 页</span> -->
	</nav> 
    <!-- 资源管理_新建 -->
	<div id="addresource"  class="ui-wrap">
		<div class="addnew-wrap">
			<h2 class="wrap-tit"><b class="addnewTit">资源管理_新建</b><span class="close closebtn"  onclick="window.location.reload()">×</span></h2>
			
			<form class="form-horizontal add_form">
			    <div class="form-group">
			 		<label for="inputPassword3" class="col-md-3 control-label">创建者</label>
					<div class="col-md-9">
						<select class="form-control" id="createrName">
							<option value="0">自建</option>
							
						</select>
					</div>
				</div>
				<div class="form-group">					
					<label for="inputPassword3" class="col-md-3 control-label">渠道</label>
					<div class="col-md-9">						
						<select class="form-control" id="source">
							<option value="0" selected="selected">自建</option>
							<option value="2">在线注册</option>
							<option value="7">在线咨询</option>										
							<option value="4">电话咨询</option>
							<option value="3">app下载注册</option>
							<option value="1">课程注册</option>
							<option value="5">金考网注册用户</option>
							<option value="6">线上渠道</option>
						</select>
					</div>
				</div>
				<p class="help-block" id="courseMoneyError"></p>
				<div class="form-group">
					<label for="inputPassword3" class="col-md-3 control-label">地域</label>
					<div class="col-md-9">
						<select class="form-control" name="address" id="address">
							<option value="">请选择地域</option>
							<option value="北京">北京</option>
							<option value="上海">上海</option>
							<option value="广东">广东</option>
							<option value="天津">天津</option>
							<option value="河北">河北</option>
							<option value="内蒙古">内蒙古</option>
							<option value="吉林">吉林</option>
							<option value="辽宁">辽宁</option>
							<option value="黑龙江">黑龙江</option>
							<option value="福建">福建</option>
							<option value="安徽">安徽</option>
							<option value="山西">山西</option>
							<option value="重庆">重庆</option>
							<option value="江苏">江苏</option>
							<option value="江西">江西</option>
							<option value="山东">山东</option>
							<option value="浙江">浙江</option>
							<option value="河南">河南</option>
							<option value="湖北">湖北</option>
							<option value="湖南">湖南</option>
							<option value="海南">海南</option>
							<option value="广西">广西</option>
							<option value="贵州">贵州</option>
							<option value="四川">四川</option>
							<option value="西藏">西藏</option>
							<option value="云南">云南</option>
							<option value="甘肃">甘肃</option>
							<option value="宁夏">宁夏</option>
							<option value="青海">青海</option>
							<option value="陕西">陕西</option>
							<option value="新疆">新疆</option>							
						</select>
					</div>
				</div>
				<div class="form-group">
					<label for="inputPassword3" class="col-md-3 control-label">姓名</label>
					<div class="col-md-9">
						<input type="text" class="form-control" id="resourceName">
						<span class="help-block" id="resourceNameError"></span>
					</div>
				</div>
				<div class="form-group">
					<label for="inputPassword3" class="col-md-3 control-label">手机</label>
					<div class="col-md-9">
						<input type="text" class="form-control" id="phone">	
						<span class="help-block" id="phoneError"></span>					
					</div>
				</div>	
				<div class="form-group">
					<label for="inputPassword3" class="col-md-3 control-label">固定电话</label>
					<div class="col-md-9">
						<input type="text" class="form-control" id="tel">	
					</div>
				</div>							
				<div class="form-group">
					<label for="inputPassword3" class="col-md-3 control-label">课程</label>
					<div class="col-md-9">
						<select class="form-control" id="course">
							<option value="">请选择课程</option>
							<option ng-repeat="option in course" value="{{option.courseid}}">{{option.courseName}}</option>											
						</select>
					</div>
				</div>
				<div class="form-group">
					<label for="inputPassword3" class="col-md-3 control-label">资源等级</label>
					<div class="col-md-9">
						<select class="form-control" id="resourceLevel">
							<option value="">请选择资源等级</option>
							<option value="A">A</option>
							<option value="B">B</option>
							<option value="C">C</option>
							<option value="D">D</option>
						</select>
					</div>
				</div>
				<div class="form-group">
					<label for="inputPassword3" class="col-md-3 control-label">备注</label>
					<div class="col-md-9">
						<input type="text" class="form-control"  id="xiaoShouNote">							
						<span class="help-block" id="xiaoShouNoteError"></span>					
					</div>
				</div>
				<div class="form-group">
					<div class="col-md-offset-4 col-md-6">
						<a href="javascript:;" class="btn addsave" onclick="ckPhone(this)">保 存</a>						
						<!-- <a href="javascript:;"  class="btn"  onclick="quxiao()" style="background: #4c7cba;">取消</a> -->
					</div>
				</div>
			</form>
		</div>
	</div>
	<!-- 批量导入 -->
	<div id="batchImport"  class="ui-wrap">
		<div class="addnew-wrap">
			<h2 class="wrap-tit"><b class="addnewTit">资源管理_批量导入</b><span class="close closebtn"  onclick="wuorder.CloseDiv('batchImport','fade')">×</span></h2>
			
			<form class="form-horizontal add_form" id="uploadForm" enctype="multipart/form-data">				
				<div class="form-group">
						<label for="inputEmail3" class="col-md-4 control-label">请选择Excel文件</label>
						<div class="col-md-8">
							<span class="fileinput-button" >添加文件<input name="resourceFile"  id="fileinput-input" class="fileinput-input" type="file"></span>
							<input name="deptid" type="hidden" value="${sessionScope.login_user.deptid}">
						</div>
					</div>
					<p class="text-primary">当前添加的文件是：<span id="itemnum"></span></p>
					<div class="form-group">
					<div class="col-md-offset-4 col-md-6">
						<a href="javascript:;" class="btn" id="importExcel" onclick="importExcel(this)">开始导入</a>						
						<a href="javascript:;"  class="btn"  onclick="wuorder.CloseDiv('batchImport','fade')" style="background: #4c7cba;">取消</a>
					</div>
				</div>
			</form>
		</div>
	</div>
	<!-- 资源管理_筛选 -->
	<div id="filter"  class="ui-wrap">
		<div class="addnew-wrap">
			<h2 class="wrap-tit"><b class="addnewTit">资源管理_筛选</b><span class="close closebtn"  onclick="wuorder.CloseDiv('filter','fade')">×</span></h2>
			
			<form id="excelExport" class="form-horizontal add_form" action="/resource/excelExport.do" method="POST">
				<input class="deptid" type="hidden" value="${sessionScope.login_user.deptid}" id="deptid" name="deptid"/>
				<div class="form-group">
					<label for="inputEmail3" class="col-md-3 control-label">创建日期</label>
					<div class="col-md-9">
						<input type="text" value="" id="taskStartTime" placeholder="开始时间" name="createStartTime" class="chioce_time">
						<span class="until">-</span>
						<input type="text" value="" id="taskEndTime" placeholder="结束时间" name="createEndTime"  class="chioce_time">
					</div>
				</div>				
				<div class="form-group">
					<label for="inputPassword3" class="col-md-3 control-label" >资源属性</label>
					<div class="col-md-9">
						<select class="form-control" id="filter_state" name="state">
							<option value="">请选择属性</option>
							<option value="0">未分配</option>
							<option value="1">已分配</option>
							<option value="2">已处理</option>
						</select>
					</div>
				</div>													
				<div class="form-group">
					<label for="inputPassword3" class="col-md-3 control-label">渠道</label>
					<div class="col-md-9">
						<select class="form-control" name="source" id="filter_source" name="source">
							<option value="">请选择渠道</option>
							<option value="0">自建</option>
							<option value="2">在线注册</option>
							<option value="7">在线咨询</option>										
							<option value="4">电话咨询</option>
							<option value="1">课程注册</option>
							<option value="3">app下载注册</option>
							<option value="5">金考网注册用户</option>
							<option value="6">线上渠道</option>
							<option value="8">大库资源</option>
							<option value="9">在线购买</option>
							<option value="10">继续教育</option>
							<option value="11" id="gszy" style="display:none">公司资源</option>
						</select>
					</div>
				</div>
				<div class="form-group">
					<label for="inputPassword3" class="col-md-3 control-label">归属者</label>
					<div class="col-md-9">
						<select class="form-control" name="source" id="filter_belongName" name="belongName">
							<option value="">请选择归属者</option>							
						</select>
					</div>
				</div>
				<div class="form-group">
					<label for="inputPassword3" class="col-md-3 control-label">地域</label>
					<div class="col-md-9">
						<select class="form-control" id="filter_address" name="address">
							<option value="">请选择地域</option>
							<option value="北京">北京</option>
							<option value="上海">上海</option>
							<option value="广东">广东</option>
							<option value="天津">天津</option>
							<option value="河北">河北</option>
							<option value="内蒙古">内蒙古</option>
							<option value="吉林">吉林</option>
							<option value="辽宁">辽宁</option>
							<option value="黑龙江">黑龙江</option>
							<option value="福建">福建</option>
							<option value="安徽">安徽</option>
							<option value="山西">山西</option>
							<option value="重庆">重庆</option>
							<option value="江苏">江苏</option>
							<option value="江西">江西</option>
							<option value="山东">山东</option>
							<option value="浙江">浙江</option>
							<option value="河南">河南</option>
							<option value="湖北">湖北</option>
							<option value="湖南">湖南</option>
							<option value="海南">海南</option>
							<option value="广西">广西</option>
							<option value="贵州">贵州</option>
							<option value="四川">四川</option>
							<option value="西藏">西藏</option>
							<option value="云南">云南</option>
							<option value="甘肃">甘肃</option>
							<option value="宁夏">宁夏</option>
							<option value="青海">青海</option>
							<option value="陕西">陕西</option>
							<option value="新疆">新疆</option>
						</select>
					</div>
				</div>
				<div class="form-group">
					<label for="inputPassword3" class="col-md-3 control-label">姓名</label>
					<div class="col-md-9">
						<input type="text" class="form-control" id="filter_resourceName" name="resourceName">
					</div>
				</div>
				<div class="form-group">
					<label for="inputPassword3" class="col-md-3 control-label">手机</label>
					<div class="col-md-9">
						<input type="text" class="form-control" id="filter_phone" name="phone">
					</div>
				</div>
				<div class="form-group">
					<label for="inputPassword3" class="col-md-3 control-label">运营备注</label>
					<div class="col-md-9">
						<input type="text" class="form-control" id="filter_yunYingNote" name="yunYingNote">
					</div>
				</div>
				<div class="form-group">
					<label for="inputPassword3" class="col-md-3 control-label">回访记录</label>
					<div class="col-md-9">
						<input type="text" class="form-control" id="filter_visitRecord" name="visitRecord">
					</div>
				</div>
				<div class="form-group">
					<label for="inputPassword3" class="col-md-3 control-label">课程</label>
					<div class="col-md-9">
						<select class="form-control" id="filter_course" name="courseid">
							<option value="">请选择咨询课程</option>
							<!-- <option value="0">基金从业</option>
							<option value="1">AFP</option>
							<option value="2">CFP</option>
							<option value="3">中级经济师</option>
							<option value="4">期货从业</option>
							<option value="5">证券从业</option>
							<option value="6">银行初级</option>
							<option value="7">初级会计师</option>
							<option value="8">会计从业</option> -->
						</select>
					</div>
				</div>				
				<div class="form-group">
					<label for="inputEmail3" class="col-md-3 control-label">回访时间</label>
					<div class="col-md-9">
						<input type="text" value="" id="secondTime" placeholder="开始时间" name="visitStartTime" class="chioce_time">
						<span class="until">-</span>
						<input type="text" value="" id="thirdTime" placeholder="结束时间" name="visitEndTime" class="chioce_time">
					</div>
				</div>				
				<div class="form-group">
					<label for="inputEmail3" class="col-md-3 control-label">资源等级</label>
					<div class="col-md-9">
						<select class="form-control" id="filter_resourceLevel" name="resourceLevel">
						    <option value="">请选择资源等级</option> 
							<option value="A">A</option>
							<option value="B">B</option>
							<option value="C">C</option>
							<option value="D">D</option>
						</select>
					</div>
				</div>
				<div class="form-group">
					<label for="inputPassword3" class="col-md-3 control-label" >是否转移</label>
					<div class="col-md-9">
						<select class="form-control" id="filter_isZhuanyi" name="isZhuanyi">
							<option value="">请选择</option>
							<option value="0">否</option>
							<option value="1">是</option>
						</select>
					</div>
				</div>
				<div class="form-group">
					<label for="inputPassword3" class="col-md-3 control-label" >回访次数</label>
					<div class="col-md-9">
						<select class="form-control" id="filter_visitCount" name="visitCount">
							<option value="">请选择回访次数</option>
						    <option value="0">0次</option>
							<option value="1">1次</option>
							<option value="2">2次</option>
							<option value="3">3次</option>
							<option value="4">3次以上</option>							
						</select>
					</div>
				</div>	
				<div class="form-group">
					<label for="inputPassword3" class="col-md-3 control-label" >星级客户</label>
					<div class="col-md-9">
						<select class="form-control" id="filter_xjkhcount" name="xjkhcount">
							<option value="">请选择</option>
							<option value="1">有</option>
						</select>
					</div>
				</div>	
				<div class="form-group">
					<div class="col-md-offset-4 col-md-6">
						<a href="javascript:;" class="btn addsave" onclick="filter();">筛选</a>						
						<a href="javascript:;"  class="btn"  onclick="wuorder.CloseDiv('filter','fade')" style="background: #4c7cba;">取消</a>
					</div>
				</div>
			</form>
		</div>
	</div>	
	<!-- 分配和转移 -->
	<div id="distributeTransfer"  class="ui-wrap">
		<div class="addnew-wrap">
			<h2 class="wrap-tit"><b class="addnewTit">资源管理_转移</b><span class="close closebtn"  onclick="wuorder.CloseDiv('distributeTransfer','fade')">×</span></h2>
			
			<form class="form-horizontal add_form">
				<div class="form-group">
					<label for="inputPassword3" class="col-md-3 control-label">选择销售员</label>
					<div class="col-md-9">
						<select class="form-control" id="chooseSale">
							 <option value="">请选择销售员</option> 
						</select>
					</div>
				</div>
				<div class="form-group">
					<div class="col-md-offset-4 col-md-6">
						<a href="javascript:;" class="btn addsave" onclick="assignall()">确定</a>						
						<a href="javascript:;" class="btn" style="background: #4c7cba;" onclick="wuorder.CloseDiv('distributeTransfer','fade')">取消</a>
					</div>
				</div>
			</form>
		</div>
	</div>
	<!-- 提示 -->
    <div class="themisWrap" style="display:none;" >
      <div class="themisGray"></div>
        <div class="themis" style="top:30%;">
           <h3 class="themistit"><span class="themisTipPic" style="float: left;padding-top: 17px;padding-left: 10px;margin-right: 10px;"><img class="pic" src="../system/img/tishi.png" height="25" width="25" alt="" /></span>友情提示</h3>
           <div class="themispay">
                <div class="themistip" style="margin-bottom: 20px; color:red; font-size:14px;">确定删除这些信息吗!</div>
                <button class="btn navbar-right" id="quxiao" >取消</button>                
                <button class="btn navbar-right" id="patterTypeDel" style="background: #4c7cba;" onclick="delmypatter()">确定</button>                    
           </div>
        </div>
      </div> 
      <div id="showdeiatil"  style="position: absolute;width: 100%;height:1px;background: #ccc;z-index: 999;left: 0;top: 0;">
      		<iframe name="detailiframe" id="detailiframe" frameBorder=0 scrolling=no width="100%" height="100%" allowTransparency="true"></iframe>
      </div> 
	<script type="text/javascript" src="../common/js/jquery-1.11.1.min.js"></script>
	<script type="text/javascript" src="../common/js/angular.min.js"></script>
	<script type="text/javascript" src="../common/js/winTip.js"></script>
	<script type="text/javascript" src="../common/js/boot.js"></script>
	<script type="text/javascript" src="../common/js/jquery.datetimepicker.js"></script>
	<script type="text/javascript" src="../common/js/js-extend.js"></script>	
	<script type="text/javascript" src="../common/js/ajaxPage.js"></script>	
	<script type="text/javascript" src="../sales/js/salesResource.js"></script>
</body>
</html>