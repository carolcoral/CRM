<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%  
		String loginName = session.getAttribute("loginName").toString();
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
	<title>特殊学员</title>
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
					<!-- <li onclick="wuorder.ShowDiv('filter','fade')">
						<a href="javascript:;" class="create"><span class="glyphicon glyphicon-sort-by-attributes"></span>筛选</a>
					</li>										
					<li onclick="exportExcel()">
						<a href="javascript:;" class="create"><span class="glyphicon glyphicon-level-up"></span>导出</a>
					</li> -->
					
					<li onclick="rowDfine('passsTable')" id="diyrow">
						<a href="javascript:;" class="create" id="row-define" data-reveal-id="row-def"><span class="glyphicon glyphicon-retweet"></span>选择列</a>						
					</li>
										
					<form class="navbar-form navbar-left ng-pristine ng-valid" role="search">
						<div class="form-group">
							<input type="text" name="search" id="search" class="form-control check_tiaoj ng-pristine ng-untouched ng-valid" placeholder="学员姓名/身份证/手机号">
						</div>
						<a href="javascript:;" onclick="passquery()" class="btn" style="background: #4c7cba;">查询</a>
					</form>
				</ul>																
			</div>
			<!-- /.navbar-collapse -->
		</div>
		<!-- /.container-fluid -->
	</nav>	
	<div class="table-responsive" data-example-id="simple-table">
		<table class="table table-bordered table-hover table-striped able-condensed" id="fixedTable" style="border-top: none;width: 640px;position: fixed;background: #fff;">
			<tr class="henglan" style="height:35px">
			    <th class='check' style='width: 60px;position: fixed;'><input type='checkbox' class='all' /></th>
			    <th class='details' style='width: 60px;position: fixed;left: 59px;'>详情</th>
            	<th class="resourceName" style="width: 60px;position: fixed;left:118px;">序号</th>
				<th class="resourceName" style="width: 100px;position: fixed;left: 177px;">姓名</th>
                <th class="idCard" style="width: 180px;position: fixed;left: 276px;">身份证号</th>				
                <th class="phone" style="width: 185px;position: fixed;left: 455px;">手机</th>              
            </tr>            
		</table>	
		<div style="padding-left: 639px;">
	        <table class="table table-bordered table-hover table-striped able-condensed" id="passsTable" style="width: 3500px;border:none;">              	                   
	           <tr class="henglan" id="passHeader">            	
					<th class="studentstate">学员状态</th>
	                <th class="dealtime" style="width:180px;">成交时间</th>   
	                <th class="customerName" style="width: 120px;">班主任</th>              
	                <th class="school">毕业院校</th>
	                <th class="education">学历</th>
	                <th class="preferinfo">优惠信息</th>
	                <th class="LCWname">LCW用户名</th>
	                <th class="LCWpassword">LCW密码</th>
	                <th class="email">邮箱</th>
	                <th class="company">单位</th>
	                <th class="department">工作部门</th>
	                <th class="position">职务</th>
	                <th class="companyTel" >单位电话</th>
	            </tr>   
	        </table>        
		</div>	
    </div>
	<!-- 分页 -->        
	<nav id="pagination">
	    
	</nav> 
    <!-- 学员管理_新建-->
	<div id="addresource"  class="ui-wrap">
		<div class="addnew-wrap">
			<h2 class="wrap-tit"><b class="addnewTit">学员管理_新建</b><span class="close closebtn"  onclick="wuorder.CloseDiv('addresource','fade')">×</span></h2>
			
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
	            <div class="form-group">
                    <label for="inputPassword3" class="col-md-3 control-label">家庭住址</label>
                    <div class="col-md-9">
                      	<input type="text" class="form-control" name="homeAddr" id="homeAddr">
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
						<a href="javascript:;"  class="btn"  onclick="wuorder.CloseDiv('addresource','fade')" style="background: #4c7cba;">取消</a>
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
                <button class="btn navbar-right" id="patterTypeDel" style="background: #4c7cba;" onclick="delmypatter()">确定</button>                    
           </div>
        </div>
      </div> 
    <!-- 定义列 --> 
    <div id="row-def">
      <h2 class="wrap-tit"><b class="addnewTit">定义列</b><span class="close closebtn" onclick="wuorder.CloseDiv('row-def','fade')">×</span></h2> 
      <div class="contain"> 
          <p class="showHideTit">总字段<span>显示</span></p> 
          <select multiple class="form-control option_frame" id="hide"title='隐藏'>                    
         		<option value="studentstate">学员状态</option>
         		<option value="customerName">班主任</option>
         		<option value="tel">固定电话</option>
         		<option value="email">邮箱</option>
         		<option value="companyAddr">公司地址</option>
         		<option value="courseName">课程</option>
         		<option value="subjectname">科目</option>
         		<option value="arrive_time">收款时间</option>
         		<option value="arrive_money">收款金额</option>
         		<option value="remitWay">汇款方式</option>
         		<option value="LCWname">LCW用户名</option>
         		<option value="LCWpassword">LCW密码</option>
         		<option value="belongName">招生老师</option>
         		<option value="ispass">是否通过</option>
         		<option value="baokaopassword">报考密码</option>
         		<option value="banci">班级</option>
         		<option value="customer_time">分配客服时间</option>
         		<option value="isjieye">是否协助结业</option>          
          </select>  
          <div class="button_frame1">  
            <button class="button glyphicon glyphicon-chevron-left" id="leftarrow" onclick="leftarrow()"></button>
            <button class="button glyphicon glyphicon-chevron-right" id="rightarrow" onclick="rightarrow()"></button>  
            <!-- <button class="button glyphicon glyphicon-indent-right" id="leftarrow_all" onclick="leftarrowAll('passsTable')"></button>  
            <button class="button glyphicon glyphicon-indent-left" id="rightarrow_all" onclick="rightarrowAll('passsTable')"></button>   -->
         </div>          
          <select multiple class="form-control option_frame" id="show" title='显示'>
          		<option value="studentstate">学员状态</option>
         		<option value="customerName">班主任</option>
         		<option value="tel">固定电话</option>
         		<option value="email">邮箱</option>
         		<option value="companyAddr">公司地址</option>
         		<option value="courseName">课程</option>
         		<option value="subjectname">科目</option>
         		<option value="arrive_time">收款时间</option>
         		<option value="arrive_money">收款金额</option>
         		<option value="remitWay">汇款方式</option>
         		<option value="LCWname">LCW用户名</option>
         		<option value="LCWpassword">LCW密码</option>
         		<option value="belongName">招生老师</option>
         		<option value="ispass">是否通过</option>
         		<option value="baokaopassword">报考密码</option>
         		<option value="banci">班级</option>
         		<option value="customer_time">分配客服时间</option>
         		<option value="isjieye">是否协助结业</option>
          </select>  
          <div class="button_frame1">  
            <button class="button glyphicon glyphicon-chevron-up" id="uparrow" onclick="uparrow()"></button>  
            <button class="button glyphicon glyphicon-chevron-down" id="downarrow" onclick="downarrow()"></button>  
           <!--  <button class="button glyphicon glyphicon-open" id="toparrow" onclick="toparrow('passsTable')"></button>  
            <button class="button glyphicon glyphicon-save" id="bottomarrow" onclick="bottomarrow('passsTable')"></button>   -->
          </div>
      </div>  
      <div class="footer">  
        <div class="button_frame2">  
          <button class="confirm_button" id="confirm" onclick="deterrow()">确认</button>  
        </div>  
      </div>  
    </div>
    <div id="showdeiatil"  style="position: absolute;width: 100%;height:1px;background: #ccc;z-index: 999;left: 0;top: 0;">
      	<iframe name="detailiframe" id="detailiframe" frameBorder=0 scrolling=no width="100%" height="100%" allowTransparency="true"></iframe>
    </div>       
	<script type="text/javascript" src="../common/js/jquery-1.11.1.min.js"></script>
	<script type="text/javascript" src="../common/js/winTip.js"></script>
	<script type="text/javascript" src="../common/js/boot.js"></script>
	<script type="text/javascript" src="../common/js/jquery.datetimepicker.js"></script>
	<script type="text/javascript" src="../common/js/js-extend.js"></script>	
	<script type="text/javascript" src="../common/js/ajaxPage.js"></script>	
    <script type="text/javascript" src="../custom/js/specialStudentMang.js"></script>
</body>
</html>