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
	<title>角色管理</title>
</head>
<body ng-app="role" ng-controller="roleCtrl">
	<div class="meun-btn">
       	<ul class="nav navbar-nav">
			<li onclick="wuorder.ShowDiv('add-orle','fade')">
				<a href="javascript:;" class="create"><span class="glyphicon glyphicon-plus"></span>增加</a>
			</li>
			<li class="orange" onclick="changeRole()">
				<a href="javascript:;" class="create"><span class="glyphicon glyphicon-pencil"></span>修改</a>
			</li>
			<li class="red" onclick="show()">
				<a href="javascript:;" class="create"><span class="glyphicon glyphicon-minus"></span>删除</a>
			</li>
			<li class="purple" style="background: #880374" onclick="assess()">
					<a href="javascript:;" class="create"><span class="glyphicon glyphicon-link"></span>分配权限</a>
			</li>						
		</ul>
	</div>
    <div class="bs-example table-responsive" data-example-id="simple-table" id="list"> 
    	<div class="coursePanel">
		  <span class="glyphicon glyphicon-th-list"></span>角色管理	  
		</div>               
         <table class="table table-bordered table-hover table-striped">                    
               <tr class="henglan">
                   <td class="text-primary" style="width: 40px;"><input type="checkbox" class="all" /></td>
                   <td class="text-primary" style="width: 60px;">序号</td>
                   <td class="text-primary" style="width: 200px;">角色名称</td>
                   <td class="text-primary" style="width: 140px;">所属部门</td>
                   <td class="text-primary">权限</td>                           
               </tr> 
              <tr ng-repeat="infor in list"  data-id="{{infor.roleid}}">
                  <td><input type="checkbox" class="{{infor.roleid}}"/></td>
                  <td ng-bind="{{(currentPage-1)*pageSize+($index+1)}}"></td>
                  <td ng-bind="infor.rolename"></td>
                  <td ng-bind="infor.deptname" data-id="{{infor.deptid}}"></td>
                  <td ng-bind="infor.menuname"></td>                             
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
	<!-- 增加 -->
	<div id="add-orle" class="ui-wrap">
		<div class="addnew-wrap">
			<h2 class="wrap-tit">角色管理_增加<span class="close closebtn"  onclick="wuorder.CloseDiv('add-orle','fade')">×</span></h2>
			
			<form class="form-horizontal add_form">
				<div class="form-group">
					<label for="inputPassword3" class="col-md-3 control-label">角色名称：</label>
					<div class="col-md-8">
						<input type="text" class="form-control" name="rolename" id="rolename"> 
					</div>					
				</div>
				<p class="help-block" id="rolenameError"></p>
				<div class="form-group">
					<label for="inputPassword3" class="col-md-3 control-label">所属部门：</label>
					<div class="col-md-8">
						<select class="form-control" name="deptname" id="deptname">
							<option value="">请选择部门</option>
							<option ng-repeat="option in deptname" value="{{option.deptid}}">{{option.deptname}}</option>							
						</select>
					</div>					
				</div>
				<p class="help-block" id="deptnameError"></p>							
				<div class="form-group">
					<div class="col-md-offset-4 col-md-6">						
						<input type="button" onclick="addRole()" value="保 存" class="btn"/>
						<a href="javascript:;"  class="btn"  onclick="wuorder.CloseDiv('add-orle','fade')" style="background: #4c7cba;">取消</a>
					</div>
				</div>
			</form>
		</div>
	</div>
	<!-- 修改 -->
	<div id="change-role"  class="ui-wrap">
		<div class="addnew-wrap">
			<h2 class="wrap-tit">角色管理_修改<span class="close closebtn"  onclick="wuorder.CloseDiv('change-role','fade')">×</span></h2>
			
			<form class="form-horizontal add_form">
				<div class="form-group">
					<label for="inputPassword3" class="col-md-3 control-label">角色名称：</label>
					<div class="col-md-8">
						<input type="text" class="form-control" name="rolename" id="ch-rolename"> 
					</div>					
				</div>
				<div class="form-group">
					<label for="inputPassword3" class="col-md-3 control-label">所属部门：</label>
					<div class="col-md-8">
						<select class="form-control" name="deptname" id="ch-deptname">
							<option value="">请选择部门</option>
							<option ng-repeat="option in deptname" value="{{option.deptid}}">{{option.deptname}}</option>
						</select>
					</div>					
				</div>				
				<div class="form-group">
					<div class="col-md-offset-4 col-md-6">
						<a href="javascript:;" class="btn" onclick="edit()">保 存</a>
						<a href="javascript:;"  class="btn"  onclick="wuorder.CloseDiv('change-role','fade')" style="background: #4c7cba;">取消</a>
					</div>
				</div>
			</form>
		</div>
	</div>	
	<!-- 分配权限-->
	<div id="change-access"  class="ui-wrap">
		<div class="addnew-wrap">
			<h2 class="wrap-tit">角色管理_分配权限<span class="close closebtn" onclick="wuorder.CloseDiv('change-access','fade')">×</span></h2>
			<form class="form-horizontal add_form">
				<div class="assessCon">
					<!-- <div class="assesstree">
						<p class="assessTit"  ng-click="show=!show">
							<span class="glyphicon glyphicon-chevron-down arrow" aria-hidden="true" ng-show="show"></span>
							<span class="glyphicon glyphicon-chevron-right arrow" aria-hidden="true" ng-show="!show"></span>							
						</p>
						<ul class="assessList" ng-show="show">
							
						</ul>
					</div> -->										
				</div>						
				<div class="form-group">
					<div class="col-md-offset-4 col-md-6">
						<a href="javascript:;" class="btn" onclick="saveAssess()">保 存</a>
						<a href="javascript:;"  class="btn"  onclick="wuorder.CloseDiv('change-access','fade')" style="background: #4c7cba;">取消</a>
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
<script type="text/javascript" src="../system/js/roleMang.js"></script>
</body>
</html>