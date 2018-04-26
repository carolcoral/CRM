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
	<title>行政-关课</title>
</head>
<body>
	<input class="userid" type="hidden" value="${sessionScope.login_user.userid}" id="userid" />
	<input class="roleid" type="hidden" value="${sessionScope.login_user.roleid}" id="roleid" />
	<input class="deptid" type="hidden" value="${sessionScope.login_user.deptid}" id="deptid" />	
	<nav class="navbar navbar-default">
		<div class="container-fluid">			
			<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav">
				    <!-- <li onclick="exportExcel()">
						<a href="javascript:;" class="create"><span class="glyphicon glyphicon-level-up"></span>导出</a>
					</li> -->
					<li onclick="cheakRepeat()">
						<a href="javascript:;" class="create"><span class="glyphicon glyphicon-transfer"></span>关课</a>
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
	<div class="bs-example table-responsive" data-example-id="simple-table">		
        <table class="table table-bordered table-hover table-striped able-condensed" id="colseTable">              	                   
           <!-- <tr class="henglan">
				<th><input type="checkbox" class="all" /></th>
				<th class="serialnumber">序号</th>				
				<th class="studentName">姓名</th>
				<th class="arrive_money">身份证</th>
				<th class="remitWay">手机</th>				
				<th class="arrive_time">课程</th>
				<th class="remituser">科目</th>
				<th class="LCWname">客服</th>
				<th class=idCard>班级</th>
				<th class="phone">单位</th>
            </tr> 
            <tr>
                <td><input type="checkbox" class="{{infor.resourceId}}"/></td>               
               	<td class="serialnumber">序号</td>        
                <td class="studentName">姓名</td>
                <td class="arrive_money">身份证</td>
                <td class="remitWay">手机</td>        
                <td class="arrive_time">课程</td>
                <td class="remituser">科目</td>
                <td class="LCWname">客服</td>
                <td class=idCard>班级</td>
                <td class="phone">单位</td>
            </tr> -->
        </table>
        <!-- 分页 -->
		<div id="pagination">
		     
		</div>        
    </div>
    <!-- 网络培训费_筛选 -->
	<div id="networkfilter"  class="ui-wrap">
		<div class="addnew-wrap">
			<h2 class="wrap-tit"><b class="addnewTit">网络培训费_筛选</b><span class="close closebtn"  onclick="wuorder.CloseDiv('networkfilter','fade')">×</span></h2>
			
			<form class="form-horizontal add_form" action="/student/excelExportStudent.do" method="POST" id="excel">
				<div class="form-group">
					<label for="inputPassword3" class="col-md-3 control-label">姓名</label>
					<div class="col-md-9">						
						<input type="text" value="" name="studentName" id="studentName" class="form-control">
					</div>
				</div>
				<div class="form-group">
					<label for="inputPassword3" class="col-md-3 control-label">手机</label>
					<div class="col-md-9">
						<input type="text" class="form-control" id="phone" name="phone">
					</div>
				</div>
				<div class="form-group">
					<label for="inputPassword3" class="col-md-3 control-label">身份证</label>
					<div class="col-md-9">
						<input type="text" class="form-control" id="idCard" name="idCard">
					</div>
				</div>
				<div class="form-group">
					<label for="inputPassword3" class="col-md-3 control-label">班级</label>
					<div class="col-md-9">
						<select class="form-control" id="banci" name="banci">
							<option value="">请选择班级</option>							
							<option value="北京">北京</option>
			                <option value="深圳">深圳</option>
			                <option value="苏州">苏州</option>
			                <option value="南京">南京</option>
			                <option value="成都">成都</option>
			                <option value="重庆">重庆</option>
			                <option value="上海">上海</option>
			                <option value="泰州">泰州</option>
			                <option value="石家庄">石家庄</option>
			                <option value="天津">天津</option>
			                <option value="杭州">杭州</option>
			                <option value="天津">天津</option>
			                <option value="天津">天津</option>
			                <option value="天津">天津</option>
			                <option value="郑州">郑州</option>
			                <option value="太原">太原</option>
			                <option value="贵阳">贵阳</option>
			                <option value="长沙">长沙</option>
			                <option value="昆明">昆明</option>
			                <option value="武汉">武汉</option>
			                <option value="西安">西安</option>
			                <option value="呼市">呼市</option>
			                <option value="广州">广州 </option>
			                <option value="长春">长春</option>
			                <option value="太原">太原</option>
			                <option value="济南">济南</option>
			                <option value="福建">福建</option>
			                <option value="合肥">合肥</option>
			                <option value="徐州">徐州</option>
			                <option value="青岛">青岛</option>
			                <option value="南昌">南昌</option>
			                <option value="宁波">宁波</option>
			                <option value="温州">温州</option>
			                <option value="乐山">乐山</option>
			                <option value="广西">广西</option>
			                <option value="兰州">兰州 </option>
			                <option value="西宁">西宁</option>
			                <option value="银川">银川</option>
			                <option value="桂林">桂林</option>
			                <option value="酒泉">酒泉</option>
			                <option value="新疆">新疆</option>
			                <option value="榆林">榆林</option>
			                <option value="佛山">佛山</option>
			                <option value="东莞">东莞</option>
							<option value="大连">大连</option>
							<option value="哈尔滨">哈尔滨</option>
							<option value="沈阳">沈阳</option>
							<option value="内蒙机构">内蒙机构</option>
							<option value="山西工行">山西工行</option>
							<option value="邯郸工行">邯郸工行</option>
							<option value="晋城银行">晋城银行</option>
							<option value="晋商银行">晋商银行</option>
							<option value="朔州工行">朔州工行</option>
							<option value="太原市工行">太原市工行</option>
							<option value="嵊州工行">嵊州工行</option>
						</select>	
					</div>
				</div>
				<div class="form-group">
					<label for="inputPassword3" class="col-md-3 control-label">单位</label>
					<div class="col-md-9">
						<input type="text" class="form-control" id="company" name="company">
					</div>
				</div>
				<div class="form-group">
					<label for="inputPassword3" class="col-md-3 control-label">公司地址</label>
					<div class="col-md-9">
						<input type="text" class="form-control" id="companyAddr" name="companyAddr">
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
	<script type="text/javascript" src="../executive/js/colseCourse.js"></script>
	
</body>
</html>