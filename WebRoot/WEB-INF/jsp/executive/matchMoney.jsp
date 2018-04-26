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
	<title>行政-匹配到账</title>
</head>
<body>
	<input class="userid" type="hidden" value="${sessionScope.login_user.userid}" id="userid" />
	<input class="roleid" type="hidden" value="${sessionScope.login_user.roleid}" id="roleid" />
	<input class="deptid" type="hidden" value="${sessionScope.login_user.deptid}" id="deptid" />	
	<nav class="navbar navbar-default">
		<div class="container-fluid" style="height: 62px;">			
			<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav">				    
					<li onclick="deterMatch()">
						<a href="javascript:;" class="create"><span class="glyphicon glyphicon-sort-by-attributes"></span>确认到账</a>
					</li>
					<li class="redtip">姓名重复的学员，需要单个去确认提交，非重复姓名的学员，可批量并按照姓名对应确认</li>					
				</ul>
			</div>			
		</div>		
	</nav>
	<div class="matchmoney">		
		<div class="bs-example table-responsive filltable" data-example-id="simple-table">
			<form class="navbar-form navbar-left ng-pristine ng-valid" role="search" style="padding:0">
				<div class="form-group">
					<input type="text"  id="searchSalename" class="form-control check_tiaoj ng-pristine ng-untouched ng-valid" placeholder="学员姓名/汇款人">
				</div>
				<a href="javascript:;" class="btn" style="background: #4c7cba;" onclick="searchSalename()">查询</a>
			</form>
			<h2 class="title">销售</h2>
			<div class="autotable" id="saleautoTable">
				<table class="table table-bordered table-hover table-striped able-condensed" id="saleTable">              	                   
		           <!-- <tr class="henglan">					
						<th class="serialnumber">序号</th>				
						<th class="coursename">课程名称</th>	
						<th class="subjectname">科目名称</th>				
						<th class="studentName">学员姓名</th>						
						<th class="dealprice">成交金额</th>
						<th class="remituser">汇款人姓名</th>				
						<th><input type="checkbox" class="saleall" /></th>		
		            </tr> 
		            <tr>
		                <td class="serialnumber"></td>				
						<td class="coursename"></td>
		                <td class="subjectname"></td>
						<td class="remituser"></td>	
						<td class="dealprice"></td>	
						<td class="remituser"></td>				
		                <td><input type="checkbox" class="{{infor.resourceId}}"/></td>
		            </tr> -->
		        </table>	
			</div>
	        <p id="saletotal"></p>            
	    </div> 
		<div class="bs-example table-responsive filltable" data-example-id="simple-table">
			<form class="navbar-form navbar-left ng-pristine ng-valid" role="search" style="padding:0">
				<div class="form-group">
					<input type="text"  id="searchFinancename" class="form-control check_tiaoj ng-pristine ng-untouched ng-valid" placeholder="学员姓名">
				</div>
				<a href="javascript:;" class="btn" style="background: #4c7cba;" onclick="searchFinancename()">查询</a>
			</form>
			<h2 class="title">财务</h2>
			<div class="autotable" id="financeautoTable">
		        <table class="table table-bordered table-hover table-striped able-condensed" id="financeTable">              	                   
		           <!-- <tr class="henglan">				
						<th><input type="checkbox" class="financeall" /></th>		
						<th class="serialnumber">序号</th>				
						<th class="studentName">学员姓名</th>						
						<th class="arrive_money">收款金额</th>	
						<th class="arrive_time">收款日期</th>				
						<th class="remitWay">汇款方式</th>				
						<th class="dealprice">备注</th>
		            </tr> 
		            <tr>
		                <td><input type="checkbox" class="{{infor.resourceId}}"/></td>
		                <td class="serialnumber"></td>				
						<td class="studentName"></td>
		                <td class="arrive_money"></td>
						<td class="arrive_time"></td>	
						<td class="remitWay"></td>	
						<td class="dealprice"></td>				
		            </tr> -->
		        </table>
	        </div>
	        <p id="financetotal"></p>	          
	    </div>
	</div> 
	
	<!-- 提示 -->
    <div class="themisWrap" style="display:none;" >
      <div class="themisGray"></div>
        <div class="themis" style="top:30%;">
           <h3 class="themistit"><span class="themisTipPic" style="float: left;padding-top: 17px;padding-left: 10px;margin-right: 10px;"><img class="pic" src="../system/img/tishi.png" height="25" width="25" alt="" /></span>友情提示</h3>
           <div class="themispay">
                <div class="themistip" style="margin-bottom: 20px; color:red; font-size:14px;">两边对账数目不一致，是否继续确认？</div>
                <button class="btn navbar-right" id="quxiao">取消</button>                
                <button class="btn navbar-right" id="patterTypeDel" style="background: #4c7cba;" onclick="matchdeter()">确定</button>                    
           </div>
        </div>
     </div>  
	<script type="text/javascript" src="../common/js/jquery-1.11.1.min.js"></script>
	<script type="text/javascript" src="../common/js/winTip.js"></script>
	<script type="text/javascript" src="../common/js/boot.js"></script>
	<script type="text/javascript" src="../common/js/jquery.datetimepicker.js"></script>
	<script type="text/javascript" src="../common/js/js-extend.js"></script>
	<script type="text/javascript" src="../common/js/ajaxPage.js"></script>
	<script type="text/javascript" src="../executive/js/matchMoney.js"></script>
	
</body>
</html>