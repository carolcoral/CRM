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
	<title>财务-到账信息</title>
</head>
<body>
	<input class="userid" type="hidden" value="${sessionScope.login_user.userid}" id="userid" />
	<input class="roleid" type="hidden" value="${sessionScope.login_user.roleid}" id="roleid" />
	<input class="deptid" type="hidden" value="${sessionScope.login_user.deptid}" id="deptid" />	
	<nav class="navbar navbar-default">
		<div class="container-fluid">			
			<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav">
				    <li onclick="repeatMatchinfo()">
						<a href="javascript:;" class="create"><span class="glyphicon glyphicon-sort-by-attributes"></span>查重</a>
					</li>	
					<li onclick="wuorder.ShowDiv('matchfilter','fade')">
						<a href="javascript:;" class="create"><span class="glyphicon glyphicon-sort-by-attributes"></span>筛选</a>
					</li>
					<li onclick="showdelResource()">
						<a href="javascript:;" class="create"><span class="glyphicon glyphicon-trash"></span>删除</a>
					</li>										
					<li onclick="wuorder.ShowDiv('batchImport','fade')">
						<a href="javascript:;" class="create"><span class="glyphicon glyphicon-level-up"></span>导入</a>
					</li>									
					<form class="navbar-form navbar-left ng-pristine ng-valid" role="search">
						<div class="form-group">
							<input type="text"  id="searchname" class="form-control check_tiaoj ng-pristine ng-untouched ng-valid" placeholder="学员姓名">
						</div>
						<a href="javascript:;" class="btn" style="background: #4c7cba;" onclick="namesearch()">查询</a>
					</form>
				</ul>
			</div>			
		</div>		
	</nav>	
	<div class="bs-example table-responsive" data-example-id="simple-table">
        <table class="table table-bordered table-hover table-striped able-condensed" id="macthTable">              	                   
            <!-- <tr class="henglan">
				<th><input type="checkbox" class="all" /></th>
				<th class="serialnumber">序号</th>				
				<th class="matchname">姓名</th>
				<th class="payMoney">收款金额</th>
				<th class="receiveTime">收款日期</th>
				<th class="payType">汇款方式</th>				
				<th class="matchnote">备注</th>
            </tr> 
            <tr>
                <td><input type="checkbox" class="{{infor.resourceId}}"/></td>
                <td class="serialnumber"></td>				
				<td class="matchname"></td>
                <td class="payMoney"></td>                               
				<td class="receiveTime"></td>
				<td class="payType"></td>				
				<td class="matchnote"></td>			                                                     
            </tr> -->
        </table>
        <!-- 分页 -->
		<div id="pagination">
		     
		</div>        
    </div>   
	<!-- 到账信息_筛选 -->
	<div id="matchfilter"  class="ui-wrap">
		<div class="addnew-wrap">
			<h2 class="wrap-tit"><b class="addnewTit">到账信息_筛选</b><span class="close closebtn"  onclick="wuorder.CloseDiv('matchfilter','fade')">×</span></h2>
			
			<form class="form-horizontal add_form" action="/student/excelExportStudent.do" method="POST" id="excel">
				<div class="form-group">
					<label for="inputEmail3" class="col-md-3 control-label">收款日期</label>
					<div class="col-md-9">
						<input type="text" value="" id="taskStartTime" placeholder="开始时间" name="createStartTime" class="chioce_time">
						<span class="until">-</span>
						<input type="text" value="" id="taskEndTime" placeholder="结束时间" name="createEndTime" class="chioce_time">
					</div>
				</div>				
				<div class="form-group">
					<label for="inputPassword3" class="col-md-3 control-label">姓名</label>
					<div class="col-md-9">						
						<input type="text" value="" name="matchname" id="matchname" class="form-control">
					</div>
				</div>
				<div class="form-group">
					<label for="inputPassword3" class="col-md-3 control-label">汇款方式</label>
					<div class="col-md-9">
						<input type="text" class="form-control" id="payType" name="payType">
					</div>
				</div>									
							
				<div class="form-group">
					<div class="col-md-offset-4 col-md-6">
						<a href="javascript:;" class="btn addsave" onclick=" matchfilter()">筛选</a>						
						<a href="javascript:;"  class="btn"  onclick="wuorder.CloseDiv('matchfilter','fade')" style="background: #4c7cba;">取消</a>
					</div>
				</div>
			</form>
		</div>
	</div>
	<!-- 批量导入 -->
	<div id="batchImport"  class="ui-wrap">
		<div class="addnew-wrap">
			<h2 class="wrap-tit"><b class="addnewTit">到账信息_批量导入</b><span class="close closebtn"  onclick="wuorder.CloseDiv('batchImport','fade')">×</span></h2>
			
			<form class="form-horizontal add_form" id="uploadForm" enctype="multipart/form-data">				
				<div class="form-group">
						<label for="inputEmail3" class="col-md-4 control-label">请选择Excel文件</label>
						<div class="col-md-8">
							<span class="fileinput-button" >添加文件<input name="matchinfoFile"  id="fileinput-input" class="fileinput-input" type="file"></span>
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
	<!-- 提示 -->
    <div class="themisWrap" style="display:none;" id="daotip">
      <div class="themisGray"></div>
        <div class="themis" style="top:30%;">
           <h3 class="themistit"><span class="themisTipPic" style="float: left;padding-top: 17px;padding-left: 10px;margin-right: 10px;"><img class="pic" src="../system/img/tishi.png" height="25" width="25" alt="" /></span>友情提示</h3>
           <div class="themispay">
                <div class="themistip" style="margin-bottom: 20px; color:red; font-size:14px;"><span class="successtip"><span></div>                
                <button class="btn navbar-right" id="successtotal" style="background: #4c7cba;" onclick="window.location.reload();">确定</button>                   
           </div>
        </div>
     </div>  
     <!-- 提示 -->
    <div class="themisWrap" style="display:none;" id="deltip">
      <div class="themisGray"></div>
        <div class="themis" style="top:30%;">
           <h3 class="themistit"><span class="themisTipPic" style="float: left;padding-top: 17px;padding-left: 10px;margin-right: 10px;"><img class="pic" src="../system/img/tishi.png" height="25" width="25" alt="" /></span>友情提示</h3>
           <div class="themispay">
                <div class="themistip" style="margin-bottom: 20px; color:red; font-size:14px;">确定删除这些信息吗!</div>
                <button class="btn navbar-right" id="quxiao" onclick="quxiao()">取消</button>                
                <button class="btn navbar-right" id="patterTypeDel" style="background: #4c7cba;" onclick="delResource()">确定</button>                    
           </div>
        </div>
      </div> 
	<script type="text/javascript" src="../common/js/jquery-1.11.1.min.js"></script>
	<script type="text/javascript" src="../common/js/winTip.js"></script>
	<script type="text/javascript" src="../common/js/boot.js"></script>
	<script type="text/javascript" src="../common/js/jquery.datetimepicker.js"></script>
	<script type="text/javascript" src="../common/js/js-extend.js"></script>
	<script type="text/javascript" src="../common/js/ajaxPage.js"></script>	
	<script type="text/javascript" src="../finance/js/macthinfor.js"></script>
</body>
</html>