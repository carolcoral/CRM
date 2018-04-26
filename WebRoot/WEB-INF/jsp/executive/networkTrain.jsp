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
	<title>行政-网络培训费</title>
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
					<li onclick="cheakRepeat()">
						<a href="javascript:;" class="create"><span class="glyphicon glyphicon-transfer"></span>查重</a>
					</li>
					<li onclick="sendexEcutive()">
						<a href="javascript:;" class="create"><span class="glyphicon glyphicon-share-alt"></span>提交</a>
					</li>
					<li onclick="wuorder.ShowDiv('networkfilter','fade')">
						<a href="javascript:;" class="create"><span class="glyphicon glyphicon-share-alt"></span>筛选</a>
					</li>							
					<form class="navbar-form navbar-left ng-pristine ng-valid" role="search">
						<div class="form-group">
							<input type="text"  id="searchname" class="form-control check_tiaoj ng-pristine ng-untouched ng-valid" placeholder="姓名/手机号">
						</div>
						<a href="javascript:;" class="btn" style="background: #4c7cba;" onclick="namesearch()">查询</a>
					</form>
				</ul>
			</div>			
		</div>		
	</nav>	
	<div class="table-responsive" data-example-id="simple-table">
		<p ><span id="onoff" class="btn onoff" onclick="onoff()">开</span><span class="btn onoff" onclick="editsave()">编辑保存</span></p>
        <table class="table table-bordered table-hover table-striped able-condensed" id="networkTable" style="width:1800px">              	                   
           <!--  <tr class="henglan">
				<th><input type="checkbox" class="all" /></th>
				<th class="serialnumber">序号</th>				
				<th class="studentName">学员姓名</th>
				<th class="arrive_money">收款金额</th>
				<th class="remitWay">汇款方式</th>				
				<th class="arrive_time">收款日期</th>
				<th class="remituser">代汇款人</th>
				<th class=idCard>身份证</th>
				<th class="phone">手机</th>
				<th class="LCWname">LCW用户名</th>
				<th class="LCWpassword">LCW密码</th>
				<th class="netedumoney">付网络培训款</th>
				<th class="address">地区</th>
				<th class="paytime">支付日期</th>
				<th class="xingzhengNote">备注</th>
            </tr> 
            <tr>
                <td><input type="checkbox" class="{{infor.resourceId}}"/></td>               
                <td class="serialnumber">序号</td>        
                <td class="matchname">姓名</td>
                <td class="payMoney">收款金额</td>
                <td class="payType">汇款方式</td>       
                <td class="receiveTime">收款日期</td>
                <td class="remituser">代汇款人</td>
                <td class=idCard>身份证</td>
                <td class="phone">手机</td>
                <td class="LCWname"><input type='text'value='"+paytime+"' class="changepaytime" disabled="disabled"/></td>
                <td class="LCWpassword"><input type='text'value='"+paytime+"' class="changepaytime" disabled="disabled"/></td>
                <td class="netedumoney">付网络培训款</td>
                <td class="address">地区</td>
                <td class="paytime"><input type='text'value='"+paytime+"' class="changepaytime" disabled="disabled"/></td>
               <td class="xingzhengNote"></td>                                                  
            </tr> -->
        </table>
    </div>
    <!-- 分页 -->
	<div id="pagination">
	     
	</div>        
    <!-- 网络培训费_筛选 -->
	<div id="networkfilter"  class="ui-wrap">
		<div class="addnew-wrap">
			<h2 class="wrap-tit"><b class="addnewTit">网络培训费_筛选</b><span class="close closebtn"  onclick="wuorder.CloseDiv('networkfilter','fade')">×</span></h2>
			
			<form class="form-horizontal add_form" action="/student/excelExportStudent.do" method="POST" id="excel">
				<div class="form-group">
					<label for="inputEmail3" class="col-md-3 control-label">支付时间</label>
					<div class="col-md-9">
						<input type="text" value="" id="taskStartTime" placeholder="开始时间" name="createStartTime" class="chioce_time">
						<span class="until">-</span>
						<input type="text" value="" id="taskEndTime" placeholder="结束时间" name="createEndTime" class="chioce_time">
					</div>
				</div>
				<div class="form-group">
					<div class="col-md-offset-4 col-md-6">
						<a href="javascript:;" class="btn addsave" onclick="networkfilter()">筛选</a>						
						<a href="javascript:;"  class="btn"  onclick="wuorder.CloseDiv('networkfilter','fade')" style="background: #4c7cba;">取消</a>
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
                <div class="themistip" style="margin-bottom: 20px; color:red; font-size:14px;"><span class="successtip"><span></div>                
                <button class="btn navbar-right" id="successtotal" style="background: #4c7cba;" onclick="window.location.reload();">确定</button>                   
           </div>
        </div>
     </div>  
	<script type="text/javascript" src="../common/js/jquery-1.11.1.min.js"></script>
	<script type="text/javascript" src="../common/js/winTip.js"></script>
	<script type="text/javascript" src="../common/js/boot.js"></script>
	<script type="text/javascript" src="../common/js/jquery.datetimepicker.js"></script>
	<script type="text/javascript" src="../common/js/js-extend.js"></script>
	<script type="text/javascript" src="../common/js/ajaxPage.js"></script>
	<script type="text/javascript" src="../executive/js/networkTrain.js"></script>
	
</body>
</html>