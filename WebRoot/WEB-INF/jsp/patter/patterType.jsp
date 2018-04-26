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
	<title>话术场景</title>
</head>
<body ng-app="patterType" ng-controller="patterTypeCtrl">
    <div class="meun-btn">
      	<ul class="nav navbar-nav">
			<li onclick="wuorder.ShowDiv('patterType','fade')"">
				<a href="javascript:;" class="create"><span class="glyphicon glyphicon-plus"></span>增加</a>
			</li>
			<li class="orange" onclick="changepatter()">
				<a href="javascript:;" class="create"><span class="glyphicon glyphicon-pencil"></span>修改</a>
			</li>
			<li class="red" onclick="showpatter()">
				<a href="javascript:;" class="create"><span class="glyphicon glyphicon-minus"></span>删除</a>
			</li>						
		</ul>
   </div>
    <div class="bs-example table-responsive" data-example-id="simple-table" style="width: 40%;">    	
    	<div class="coursePanel">
		  <span class="glyphicon glyphicon-th-list"></span>课程话术场景列表	  
		</div>               
        <table class="table table-bordered table-hover table-striped able-condensed" id="patterTable">        	                   
            <tr class="henglan">
                <td class="text-primary" style="width: 45px;"><input type="checkbox" class="all" /></td>
                <td class="text-primary" style="width: 45px;">序号</td>                
                <td class="text-primary">所属课程</td>
                <td class="text-primary">话术场景类别名称</td>
            </tr> 
            <tr ng-repeat="infor in list"  data-id="{{infor.patterTypeId}}">
                <td><input type="checkbox" class="{{infor.patterTypeId}}"/></td>
                <td ng-bind="{{$index+1}}"></td>
                <td ng-bind="patter(infor.courseid)" data-id="{{infor.courseid}}"></td>
                <td ng-bind="infor.patterTypeName"></td>                                                                
            </tr>
        </table>        
    </div>
	<!-- 场景增加 -->
	<div id="patterType"  class="ui-wrap">
		<div class="addnew-wrap">
			<h2 class="wrap-tit">话术场景_增加<span class="close closebtn"  onclick="wuorder.CloseDiv('patterType','fade')">×</span></h2>
			<form class="form-horizontal add_form">
				
				<div class="form-group">
					<label for="inputPassword3" class="col-md-4 control-label">所属课程：</label>
					<div class="col-md-7">						
						<select class="form-control" name="deptname" id="courseName">
							<option value="">请选择所属课程</option>
							<option value="0">AFP</option>	
							<option value="1">CFP</option>
							<option value="2">基金从业</option>
							<option value="3">银行从业</option>
							<option value="4">中级经济师</option>
							<option value="5">会计从业</option>
							<option value="6">初级会计</option>
							<option value="7">期货从业</option>
							<option value="8">证券从业</option>
							<option value="9">其它</option>						
						</select>
					</div>					
				</div>
				<div class="form-group">
					<label for="inputPassword3" class="col-md-4 control-label">话术场景名称：</label>
					<div class="col-md-7">
						<input type="text" class="form-control" id="patterTypeName"> 
					</div>					
				</div>
				<p class="help-block" id="contentTypeNameError"></p>
				
				<div class="form-group">
					<div class="col-md-offset-4 col-md-6">
						<a href="javascript:;" class="btn" id="savePatterType" onclick="addPatterType()">保 存</a>
						<a href="javascript:;" class="btn" id="saveChPatter" onclick="saveChPatter()">保 存</a>						
						<a href="javascript:;"  class="btn"  onclick="wuorder.CloseDiv('patterType','fade')" style="background: #4c7cba;">取消</a>
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
                <button class="btn navbar-right" id="patterTypeDel" style="background: #4c7cba;" onclick="delpatter()">确定</button>                    
           </div>
        </div>
      </div>	
<script type="text/javascript" src="../common/js/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="../common/js/angular.min.js"></script>
<script type="text/javascript" src="../common/js/winTip.js"></script>
<script type="text/javascript" src="../common/js/boot.js"></script>
<script type="text/javascript" src="../patter/js/patterType.js"></script>
</body>
</html>