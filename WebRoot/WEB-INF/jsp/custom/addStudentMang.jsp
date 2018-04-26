<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%  
		String loginName = session.getAttribute("loginName").toString();
 %>
 <%
	pageContext.setAttribute("PATH", request.getContextPath());
			
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
	<link rel="stylesheet" type="text/css" href="../common/css/jquery.datetimepicker.css" />
	<link rel="stylesheet" type="text/css" href="../operate/css/resourcesMang.css" />
	<title>新增学员</title>
</head>
<body>
	<input class="userid" type="hidden" value="${sessionScope.login_user.userid}" id="userid" />
	<input class="roleid" type="hidden" value="${sessionScope.login_user.roleid}" id="roleid" />
	<input class="deptid" type="hidden" value="${sessionScope.login_user.deptid}" id="deptid" />
	<input id="backcurrent" type="hidden" value=""  data-id=""/>
	<p class="shuiyin"><%=loginName%></p>
	<p class="shuiyin suiyintwo"><%=loginName%></p>
	<p class="shuiyin suiyinthree"><%=loginName%></p>
	<nav class="navbar navbar-default">
		<div class="container-fluid">			
			<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav">
					<li onclick="wuorder.ShowDiv('addresource','fade')">
						<a href="javascript:;" class="create"><span class="glyphicon glyphicon-stats"></span>新建</a>
					</li>
					<li onclick="sendexEcutive()">
						<a href="javascript:;" class="create"><span class="glyphicon glyphicon-share-alt"></span>提交</a>
					</li>
					<li onclick="wuorder.ShowDiv('filter','fade')">
						<a href="javascript:;" class="create"><span class="glyphicon glyphicon-sort-by-attributes"></span>筛选</a>
					</li>	
					<li onclick="delResource()">
						<a href="javascript:;" class="create"><span class="glyphicon glyphicon-trash"></span>删除</a>
					</li>									
					<form class="navbar-form navbar-left ng-pristine ng-valid" role="search">
						<div class="form-group">
							<input type="text" name="search" id="search" class="form-control check_tiaoj ng-pristine ng-untouched ng-valid" placeholder="学员姓名/身份证/手机号">
						</div>
						<a href="javascript:void(0);"  onclick="fastquery()" class="btn" style="background: #4c7cba;">查询</a>
					</form>
				</ul>																
			</div>
			<!-- /.navbar-collapse -->
		</div>
		<!-- /.container-fluid -->
	</nav>	
	<div class="table-responsive" data-example-id="simple-table">		
        <table class="table table-bordered table-hover table-striped able-condensed" id="resourceTable" style="width: 3500px;border:none;">              	                   
            <tr class="henglan">
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
					
            </tr>
           <!--  <tr class="henglan">
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
	<nav id="filterpagination">
	    
	</nav> 
   <!-- 学员管理_新建-->
	<div id="addresource"  class="ui-wrap">
		<div class="addnew-wrap">
			<h2 class="wrap-tit"><b class="addnewTit">新增学员_新建</b><span class="close closebtn"  onclick="clearadd()">×</span></h2>
			
			<form class="form-horizontal add_form">
				<div class="form-group">
					<label for="inputPassword3" class="col-md-3 control-label">姓名</label>
					<div class="col-md-9">
						<input type="text" class="form-control" id="studentName">
						<span class="help-block" id="studentNameError"></span>
					</div>
				</div>
				<div class="form-group">
					<label for="inputPassword3" class="col-md-3 control-label">手机</label>
					<div class="col-md-9">
						<input type="text" class="form-control" id="phone">	
						<span class="help-block" id="phoneError"></span>					
					</div>
				</div>
				<!-- <div class="form-group">					
					<label for="inputPassword3" class="col-md-3 control-label">渠道</label>
					<div class="col-md-9">
						<select class="form-control" id="source">
							<option value="0">自建</option>
							<option value="1">课程注册</option>
							<option value="2">在线注册</option>
							<option value="3">app下载注册</option>
							<option value="4">电话咨询</option>
							<option value="5">金考网注册用户</option>
							<option value="6">线上渠道</option>
							<option value="7">在线咨询</option>										
							<option value="8">大库资源</option>
							<option value="9">在线购买</option>
						</select>
						<span class="help-block" id="sourceError"></span>	
					</div>
				</div>		 -->		
				<div class="form-group">
					<label for="inputPassword3" class="col-md-3 control-label">身份证</label>
					<div class="col-md-9">
						<input type="text" class="form-control" id="idCard">	
						<span class="help-block" id="idCardError"></span>					
					</div>
				</div>
				<div class="form-group">
	                  <label for="inputPassword3" class="col-md-3 control-label">毕业院校</label>
	                  <div class="col-md-9">
	                    <input type="text" class="form-control" name="school" id="school">                            
	                  </div>
                </div>	
                <div class="form-group">
	                  <label for="inputPassword3" class="col-md-3 control-label">学历</label>
	                  <div class="col-md-9">
	                    <input type="text" class="form-control" name="education" id="education">
	                  </div>
                </div>	
                <div class="form-group">
	                  <label for="inputPassword3" class="col-md-3 control-label">邮箱</label>
	                  <div class="col-md-9">
	                    <input type="text" class="form-control" name="email" id="email">
	                    <span class="help-block" id="emailError"></span>
	                  </div>
                </div>	
                <div class="form-group">
	                  <label for="inputPassword3" class="col-md-3 control-label">单位</label>
	                  <div class="col-md-9">
	                    <input type="text" class="form-control" name="company" id="company">
	                  </div>
                </div>	
                <div class="form-group">
		              <label for="inputPassword3" class="col-md-3 control-label">单位地址</label>
		              <div class="col-md-9">
		                <input type="text" class="form-control" name="companyAddr" id="companyAddr">
		              </div>
	            </div>
	            <div class="form-group">
                    <label for="inputPassword3" class="col-md-3 control-label">单位电话</label>
                    <div class="col-md-9">
                      <input type="text" class="form-control" name="companyTel" id="companyTel">
                    </div>
                </div>
                <div class="form-group">
	                  <label for="inputPassword3" class="col-md-3 control-label">工作部门</label>
	                  <div class="col-md-9">
	                    <input type="text" class="form-control" name="department" id="department">
	                  </div>
                </div>
                <div class="form-group">
                    <label for="inputPassword3" class="col-md-3 control-label">职务</label>
                    <div class="col-md-9">
                      	<input type="text" class="form-control" name="position" id="position">
                    </div>
                </div>	            
				<p class="help-block" id="courseMoneyError"></p>
				<div class="form-group">
					<label for="inputPassword3" class="col-md-3 control-label">备注</label>
					<div class="col-md-9">
						<input type="text" class="form-control"  id="xiaoShouNote">	
					</div>
				</div>
				<div class="form-group">
					<div class="col-md-offset-4 col-md-6">
						<a href="javascript:;" class="btn addsave" onclick="addnewStudent()">保 存</a>						
						<a href="javascript:;"  class="btn"  onclick="clearadd()" style="background: #4c7cba;">取消</a>
					</div>
				</div>
			</form>
		</div>
	</div>
	<!-- 资源管理_筛选 -->
	<div id="filter"  class="ui-wrap">
		<div class="addnew-wrap">
			<h2 class="wrap-tit"><b class="addnewTit">新增学员_筛选</b><span class="close closebtn"  onclick="wuorder.CloseDiv('filter','fade')">×</span></h2>
			
			<form class="form-horizontal add_form" id="excel">				
				<div class="form-group">
					<label for="inputPassword3" class="col-md-3 control-label">学员姓名</label>
					<div class="col-md-9">
						<input type="text" class="form-control" id="filter_studentName" name="studentName">
					</div>
				</div>										
				<div class="form-group">
					<label for="inputPassword3" class="col-md-3 control-label">渠道</label>
					<div class="col-md-9">
						<select class="form-control" name="source" id="filter_source">
							<option value="">请选择渠道</option>
							<option value="0">自建</option>
							<option value="1">课程注册</option>
							<option value="2">在线注册</option>
							<option value="3">app下载注册</option>
							<option value="4">电话咨询</option>
							<option value="5">金考网注册用户</option>
							<option value="6">线上渠道</option>
							<option value="7">在线咨询</option>										
							<option value="8">大库资源</option>
							<option value="9">在线购买</option>
						</select>
					</div>
				</div>
				<div class="form-group">
					<label for="inputPassword3" class="col-md-3 control-label">手机</label>
					<div class="col-md-9">
						<input type="text" class="form-control" id="filter_phone">
					</div>
				</div>
				<div class="form-group">
					<label for="inputPassword3" class="col-md-3 control-label">课程</label>
					<div class="col-md-9">						
						<select class="form-control" name="source" id="filter_courseid">
							<option value="">请选择课程</option>							
						</select>
					</div>
				</div>							
				<div class="form-group">
					<div class="col-md-offset-4 col-md-6">
						<a href="javascript:;" class="btn addsave" onclick="filter()">筛选</a>						
						<a href="javascript:;"  class="btn"  onclick="wuorder.CloseDiv('filter','fade')" style="background: #4c7cba;">取消</a>
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
                <div class="themistip" style="margin-bottom: 20px; color:red; font-size:14px;"><span id="sendtip">确定删除这些信息吗!</span></div>
                <button class="btn navbar-right" id="quxiao" >取消</button>                
                <button class="btn navbar-right" id="patterTypeDel" style="background: #4c7cba;" onclick="window.location.reload();">确定</button>                    
           </div>
        </div>
    </div> 
    <div id="showdeiatil"  style="position: absolute;width: 100%;height:1px;background: #ccc;z-index: 999;left: 0;top: 0;">
      	<iframe name="detailiframe" id="detailiframe" frameBorder=0 scrolling=no width="100%" height="100%" allowTransparency="true"></iframe>
    </div> 
	<script type="text/javascript" src="../common/js/jquery-1.11.1.min.js"></script>
	<script type="text/javascript" src="../common/js/angular.min.js"></script>
	<script type="text/javascript" src="../common/js/winTip.js"></script>
	<script type="text/javascript" src="../common/js/boot.js"></script>
	<script type="text/javascript" src="../common/js/jquery.datetimepicker.js"></script>
	<script type="text/javascript" src="../common/js/js-extend.js"></script>	
	<script type="text/javascript" src="../common/js/ajaxPage.js"></script>	
	<script type="text/javascript" src="../common/js/mydefinel.js"></script>
	<script type="text/javascript" src="../custom/js/addStudentMang.js"></script>
	
</body>
</html>