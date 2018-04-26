<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
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
	<link rel="stylesheet" type="text/css" href="../system/css/userMang.css" />
	<title>菜单管理</title>
</head>
<body ng-app="menu" ng-controller="menuCtrl">
	<div class="meun-btn">
       	<ul class="nav navbar-nav option">
			<li onclick="wuorder.ShowDiv('addmeun','fade')">
				<a href="javascript:;" class="create"><span class="glyphicon glyphicon-plus"></span>增加</a>
			</li>
			<li class="orange" onclick="changeMenu()">
				<a href="javascript:;" class="create"><span class="glyphicon glyphicon-pencil"></span>修改</a>
			</li>
			<li class="red" onclick="showTip()">
					<a href="javascript:;" class="create"><span class="glyphicon glyphicon-minus"></span>删除</a>
			</li>
		</ul>
	</div>
    <div class="menuCon"> 
    	<div class="coursePanel">
		  <span class="glyphicon glyphicon-th-list"></span>菜单管理	  
		</div>  
    	<div ng-repeat="infors in list" class="menutree">	
    		<span class="glyphicon glyphicon-chevron-down sildup arrow" aria-hidden="true"  ng-click="Financial_show=!Financial_show"></span>                      		
    		<p class="menuTit"  data-id="{{infors.menuid}}" ng-class="{menuactive : active}" ng-click="active = !active">           			
    			<b ng-bind="infors.menuname"></b>
     		<b ng-bind="infors.menuno"  style="display:none"></b>
     		<b ng-bind="infors.menuname"  style="display:none"></b>
     		<b ng-bind="infors.menucode"  style="display:none"></b>
     		<b ng-bind="infors.menuurl"  style="display:none"></b>
     		<b ng-bind="infors.menuimgurl"  style="display:none"></b>
    		</p>
            <p class="menuList" ng-show="!Financial_show" ng-class="{menuactive : color.active}" ng-click="color.active = !color.active" ng-repeat="infor in infors.children" data-id="{{infor.menuid}}">
            	<span class="glyphicon glyphicon-bookmark flag"></span>
            	<b ng-bind="infor.menuParaname" style="display:none"></b>	                    	                    	
     		<b ng-bind="infor.menuno"  style="display:none"></b>
     		<b ng-bind="infor.menuname"></b>			            		
     		<b ng-bind="infor.menucode"  style="display:none"></b>
     		<b ng-bind="infor.menuurl"  style="display:none"></b>
     		<b ng-bind="infor.menuimgurl"  style="display:none"></b>  	                    	
            </p> 
    	</div>            
    </div> 
	<!-- 菜单管理_增加 -->
	<div id="addmeun" class="ui-wrap">
		<div class="addnew-wrap">
			<h2 class="wrap-tit">菜单管理_增加<span class="close closebtn"  onclick="wuorder.CloseDiv('addmeun','fade')">×</span></h2>			
			<form class="form-horizontal add_form">
				<div class="form-group">
					<label for="inputPassword3" class="col-md-3 control-label">上级菜单：</label>
					<div class="col-md-8">						
						<select class="form-control" name="menuParaname" id="menuParaname" >
							<option  value="">请选择菜单</option>
							<option  ng-repeat="option in list" value="{{option.menuid}}">{{option.menuname}}</option>							
						</select>
					</div>									
				</div>
				<div class="form-group">
					<label for="inputPassword3" class="col-md-3 control-label">菜单序号：</label>
					<div class="col-md-8">
						<input type="text" class="form-control" name="menuno" id="menuno"> 
					</div>
				</div>
				<div class="form-group">
					<label for="inputPassword3" class="col-md-3 control-label">菜单名称：</label>
					<div class="col-md-8">
						<input type="text" class="form-control" name="menuname" id="menuname">
						<span class="help-block" id="menunameError"></span>
					</div>
				</div>
				<p class="help-block" id="menunameError"></p>	
				<div class="form-group">
					<label for="inputPassword3" class="col-md-3 control-label">菜单编码：</label>
					<div class="col-md-8">
						<input type="text" class="form-control" name="menucode" id="menucode">
					</div>
				</div>				
				<div class="form-group">
					<label for="inputPassword3" class="col-md-3 control-label">菜单URL：</label>
					<div class="col-md-8">
						<input type="text" class="form-control" name="menuurl" id="menuurl">
					</div>
				</div>
							
				<div class="form-group">
					<label for="inputPassword3" class="col-md-3 control-label">菜单图片：</label>
					<div class="col-md-8">
						<input type="text" class="form-control" name="menuimgurl" id="menuimgurl" disabled="disabled">
					</div>
				</div>
				
				<p class="help-block" ng-model="errorName"></p>
				<div class="form-group">
					<div class="col-md-offset-4 col-md-6">						
						<input type="button" onclick="addMenu()" value="保 存" class="btn"/>
						<a href="javascript:;"  class="btn"  onclick="wuorder.CloseDiv('addmeun','fade')" style="background: #4c7cba;">取消</a>
					</div>
				</div>
			</form>
		</div>
	</div>
	<!-- 菜单理_修改-->
	<div id="meunchange"  class="ui-wrap">
		<div class="addnew-wrap">
			<h2 class="wrap-tit">菜单理_修改<span class="close closebtn"  onclick="wuorder.CloseDiv('meunchange','fade')">×</span></h2>
			<form class="form-horizontal add_form">
				<div class="form-group">
					<label for="inputPassword3" class="col-md-3 control-label">上级菜单：</label>
					<div class="col-md-8">						
						<select class="form-control" name="menuParaname" id="ch-upmenu">
							<option  value="">请选择菜单</option>
							<option  ng-repeat="option in list" value="{{option.menuid}}">{{option.menuname}}</option>
						</select>
					</div>									
				</div>
				<div class="form-group">
					<label for="inputPassword3" class="col-md-3 control-label">菜单序号：</label>
					<div class="col-md-8">
						<input type="text" class="form-control" name="menuno" id="ch-menuno"> 
					</div>
				</div>
				<div class="form-group">
					<label for="inputPassword3" class="col-md-3 control-label">菜单名称：</label>
					<div class="col-md-8">
						<input type="text" class="form-control" id="ch-menuname">						
					</div>
				</div>
				<div class="form-group">
					<label for="inputPassword3" class="col-md-3 control-label">菜单编码：</label>
					<div class="col-md-8">
						<input type="text" class="form-control" name="menucode" id="ch-menucode">
					</div>
				</div>				
				<div class="form-group">
					<label for="inputPassword3" class="col-md-3 control-label">菜单URL：</label>
					<div class="col-md-8">
						<input type="text" class="form-control" name="menuurl" id="ch-menuurl">
					</div>
				</div>				
				<div class="form-group">
					<label for="inputPassword3" class="col-md-3 control-label">菜单图片：</label>
					<div class="col-md-8">
						<input type="text" class="form-control" name="menuimgurl" id="ch-menuimgurl">
					</div>
				</div>				
				<div class="form-group">
					<div class="col-md-offset-4 col-md-6">						
						<input type="button" onclick="meunsave()" value="保 存" class="btn"/>
						<a href="javascript:;"  class="btn"  onclick="wuorder.CloseDiv('meunchange','fade')" style="background: #4c7cba;">取消</a>
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
                <button class="btn navbar-right" id="queding" style="background: #4c7cba;" onclick="del()">确定</button> 
           </div>
        </div>
      </div>
<script type="text/javascript" src="../common/js/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="../common/js/angular.min.js"></script>
<script type="text/javascript" src="../common/js/winTip.js"></script>
<script type="text/javascript" src="../common/js/boot.js"></script>
<script type="text/javascript" src="../system/js/login.js"></script>
<script type="text/javascript" src="../system/js/menuMang.js"></script>
</body>
</html>