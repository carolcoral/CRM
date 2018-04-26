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
	<link rel="stylesheet" type="text/css" href="../system/css/userMang.css" />
	<title>科目管理</title>
</head>
<body ng-app="subject" ng-controller="subjectCtrl">
    <div class="meun-btn">
      	<ul class="nav navbar-nav">
			<li onclick="wuorder.ShowDiv('subject','fade')">
				<a href="javascript:;" class="create"><span class="glyphicon glyphicon-plus"></span>增加</a>
			</li>
			<li class="orange" onclick="changeSub()">
				<a href="javascript:;" class="create"><span class="glyphicon glyphicon-pencil"></span>修改</a>
			</li>
			<li class="red" onclick="delShow()">
				<a href="javascript:;" class="create"><span class="glyphicon glyphicon-minus"></span>删除</a>
			</li>						
		</ul>
   </div>
    <div class="bs-example table-responsive" data-example-id="simple-table" style="width: 60%;">    	
    	<div class="coursePanel">
		  <span class="glyphicon glyphicon-th-list"></span>科目列表	  
		</div>               
        <table class="table table-bordered table-hover table-striped able-condensed" id="sbujectTable">        	                   
            <tr class="henglan">
                <td class="text-primary" style="width: 45px;"><input type="checkbox" class="all" /></td>
                <td class="text-primary" style="width: 45px;">序号</td>                
                <td class="text-primary">所属课程</td>
                <td class="text-primary">科目名称</td>
                <td class="text-primary">科目编码</td>
                <td class="text-primary">备注</td>
            </tr> 
            <tr ng-repeat="infor in list"  data-id="{{infor.subjectid}}">
                <td><input type="checkbox" class="{{infor.subjectid}}"/></td>
                <td ng-bind="{{(currentPage-1)*pageSize+($index+1)}}"></td>
                <td ng-bind="infor.coursename" data-id="{{infor.courseid}}"></td>
                <td ng-bind="infor.subjectname"></td>
                <td ng-bind="infor.subjectcode"></td>
                <td ng-bind="infor.note"></td>                                                                
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
	<!-- 场景增加 -->
	<div id="subject"  class="ui-wrap">
		<div class="addnew-wrap">
			<h2 class="wrap-tit"><b class="addnewTit">科目管理_增加</b><span class="close closebtn"  onclick="wuorder.CloseDiv('subject','fade')">×</span></h2>
			<form class="form-horizontal add_form">
				
				<div class="form-group">
					<label for="inputPassword3" class="col-md-4 control-label">所属课程：</label>
					<div class="col-md-7">						
						<select class="form-control" name="deptname" id="courseName">
							<option value="">请选择所属课程</option>
							<option ng-repeat="option in course" value="{{option.courseid}}">{{option.courseName}}</option>							
						</select>
					</div>					
				</div>
				<div class="form-group">
					<label for="inputPassword3" class="col-md-4 control-label">科目名称：</label>
					<div class="col-md-7">
						<input type="text" class="form-control" id="subjectname"> 
					</div>					
				</div>
				<div class="form-group">
					<label for="inputPassword3" class="col-md-4 control-label">科目编码：</label>
					<div class="col-md-7">
						<input type="text" class="form-control" id="subjectcode"> 
					</div>					
				</div>
				<div class="form-group">
					<label for="inputPassword3" class="col-md-4 control-label">备注：</label>
					<div class="col-md-7">
						<input type="text" class="form-control" id="note"> 
					</div>					
				</div>
				<div class="form-group">
					<div class="col-md-offset-4 col-md-6">
						<a href="javascript:;" class="btn" id="saveSubject" onclick="addSubject()">保 存</a>
						<a href="javascript:;" class="btn" id="saveChSub" onclick="saveChSubject()" style="display: none;">保 存</a>						
						<a href="javascript:;"  class="btn"  onclick="wuorder.CloseDiv('subject','fade')" style="background: #4c7cba;">取消</a>
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
                <button class="btn navbar-right" id="patterTypeDel" style="background: #4c7cba;" onclick="delSunject()">确定</button>                    
           </div>
        </div>
      </div>	
<script type="text/javascript" src="../common/js/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="../common/js/angular.min.js"></script>
<script type="text/javascript" src="../common/js/winTip.js"></script>
<script type="text/javascript" src="../common/js/boot.js"></script>
<script type="text/javascript" src="../system/js/subjectMang.js"></script>
</body>
</html>