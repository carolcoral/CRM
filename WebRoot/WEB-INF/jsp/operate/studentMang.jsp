<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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
	<title>运营学员管理</title>
</head>
<body>
	<input class="userid" type="hidden" value="${sessionScope.login_user.userid}" id="userid" />
	<input class="roleid" type="hidden" value="${sessionScope.login_user.roleid}" id="roleid" />
	<input class="deptid" type="hidden" value="${sessionScope.login_user.deptid}" id="deptid" />	
	<nav class="navbar navbar-default">
		<div class="container-fluid">			
			<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav">
					<li onclick="wuorder.ShowDiv('filter','fade')">
						<a href="javascript:;" class="create"><span class="glyphicon glyphicon-sort-by-attributes"></span>筛选</a>
					</li>										
					<li onclick="excelExport()">
						<a href="javascript:;" class="create"><span class="glyphicon glyphicon-level-up"></span>导出</a>
					</li>
					<form class="navbar-form navbar-left ng-pristine ng-valid" role="search">
						<div class="form-group">
							<input type="text" name="mobile" id="mobile" class="form-control check_tiaoj" placeholder="手机搜索">
						</div>
						<a class="btn" href="javascript:void(0);" onclick="fastquery();"><span class="glyphicon glyphicon-search" aria-hidden="true"></span></a>
					</form>
				</ul>
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
					成交日期：<span class="text-danger cjfilter"></span>
				</p>
				<p class="navbar-text filtercolr">
					归属者：<span class="text-danger gszfilter"></span>
				</p>
				<p class="navbar-text filtercolr">
					姓名：<span class="text-danger xmfilter"></span>
				</p>
				<p class="navbar-text filtercolr">
					渠道：<span class="text-danger qdfilter"></span>
				</p>									
				<p class="navbar-text filtercolr">
					手机：<span class="text-danger sjfilter"></span>
				</p>
				<p class="navbar-text filtercolr">
					课程：<span class="text-danger kcfilter"></span>
				</p>
				<a class="btn btn-info navbar-right info-set" href="javascript:;" style="background: #46b8da;margin-top: 8px;"><b id="zongs">当前共有资源：</b><span class="text-danger" id="filterTotal">0</span>条</a>				
			</div>
		</div>
	</nav>	
	<div class="table-responsive" data-example-id="simple-table">
        <table class="table table-bordered table-hover table-striped able-condensed" id="studentTable">              	                   
            <tr class="henglan">
				<th style="width:40px;"><input type="checkbox" class="all" /></th>
				<th class="serialnumber" style="width:60px;">序号</th>
				<th class="dealtime" style="width:140px;">成交时间</th>
				<th class="arrive_time" style="width:140px;">到账时间</th>				
				<th class="belongName" style="width:70px;">归属者</th>
				<th class="studentName" style="width:110px;">学员姓名</th>
				<th class="source" style="width:110px;">渠道</th>
				<th class="phone" style="width:110px;">客户电话</th>				
				<th class="courseName" style='width:90px;'>课程</th>
				<th class="subjectname">报名科目</th>
				<th class="dealprice" style='width:70px;'>成交金额</th>
				<th class="arrive_money" style='width:70px;'>到账金额</th>
            </tr> 
            <!-- <tr ng-repeat="infor in list"  data-id="{{infor.resourceId}}">
                <td><input type="checkbox" class="{{infor.resourceId}}"/></td>
                <td class="dealtime" ng-bind="infor.dealtime"></td>				
				<td class="belongName" ng-bind="infor.belongName"></td>
                <td class="studentName" ng-bind="infor.studentName"></td>                               
				<td class="source" ng-bind="source(infor.source)"></td>
				<td class="phone" ng-bind="infor.phone"></td>				
				<td class="courseName" ng-bind="infor.courseName"></td>				
				<td class="subjectname" ng-bind="infor.subjectname"></td> 
				<td class="dealprice" ng-bind="infor.dealprice"></td>				                                                     
            </tr> -->
        </table>
        <!-- 分页 -->
    </div>   
	<nav id="pagination">
	     
	</nav>        
	<!-- 资源管理_筛选 -->
	<div id="filter"  class="ui-wrap">
		<div class="addnew-wrap">
			<h2 class="wrap-tit"><b class="addnewTit">学员管理_筛选</b><span class="close closebtn"  onclick="wuorder.CloseDiv('filter','fade')">×</span></h2>
			
			<form class="form-horizontal add_form" id="excel">
				<div class="form-group">
					<label for="inputEmail3" class="col-md-3 control-label">成交日期</label>
					<div class="col-md-9">
						<input type="text" value="" id="taskStartTime" placeholder="开始时间" name="createStartTime" class="chioce_time">
							<span class="until">-</span>
						<input type="text" value="" id="taskEndTime" placeholder="结束时间" name="createEndTime"  class="chioce_time">						
					</div>
				</div>				
				<div class="form-group">
					<label for="inputPassword3" class="col-md-3 control-label">归属者</label>
					<div class="col-md-9">
						<select class="form-control" id="belongName" name="belongid">
							<option value="">请选择归属者</option>
						</select>
					</div>
				</div>
				<div class="form-group">
					<label for="inputPassword3" class="col-md-3 control-label">学员姓名</label>
					<div class="col-md-9">
						<input type="text" class="form-control" id="studentName" name="studentName">
					</div>
				</div>										
				<div class="form-group">
					<label for="inputPassword3" class="col-md-3 control-label">渠道</label>
					<div class="col-md-9">
						<select class="form-control" name="source" id="source">
							<option value="">请选择渠道</option>
							<option value="0">自建</option>
							<option value="1">课程注册</option>
							<option value="2">在线注册</option>
							<option value="3">app下载注册</option>
							<option value="4">电话咨询</option>
							<option value="5">金考网注册用户</option>
							<option value="6">线上渠道</option>
							<option value="7">在线咨询</option>										
							<option value="8">大库资源</option>
							<option value="9">在线购买</option>
							<option value="10">继续教育</option>
						</select>
					</div>
				</div>
				<div class="form-group">
					<label for="inputPassword3" class="col-md-3 control-label">手机</label>
					<div class="col-md-9">
						<input type="text" class="form-control" id="phone">
					</div>
				</div>
				<div class="form-group">
					<label for="inputPassword3" class="col-md-3 control-label">课程</label>
					<div class="col-md-9">						
						<select class="form-control" name="source" id="courseid">
							<option value="">请选择课程</option>							
						</select>
					</div>
				</div>
				<div class="form-group">
					<div class="col-md-offset-4 col-md-6">
						<a href="javascript:;" class="btn addsave" onclick="filter()">筛选</a>						
						<a href="javascript:;"  class="btn"  onclick="wuorder.CloseDiv('filter','fade')" style="background: #4c7cba;">取消</a>
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
	<script type="text/javascript" src="../common/js/jquery-1.11.1.min.js"></script>
	<script type="text/javascript" src="../common/js/winTip.js"></script>
	<script type="text/javascript" src="../common/js/boot.js"></script>
	<script type="text/javascript" src="../common/js/jquery.datetimepicker.js"></script>
	<script type="text/javascript" src="../common/js/js-extend.js"></script>
	<script type="text/javascript" src="../common/js/ajaxPage.js"></script>	
	<script type="text/javascript" src="../operate/js/studentMang.js"></script>
</body>
</html>