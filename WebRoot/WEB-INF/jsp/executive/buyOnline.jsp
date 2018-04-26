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
	<title>行政-在线购买</title>
</head>
<body>
	<input class="userid" type="hidden" value="${sessionScope.login_user.userid}" id="userid" />
	<input class="roleid" type="hidden" value="${sessionScope.login_user.roleid}" id="roleid" />
	<input class="deptid" type="hidden" value="${sessionScope.login_user.deptid}" id="deptid" />
	<nav class="navbar navbar-default">
		<div class="container-fluid">			
			<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav">		
			    	<li onclick="wuorder.ShowDiv('batchImport','fade')">
						<a href="javascript:;" class="create"><span class="glyphicon glyphicon-paste"></span>批量导入</a>
					</li>	
				    <li onclick="wuorder.ShowDiv('studentfilter','fade')">
						<a href="javascript:;" class="create"><span class="glyphicon glyphicon-sort-by-attributes"></span>筛选</a>
					</li>										
					<li onclick="checkassignall('#assgincustom')">
						<a href="javascript:;" class="create"><span class="glyphicon glyphicon-indent-right"></span>分配</a>
					</li>
					<li onclick="checkassignall('#Transfercustom')" id="zgtransfer" >
						<a href="javascript:;" class="create"><span class="glyphicon glyphicon-retweet"></span>转移</a>
					</li>	 
					  				
					<form class="navbar-form navbar-left ng-pristine ng-valid" role="search">
						<div class="form-group">
							<input type="text"  id="search" class="form-control check_tiaoj ng-pristine ng-untouched ng-valid" placeholder="手机号">
						</div>
						<a href="javascript:;" class="btn" style="background: #4c7cba;" id="fastquery">查询</a>
					</form>
				</ul>
				<a class="btn btn-info navbar-right info-set" href="javascript:;" style="background: #46b8da;margin-top: 8px;"><b id="zongs">当前共有学员：</b><span class="text-danger" id="filterTotal">0</span>条</a>
			</div>			
		</div>		
	</nav>		
	<div class="bs-example table-responsive" data-example-id="simple-table">		
        <table class="table table-bordered table-hover table-striped able-condensed" id="buyonlineTable">              	                   
        </table>
        <!-- 分页 -->
		<div id="pagination">
		     
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
	<!-- 分配 -->
	<div id="assgincustom"  class="ui-wrap">
		<div class="addnew-wrap">
			<h2 class="wrap-tit"><b class="addnewTit">到账学员_分配</b><span class="close closebtn"  onclick="wuorder.CloseDiv('assgincustom','fade')">×</span></h2>
			
			<form class="form-horizontal add_form">
				<div class="form-group">
					<label for="inputPassword3" class="col-md-3 control-label">选择客服员</label>
					<div class="col-md-9">
						<select class="form-control" id="assginCustomer">
							 <option value="">请选择选择客服员</option> 
						</select>
					</div>
				</div>
				<div class="form-group">
					<div class="col-md-offset-4 col-md-6">						
						<span href="javascript:;" class="btn addsave"  onclick="assignall()">确定</span>						
						<span href="javascript:;" class="btn" style="background: #4c7cba;" onclick="wuorder.CloseDiv('assgincustom','fade')">取消</span>
					</div>
				</div>
			</form>
		</div>
	</div>	
	<!-- 转移 -->
	<div id="Transfercustom"  class="ui-wrap">
		<div class="addnew-wrap">
			<h2 class="wrap-tit"><b class="addnewTit">到账学员_转移</b><span class="close closebtn"  onclick="wuorder.CloseDiv('Transfercustom','fade')">×</span></h2>
			
			<form class="form-horizontal add_form">
				<div class="form-group">
					<label for="inputPassword3" class="col-md-3 control-label">选择客服员</label>
					<div class="col-md-9">
						<select class="form-control" id="TransferCustomer">
							 <option value="">请选择选择客服员</option> 
						</select>
					</div>
				</div>
				<div class="form-group">
					<div class="col-md-offset-4 col-md-6">
						<span href="javascript:;" class="btn addsave" onclick="transfer()">确定</span>									
						<span href="javascript:;" class="btn" style="background: #4c7cba;" onclick="wuorder.CloseDiv('Transfercustom','fade')">取消</span>
					</div>
				</div>
			</form>
		</div>
	</div>
	<!-- 筛选 -->
	<div id="studentfilter"  class="ui-wrap">
		<div class="addnew-wrap">
			<h2 class="wrap-tit"><b class="addnewTit">筛选</b><span class="close closebtn"  onclick="wuorder.CloseDiv('studentfilter','fade')">×</span></h2>
			
			<form class="form-horizontal add_form" action="/student/excelExportStudent.do" method="POST" id="excel">
				<div class="form-group">
					<label for="inputPassword3" class="col-md-3 control-label">课程</label>
					<div class="col-md-9">
						<select class="form-control" id="course" name="course">
							<option value="">请选择课程</option>							
						</select>	
					</div>
				</div>
								
				<div class="form-group">
					<div class="col-md-offset-4 col-md-6">
						<a href="javascript:;" class="btn addsave" onclick="studentfilter()">筛选</a>						
						<a href="javascript:;"  class="btn"  onclick="wuorder.CloseDiv('studentfilter','fade')" style="background: #4c7cba;">取消</a>
					</div>
				</div>
			</form>
		</div>
	</div>	
	<script type="text/javascript" src="../common/js/jquery-1.11.1.min.js"></script>
	<script type="text/javascript" src="../common/js/winTip.js"></script>
	<script type="text/javascript" src="../common/js/boot.js"></script>
	<script type="text/javascript" src="../common/js/jquery.datetimepicker.js"></script>
	<script type="text/javascript" src="../common/js/js-extend.js"></script>
	<script type="text/javascript" src="../common/js/ajaxPage.js"></script>
	<script type="text/javascript" src="../executive/js/buyOline.js"></script>
	
</body>
</html>