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
	<title>证券回访</title>
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
					<li onclick="wuorder.ShowDiv('filter','fade')">
						<a href="javascript:;" class="create"><span class="glyphicon glyphicon-sort-by-attributes"></span>筛选</a>
					</li>										
					<li onclick="exportExcel()">
						<a href="javascript:;" class="create"><span class="glyphicon glyphicon-level-up"></span>导出</a>
					</li>										
					<form class="navbar-form navbar-left ng-pristine ng-valid" role="search">
						<div class="form-group">
							<input type="text" name="search" id="search" class="form-control check_tiaoj ng-pristine ng-untouched ng-valid" placeholder="学员姓名/身份证/手机号">
						</div>
						<a href="javascript:void(0);" onclick="fastquery()" class="btn" style="background: #4c7cba;">查询</a>
					</form>
				</ul>
				<div class="filterTotal">
					<a class="btn btn-info navbar-right info-set" href="javascript:;" style="background: #46b8da;margin-top: 15px;"><b id="zongs">当前共有资源：</b><span class="text-danger" id="filterTotal">0</span>条</a>
				</div>																
				<div class="setcolorbtn">
					<a href="javascript:;" style="background:#fb8c8c" onclick="setColor(this,'resourceTable')">粉红</a>
					<a href="javascript:;" style="background:#65bfd4" onclick="setColor(this,'resourceTable')">深蓝</a>
					<a href="javascript:;" style="background:#f1a766" onclick="setColor(this,'resourceTable')">土黄</a>
					<a href="javascript:;" style="background:#00FFFF" onclick="setColor(this,'resourceTable')">亮蓝</a>
					<a href="javascript:;" style="background:#912CEE" onclick="setColor(this,'resourceTable')">紫色</a>
					<a href="javascript:;" style="background:#00CD00" onclick="setColor(this,'resourceTable')">绿色</a>
					<div class="delcolor" style="background:rgba(255, 255, 255, 1)" onclick="setColor(this,'resourceTable')"><a href="javascript:;" style="background:#4c7cba">取消颜色标记</a></div>
				</div>
			</div>
			<!-- /.navbar-collapse -->
		</div>
		<!-- /.container-fluid -->
	</nav>	
	<div class="table-responsive" data-example-id="simple-table">		
        <table class="table table-bordered table-hover table-striped able-condensed" id="resourceTable" style="width: 2400px;border:none;">              	                   
            <tr class="henglan">
            	<th class="resourceName" style="width: 60px;position: fixed;background: #fff;">序号</th>
				<th class="resourceName" style="width: 60px;position: fixed;background: #fff;left: 59px;">姓名</th>
                <th class="phone" style="padding-left: 120px;">手机</th>
				<th class="studentstate">学员状态</th>
				<th class="headmaster">班主任</th>
				<th class="address">地区</th>
				<th class="idCard" style="width: 202px;">身份证号</th>
				<th class="baokaopassword">报考密码</th>
				<th class="email">邮箱</th>
				<th class="belongName">招生老师</th>
                <th class="arrive_money">到账费用</th>   
                <th class="arrive_time">到账日期</th>
                <th class="weixin">微信</th>
                 <th class="courseversion">课件打印</th>
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
	<nav id="pagination">
	    
	</nav> 
   <!-- 筛选 -->
	<div id="filter"  class="ui-wrap">
		<div class="addnew-wrap">
			<h2 class="wrap-tit"><b class="addnewTit">回访_筛选</b><span class="close closebtn"  onclick="wuorder.CloseDiv('filter','fade')">×</span></h2>
			
			<form class="form-horizontal add_form" id="excel">				
				<div class="form-group">
					<label for="inputPassword3" class="col-md-3 control-label">学员姓名</label>
					<div class="col-md-9">
						<input type="text" class="form-control" id="studentName" name="studentName">
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
					<label for="inputPassword3" class="col-md-3 control-label">课程</label>
					<div class="col-md-9">						
						<select class="form-control" name="source" id="courseid">
							<option value="">请选择课程</option>							
						</select>
					</div>
				</div>	
				<div class="form-group">
					<label for="inputPassword3" class="col-md-3 control-label">科目</label>
					<div class="col-md-9">						
						<select class="form-control" name="subject" id="subject">
							<option value="">请选择科目</option>							
						</select>
					</div>
				</div>
				<div class="form-group">
					<label for="inputPassword3" class="col-md-3 control-label">学员状态</label>
					<div class="col-md-9">
						<select class="form-control" name="studentstate" id="studentstate">
							<option value="">请选择状态</option>	
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
					<label for="inputPassword3" class="col-md-3 control-label">学员评价</label>
					<div class="col-md-9">
						<select class="form-control" name="studentEvaluate" id="studentEvaluate">
							<option value="">请选择评价</option>	
							<option value="优">优</option>
							<option value="良">良</option>
							<option value="中">中</option>
							<option value="差">差</option>
						</select>
					</div>
				</div>
				<div class="form-group">
					<label for="inputPassword3" class="col-md-3 control-label">是否通过</label>
					<div class="col-md-9">
						<select class="form-control" name="ispass" id="ispass">
							<option value="">请选择渠道</option>
							<option value="0">未通过</option>
							<option value="1">通过</option>
							<option value="1">缺考</option>
						</select>
					</div>
				</div>
				<div class="form-group">
					<label for="inputPassword3" class="col-md-3 control-label">地域</label>
					<div class="col-md-9">
						<select class="form-control" name="address" id="address">
							<option value="">请选择地域</option>
							<option value="北京">北京</option>
							<option value="上海">上海</option>
							<option value="广东">广东</option>
							<option value="天津">天津</option>
							<option value="河北">河北</option>
							<option value="内蒙古">内蒙古</option>
							<option value="吉林">吉林</option>
							<option value="辽宁">辽宁</option>
							<option value="黑龙江">黑龙江</option>
							<option value="福建">福建</option>
							<option value="安徽">安徽</option>
							<option value="山西">山西</option>
							<option value="重庆">重庆</option>
							<option value="江苏">江苏</option>
							<option value="江西">江西</option>
							<option value="山东">山东</option>
							<option value="浙江">浙江</option>
							<option value="河南">河南</option>
							<option value="湖北">湖北</option>
							<option value="湖南">湖南</option>
							<option value="海南">海南</option>
							<option value="广西">广西</option>
							<option value="贵州">贵州</option>
							<option value="四川">四川</option>
							<option value="西藏">西藏</option>
							<option value="云南">云南</option>
							<option value="甘肃">甘肃</option>
							<option value="宁夏">宁夏</option>
							<option value="青海">青海</option>
							<option value="陕西">陕西</option>
							<option value="新疆">新疆</option>							
						</select>
					</div>
				</div>
				<div class="form-group">
					<label for="inputPassword3" class="col-md-3 control-label">考试日期</label>
					<div class="col-md-9">
						<input type="text" class="form-control" id="scoretime" name="scoretime">
					</div>
				</div>
				<div class="form-group">
					<label for="inputPassword3" class="col-md-3 control-label">到账日期</label>
					<div class="col-md-9">
						<input type="text" class="form-control" id="arrive_time" name="arrive_time">
					</div>
				</div>	
				<div class="form-group">
					<label for="inputPassword3" class="col-md-3 control-label">颜色</label>
					<div class="col-md-9">
						<select class="form-control" name="studentColor" id="studentColor">
							<option value="">请选择颜色</option>
							<option value="rgb(251, 140, 140)">粉红</option>
							<option value="rgb(101, 191, 212)">深蓝</option>
							<option value="rgb(241, 167, 102)">土黄</option>
							<option value="rgb(0, 255, 255)">亮蓝</option>
							<option value="rgb(145, 44, 238)">紫色</option>
							<option value="rgb(0, 205, 0)">绿色</option>
						</select>
					</div>
				</div>
				<div class="form-group">
					<label for="inputPassword3" class="col-md-3 control-label">选择班主任</label>
					<div class="col-md-9">
						<select class="form-control" id="chooseCustomer">
							 <option value="">请选择选择客服员</option> 
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
                <button class="btn navbar-right" id="patterTypeDel" style="background: #4c7cba;" onclick="delmypatter()">确定</button>                    
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
	<script type="text/javascript" src="../common/js/mydefinel.js"></script>
	<script type="text/javascript" src="../custom/js/zhengQuanStudentMang.js"></script>
	<script type="text/javascript" src="../custom/js/pubilcAllstudent.js"></script>
</body>
</html>