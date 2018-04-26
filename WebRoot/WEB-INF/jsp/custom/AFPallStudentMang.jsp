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
	<title>客服-AFP总表学员</title>
</head>
<body>
	<input class="userid" type="hidden" value="${sessionScope.login_user.userid}" id="userid" />
	<input class="roleid" type="hidden" value="${sessionScope.login_user.roleid}" id="roleid" />
	<input class="deptid" type="hidden" value="${sessionScope.login_user.deptid}" id="deptid" />
	<p class="shuiyin"><%=loginName%></p>
	<p class="shuiyin suiyintwo"><%=loginName%></p>
	<p class="shuiyin suiyinthree"><%=loginName%></p>
	<nav class="navbar navbar-default">
		<div class="container-fluid">			
			<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav">
				    <li onclick="showFilter()">
						<a href="javascript:;" class="create"><span class="glyphicon glyphicon-sort-by-attributes"></span>筛选</a>
					</li>	
					<!-- <li onclick="exportExcel()">
						<a href="javascript:;" class="create"><span class="glyphicon glyphicon-level-up"></span>导出</a>
					</li> -->
					<li onclick="huifang()">
						<a href="javascript:;" class="create"><span class="glyphicon glyphicon-share-alt"></span>移至回访表</a>
					</li>
					<li onclick="checkassignall('#distributeTransfer')" id="zgtransfer" style="display:none">
						<a href="javascript:;" class="create"><span class="glyphicon glyphicon-retweet"></span>转移</a>
					</li>									
					<form class="navbar-form navbar-left ng-pristine ng-valid" role="search">
						<div class="form-group">
							<input type="text" name="searchname" id="search" class="form-control check_tiaoj ng-pristine ng-untouched ng-valid" placeholder="学员姓名/身份证/手机号">
						</div>
						<a href="javascript:;" class="btn" style="background: #4c7cba;" id="quk">查询</a>
					</form>
				</ul>
				<div class="filterTotal">
					<a class="btn btn-info navbar-right info-set" href="javascript:;" style="background: #46b8da;margin-top: 15px;"><b id="zongs">当前共有资源：</b><span class="text-danger" id="filterTotal">0</span>条</a>
				</div>																
			</div>
			<!-- /.navbar-collapse -->
		</div>
		<!-- /.container-fluid -->
	</nav>	
	<div class="table-responsive" data-example-id="simple-table">		
        <table class="table table-bordered table-hover table-striped able-condensed" id="AFPallTable" style="width: 3500px;border:none;">              	                   
           <!--  <tr class="henglan">
            	 <th class="" style="width: 60px;position: fixed;background: #fff;">序号</th>
            	 <th class="studentstate">学员状态</th>
            	 <th class="matchinfo_time">到账时间</th> 
            	 <th class="courseName">报名课程</th> 
            	 <th class="subjectname">报名科目</th> 
            	 <th class="resourceName">学员姓名</th> 
            	 <th class="headmaster">招生老师</th> 
            	 <th class="idCard">身份证</th> 
            	 <th class="phone">手机</th> 
            	 <th class="email">邮箱</th> 
            	 <th class="company">单位名称</th> 
            	 <th class="companyAddr">单位地址</th> 
            </tr> -->
        </table>
    </div>
	<!-- 分页 -->        
	<nav id="pagination">
	    
	</nav> 
   <!-- 筛选 -->
	<div id="filter"  class="ui-wrap">
		<div class="addnew-wrap">
			<h2 class="wrap-tit"><b class="addnewTit">总表学员_筛选</b><span class="close closebtn"  onclick="wuorder.CloseDiv('filter','fade')">×</span></h2>
			
			<form class="form-horizontal add_form" id="excel">				
				<div class="form-group">
					<label for="inputPassword3" class="col-md-3 control-label">学员姓名</label>
					<div class="col-md-9">
						<input type="text" class="form-control" id="studentName" name="studentName">
					</div>
				</div>	
				<div class="form-group">
					<label for="inputPassword3" class="col-md-3 control-label">学员状态</label>
					<div class="col-md-9">
						<select class="form-control" name="studentstate" id="studentstate">
							<option value="">请选择学员状态</option>
							<option value="0">新增</option>
							<option value="1">已成交</option>
							<option value="2">已提交</option>
							<option value="3">已到账</option>
							<option value="4">已分配</option>
							<option value="5">已转入</option>
							<option value="6">已通过考试</option>
							<option value="7">已关课</option>										
							<option value="8">已退回</option>
						</select>
					</div>
				</div>
				<div class="form-group">
					<label for="inputPassword3" class="col-md-3 control-label">手机</label>
					<div class="col-md-9">
						<input type="text" class="form-control" id="phone" name="phone">
					</div>
				</div>										
				<div class="form-group">
					<label for="inputPassword3" class="col-md-3 control-label">渠道</label>
					<div class="col-md-9">
						<select class="form-control" name="source" id="source">
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
					<label for="inputPassword3" class="col-md-3 control-label">招生老师</label>
					<div class="col-md-9">
						<select class="form-control" id="belongName" name="belongName">
							<option value="">请选择招生老师</option>							
						</select>	
					</div>
				</div>
				<div class="form-group">
					<label for="inputPassword3" class="col-md-3 control-label">班主任</label>
					<div class="col-md-9">
						<select class="form-control" id="filterCustomer">
							 <option value="">请选择班主任</option> 
						</select>
					</div>
				</div>	
				<div class="form-group">
					<label for="inputPassword3" class="col-md-3 control-label">课程</label>
					<div class="col-md-9">						
						<select class="form-control" name="source" id="courseid">
							<option value="">请选择课程</option>							
						</select>
					</div>
				</div>	
				<div class="form-group">
					<label for="inputPassword3" class="col-md-3 control-label">班级</label>
					<div class="col-md-9">
						<select class="form-control" name="banci" id="banci">
							<option value="">请选择班级</option>
							<option value="北京" <c:if test="${liststudent.banci == '北京'}">selected="selected"</c:if>>北京</option>
							<option value="深圳" <c:if test="${liststudent.banci == '深圳'}">selected="selected"</c:if>>深圳</option>
							<option value="苏州" <c:if test="${liststudent.banci == '苏州'}">selected="selected"</c:if>>苏州</option>
							<option value="南京" <c:if test="${liststudent.banci == '南京'}">selected="selected"</c:if>>南京</option>
							<option value="成都" <c:if test="${liststudent.banci == '成都'}">selected="selected"</c:if>>成都</option>
							<option value="重庆" <c:if test="${liststudent.banci == '重庆'}">selected="selected"</c:if>>重庆</option>
							<option value="上海" <c:if test="${liststudent.banci == '上海'}">selected="selected"</c:if>>上海</option>
							<option value="泰州" <c:if test="${liststudent.banci == '泰州'}">selected="selected"</c:if>>泰州</option>
							<option value="石家庄" <c:if test="${liststudent.banci == '石家庄'}">selected="selected"</c:if>>石家庄</option> 
							<option value="天津" <c:if test="${liststudent.banci == '天津'}">selected="selected"</c:if>>天津</option>
							<option value="杭州" <c:if test="${liststudent.banci == '杭州'}">selected="selected"</c:if>>杭州</option>
							<option value="郑州" <c:if test="${liststudent.banci == '郑州'}">selected="selected"</c:if>>郑州</option>
							<option value="太原" <c:if test="${liststudent.banci == '太原'}">selected="selected"</c:if>>太原</option>
							<option value="贵阳" <c:if test="${liststudent.banci == '贵阳'}">selected="selected"</c:if>>贵阳</option>
							<option value="长沙" <c:if test="${liststudent.banci == '长沙'}">selected="selected"</c:if>>长沙</option>
							<option value="昆明" <c:if test="${liststudent.banci == '昆明'}">selected="selected"</c:if>>昆明</option>
							<option value="武汉" <c:if test="${liststudent.banci == '武汉'}">selected="selected"</c:if>>武汉</option>
							<option value="西安" <c:if test="${liststudent.banci == '西安'}">selected="selected"</c:if>>西安</option>
							<option value="呼市" <c:if test="${liststudent.banci == '呼市'}">selected="selected"</c:if>>呼市</option>
							<option value="广州" <c:if test="${liststudent.banci == '广州'}">selected="selected"</c:if>>广州 </option>
							<option value="长春" <c:if test="${liststudent.banci == '长春'}">selected="selected"</c:if>>长春</option>
							<option value="太原" <c:if test="${liststudent.banci == '太原'}">selected="selected"</c:if>>太原</option>
							<option value="济南" <c:if test="${liststudent.banci == '济南'}">selected="selected"</c:if>>济南</option>
							<option value="福建" <c:if test="${liststudent.banci == '福建'}">selected="selected"</c:if>>福建</option>
							<option value="合肥" <c:if test="${liststudent.banci == '合肥'}">selected="selected"</c:if>>合肥</option>
							<option value="徐州" <c:if test="${liststudent.banci == '徐州'}">selected="selected"</c:if>>徐州</option>
							<option value="青岛" <c:if test="${liststudent.banci == '青岛'}">selected="selected"</c:if>>青岛</option>
							<option value="南昌" <c:if test="${liststudent.banci == '南昌'}">selected="selected"</c:if>>南昌</option>
							<option value="宁波" <c:if test="${liststudent.banci == '宁波'}">selected="selected"</c:if>>宁波</option>
							<option value="温州" <c:if test="${liststudent.banci == '温州'}">selected="selected"</c:if>>温州</option>
							<option value="乐山" <c:if test="${liststudent.banci == '乐山'}">selected="selected"</c:if>>乐山</option>
							<option value="广西" <c:if test="${liststudent.banci == '广西'}">selected="selected"</c:if>>广西</option>
							<option value="兰州" <c:if test="${liststudent.banci == '兰州'}">selected="selected"</c:if>>兰州 </option>
							<option value="西宁" <c:if test="${liststudent.banci == '西宁'}">selected="selected"</c:if>>西宁</option>
							<option value="银川" <c:if test="${liststudent.banci == '银川'}">selected="selected"</c:if>>银川</option>
							<option value="桂林" <c:if test="${liststudent.banci == '桂林'}">selected="selected"</c:if>>桂林</option>
							<option value="酒泉" <c:if test="${liststudent.banci == '酒泉'}">selected="selected"</c:if>>酒泉</option>
							<option value="新疆" <c:if test="${liststudent.banci == '新疆'}">selected="selected"</c:if>>新疆</option>
							<option value="榆林" <c:if test="${liststudent.banci == '榆林'}">selected="selected"</c:if>>榆林</option>
							<option value="佛山" <c:if test="${liststudent.banci == '佛山'}">selected="selected"</c:if>>佛山</option>
							<option value="东莞" <c:if test="${liststudent.banci == '东莞'}">selected="selected"</c:if>>东莞</option>
							<option value="大连" <c:if test="${liststudent.banci == '大连'}">selected="selected"</c:if>>大连</option>
							<option value="哈尔滨" <c:if test="${liststudent.banci == '哈尔滨'}">selected="selected"</c:if>>哈尔滨</option>
							<option value="沈阳" <c:if test="${liststudent.banci == '沈阳'}">selected="selected"</c:if>>沈阳</option>
							<option value="内蒙机构" <c:if test="${liststudent.banci == '内蒙机构'}">selected="selected"</c:if>>内蒙机构</option>
							<option value="山西工行" <c:if test="${liststudent.banci == '山西工行'}">selected="selected"</c:if>>山西工行</option>
							<option value="邯郸工行" <c:if test="${liststudent.banci == '邯郸工行'}">selected="selected"</c:if>>邯郸工行</option>
							<option value="晋城银行" <c:if test="${liststudent.banci == '晋城银行'}">selected="selected"</c:if>>晋城银行</option>
							<option value="晋商银行" <c:if test="${liststudent.banci == '晋商银行'}">selected="selected"</c:if>>晋商银行</option>
							<option value="朔州工行" <c:if test="${liststudent.banci == '朔州工行'}">selected="selected"</c:if>>朔州工行</option>
							<option value="太原市工行" <c:if test="${liststudent.banci == '太原市工行'}">selected="selected"</c:if>>太原市工行</option>
							<option value="嵊州工行" <c:if test="${liststudent.banci == '嵊州工行'}">selected="selected"</c:if>>嵊州工行</option>
							<option value="A网" <c:if test="${liststudent.banci == 'A网'}">selected="selected"</c:if>>A网</option>
						    <option value="C网" <c:if test="${liststudent.banci == 'A网'}">selected="selected"</c:if>>C网</option>
						</select>
					</div>
				</div>	
				
				<div class="form-group">
					<label for="inputPassword3" class="col-md-3 control-label">单位</label>
					<div class="col-md-9">
						<input type="text" class="form-control" id="company" name="company">
					</div>
				</div>
				<!-- <div class="form-group">
					<label for="inputPassword3" class="col-md-3 control-label">考试日期</label>
					<div class="col-md-9">
						<input type="text" class="form-control" id="scoretime" name="scoretime">
					</div>
				</div> -->
				<div class="form-group">
					<label for="inputEmail3" class="col-md-3 control-label">考试日期</label>
					<div class="col-md-9">
						<input type="text" value="" id="examStartTime" placeholder="开始时间" name="examStartTime" class="chioce_time">
						<input type="text" value="" id="examEndTime" placeholder="结束时间" name="examEndTime"  class="chioce_time">
					</div>
				</div>
				<div class="form-group">
					<label for="inputPassword3" class="col-md-3 control-label">是否通过</label>
					<div class="col-md-9">
						<select class="form-control" name="ispass" id="ispass">
							<option value="">请选择</option>
							<option value="0">未通过</option>
							<option value="1">通过</option>
							<option value="1">缺考</option>
						</select>
					</div>
				</div>
				<!-- <div class="form-group">
					<label for="inputPassword3" class="col-md-3 control-label">到账日期</label>
					<div class="col-md-9">
						<input type="text" class="form-control" id="arrive_time" name="arrive_time">
					</div>
				</div> -->
				<div class="form-group">
					<label for="inputEmail3" class="col-md-3 control-label">到账日期</label>
					<div class="col-md-9">
						<input type="text" value="" id="matchinfoStarttime" placeholder="开始时间" name="matchinfoStarttime" class="chioce_time">
						<input type="text" value="" id="matchinfoEndtime" placeholder="结束时间" name="matchinfoEndtime"  class="chioce_time">
					</div>
				</div>
				<div class="form-group">
					<label for="inputPassword3" class="col-md-3 control-label">上课班号</label>
					<div class="col-md-9">
						<input type="text" class="form-control" id="classNum" name="classNum">
					</div>
				</div>
				<div class="form-group">
					<label for="inputPassword3" class="col-md-3 control-label">课件版本</label>
					<div class="col-md-9">
						<input type="text" class="form-control" id="courseversion" name="courseversion">
					</div>
				</div>
				<!-- <div class="form-group">
					<label for="inputPassword3" class="col-md-3 control-label">邮寄时间</label>
					<div class="col-md-9">
						<input type="text" class="form-control" id="mailTime" name="mailTime">
					</div>
				</div>	 -->
				<div class="form-group">
					<label for="inputEmail3" class="col-md-3 control-label">邮寄时间</label>
					<div class="col-md-9">
						<input type="text" value="" id="mailStartTime" placeholder="开始时间" name="mailStartTime" class="chioce_time">
						<input type="text" value="" id="mailEndTime" placeholder="结束时间" name="mailEndTime"  class="chioce_time">
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
                <button class="btn navbar-right" id="patterTypeDel" style="background: #4c7cba;" onclick="delmypatter()">确定</button>                    
           </div>
        </div>
      </div> 
    <!-- 分配和转移 -->
	<div id="distributeTransfer"  class="ui-wrap">
		<div class="addnew-wrap">
			<h2 class="wrap-tit"><b class="addnewTit">总表学员_转移</b><span class="close closebtn"  onclick="wuorder.CloseDiv('distributeTransfer','fade')">×</span></h2>
			
			<form class="form-horizontal add_form">
				<div class="form-group">
					<label for="inputPassword3" class="col-md-3 control-label">选择客服员</label>
					<div class="col-md-9">
						<select class="form-control" id="chooseCustomer">
							 <option value="">请选择选择客服员</option> 
						</select>
					</div>
				</div>
				<div class="form-group">
					<div class="col-md-offset-4 col-md-6">
						<a href="javascript:;" class="btn addsave" onclick="transfer()">确定</a>											
						<a href="javascript:;" class="btn" style="background: #4c7cba;" onclick="wuorder.CloseDiv('distributeTransfer','fade')">取消</a>
					</div>
				</div>
			</form>
		</div>
	</div>
         
	<script type="text/javascript" src="../common/js/jquery-1.11.1.min.js"></script>
	<script type="text/javascript" src="../common/js/angular.min.js"></script>
	<script type="text/javascript" src="../common/js/winTip.js"></script>
	<script type="text/javascript" src="../common/js/boot.js"></script>
	<script type="text/javascript" src="../common/js/jquery.datetimepicker.js"></script>
	<script type="text/javascript" src="../common/js/mydefinel.js"></script>
	<script type="text/javascript" src="../common/js/js-extend.js"></script>	
	<script type="text/javascript" src="../common/js/ajaxPage.js"></script>	
	<script type="text/javascript" src="../custom/js/AFPallStudentMang.js"></script>
	
</body>
</html>