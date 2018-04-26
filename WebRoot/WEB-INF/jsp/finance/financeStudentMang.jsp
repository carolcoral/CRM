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
	<title>财务-总表学员</title>
</head>
<body>
	<input class="userid" type="hidden" value="${sessionScope.login_user.userid}" id="userid" />
	<input class="roleid" type="hidden" value="${sessionScope.login_user.roleid}" id="roleid" />
	<input class="deptid" type="hidden" value="${sessionScope.login_user.deptid}" id="deptid" />
	<nav class="navbar navbar-default">
		<div class="container-fluid">			
			<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav">				    										
					<li onclick="exportExcel()">
						<a href="javascript:;" class="create"><span class="glyphicon glyphicon-level-up"></span>导出</a>
					</li>
					<li onclick="wuorder.ShowDiv('filter','fade')">
						<a href="javascript:;" class="create" id="row-define" data-reveal-id="row-def"><span class="glyphicon glyphicon-retweet"></span>筛选</a>						
					</li>					
					<form class="navbar-form navbar-left ng-pristine ng-valid" role="search">
						<div class="form-group">
							<input type="text" name="search" id="search" class="form-control check_tiaoj ng-pristine ng-untouched ng-valid" placeholder="手机号">
						</div>
						<a href="javascript:;" class="btn" style="background: #4c7cba;" onclick="phonesearch()">查询</a>
					</form>
				</ul>																
			</div>
			<!-- /.navbar-collapse -->
		</div>
		<!-- /.container-fluid -->
	</nav>	
	<div class="table-responsive" data-example-id="simple-table">	
		<p ><span id="onoff" class="btn onoff" onclick="onoff()">开</span><span class="btn onoff" onclick="editsave()">编辑保存</span></p>	
        <table class="table table-bordered table-hover table-striped able-condensed" id="resourceTable" style="width: 5000px;border:none;">
        	<tr class="henglan">   
        		<th style="width: 60px;position: fixed;background: #fff;"><input type="checkbox" class="all" /></th>
                <th style="width: 60px;position: fixed;background: #fff;left: 59px;">详情</th>
                <th class="serialnumber" style="width: 60px;position: fixed;background: #fff;left: 118px;">序号</th>
                <th class="studentName" style="width: 80px;position: fixed;background: #fff;left: 177px;">姓名</th>
                <th class="phone" style="padding-left: 260px;">手机</th>
                <th class="studentstate">学员状态</th>
                <th class="matchinfo_time">确认到账时间</th>
                <th class="idCard">身份证</th>              
                <th class="tel">固定电话</th>
                <th class="email">邮箱</th>
                <th class="company">公司</th>
                <th class="companyAddr">公司地址</th>
                <th class="homeAddr">家庭地址</th>
                <th class="position">职务</th>
                <th class="school">毕业院校</th>
                <th class="education">学历</th>
                <th class="nation">民族</th>
                <th class="belongName" >招生老师</th>
                <th class="preferinfo" >优惠情况</th>
                <th class="remituser" >代汇款姓名</th>
                <th class="arrive_money" >收款金额</th>
                <th class="arrive_time" >收款日期</th>
                <th class="remitWay" >汇款方式</th>
                <th class="LCWname" >用户名</th>
                <th class="LCWpassword" >LCW密码</th>
                <th class="courseversion" >课件</th>
                <th class="invoiceinfo" >发票情况</th>
                <th class="baokaopassword" >报考密码</th>
                <th class="ispass" >是否通过</th>
                <th class="isSignAgreement" >是否签订协议</th>
                <th class="banci" >班级</th>
                <th class="qici" >期次</th>
                <th class="xingzhengNote" >行政备注</th>
                <th class="subjectname" >科目</th>
                <th class="courseName" >课程名称</th>
                <th class="dealprice" >我司收入</th>
                <th class="tuifei" >退费</th>
                <th class="tuifeitime" >退费日期</th>
                <th class="netedumoney" >网络培训费</th>
                <th class="paytime" >支付日期</th>
                <th class="shenhe" >审核问题</th>
                <th class="caiwunote" >备注</th> 
            </tr>          	                   
           <!-- <tr class="henglan">
            	<th class="resourceName" style="width: 60px;position: fixed;background: #fff;">序号</th>
				<th class="resourceName" style="width: 60px;position: fixed;background: #fff;left: 59px;">姓名</th>
                <th class="phone" style="padding-left: 120px;">手机</th>
				<th class="studentstate">学员状态</th>
                <th class="dealtime" style="width:180px;">成交时间</th>   
                <th class="headmaster" style="width: 120px;">班主任</th>              
                <th class="idCard" style="width: 202px;">身份证号</th>
                <th class="school">毕业院校</th>
                <th class="education">学历</th>
                <th class="preferinfo">优惠信息</th>
                <th class="LCWname">理财网用户名</th>
                <th class="LCWpassword">理财网密码</th>
                <th class="email">邮箱</th>
                <th class="company">单位</th>
                <th class="department">工作部门</th>
                <th class="position">职务</th>
                <th class="companyTel" >单位电话</th>
					
            </tr> -->
            <!-- <tr class="henglan">
            	<td class="serialnumber" style="width: 60px;position: fixed;background: #fff;">序号</td>
				<td class="studentName" style="width: 60px;position: fixed;background: #fff;left: 59px;">姓名</td>
                <td class="phone" style="padding-left: 120px;">手机</td>
				<td class="studentstate">学员状态</td>
                <td class="dealtime" style="width:180px;">成交时间</td>  
                <td class="headmaster" style="width: 120px;">班主任</td>               
                <td class="idCard" style="width: 202px;">身份证号</td>
                <td class="school">毕业院校</td>
                <td class="education">学历</td>
                <td class="preferinfo">优惠信息</td>
                <td class="LCWname">理财网用户名</td>
                <td class="LCWpassword">理财网密码</td>
                <td class="email">邮箱</td>
                <td class="company">单位</td>
                <td class="department">工作部门</td>
                <td class="position">职务</td>
                <td class="companyTel" >单位电话</td>
            </tr> -->
        </table>
    </div>
	<!-- 分页 -->        
	<nav id="pagination">
	    
	</nav>
	<!-- 资源管理_筛选 -->
	<div id="filter"  class="ui-wrap">
		<div class="addnew-wrap">
			<h2 class="wrap-tit"><b class="addnewTit">总表学员_筛选</b><span class="close closebtn"  onclick="wuorder.CloseDiv('filter','fade')">×</span></h2>
			
			<form id="excelExport" class="form-horizontal add_form">
				<div class="form-group">
					<label for="inputEmail3" class="col-md-3 control-label">收款日期</label>
					<div class="col-md-9">
						<input type="text" value="" id="arriveStartTime" placeholder="开始时间" name="arriveStartTime" class="chioce_time">
						<span class="until">-</span>
						<input type="text" value="" id="arriveEndTime" placeholder="结束时间" name="arriveEndTime" class="chioce_time">
					</div>
				</div>
				<div class="form-group">
					<label for="inputPassword3" class="col-md-3 control-label">汇款方式</label>
					<div class="col-md-9">
						<input type="text" class="form-control" id="remitWay" name="remitWay">
					</div>
				</div>
				<div class="form-group">
					<label for="inputPassword3" class="col-md-3 control-label">学员姓名</label>
					<div class="col-md-9">
						<input type="text" class="form-control" id="studentName" name="studentName">
					</div>
				</div>
				<div class="form-group">
					<label for="inputPassword3" class="col-md-3 control-label">身份证</label>
					<div class="col-md-9">
						<input type="text" class="form-control" id="idCard" name="idCard">
					</div>
				</div>
				<div class="form-group">
					<label for="inputPassword3" class="col-md-3 control-label">手机号</label>
					<div class="col-md-9">
						<input type="text" class="form-control" id="phone" name="phone">
					</div>
				</div>
				<div class="form-group">
					<label for="inputPassword3" class="col-md-3 control-label">招生老师</label>
					<div class="col-md-9">
						<select class="form-control" id="belongName" name="belongName">
							<option value="">请选择招生老师</option>							
						</select>	
					</div>
				</div>					
				<div class="form-group">
					<label for="inputEmail3" class="col-md-3 control-label">课程</label>
					<div class="col-md-9">
						<select class="form-control" id="course" name="course">
						    <option value="">请选择课程</option> 
							
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
	<script type="text/javascript" src="../common/js/jquery-1.11.1.min.js"></script>
	<script type="text/javascript" src="../common/js/angular.min.js"></script>
	<script type="text/javascript" src="../common/js/winTip.js"></script>
	<script type="text/javascript" src="../common/js/boot.js"></script>
	<script type="text/javascript" src="../common/js/jquery.datetimepicker.js"></script>
	<script type="text/javascript" src="../common/js/js-extend.js"></script>	
	<script type="text/javascript" src="../common/js/ajaxPage.js"></script>	
	<script type="text/javascript" src="../common/js/mydefinel.js"></script>
	<script type="text/javascript" src="../finance/js/financeStudentMang.js"></script>
	
</body>
</html>