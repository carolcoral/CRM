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
	<title>行政-到账学员总表</title>
</head>
<body>
	<input class="userid" type="hidden" value="${sessionScope.login_user.userid}" id="userid" />
	<input class="roleid" type="hidden" value="${sessionScope.login_user.roleid}" id="roleid" />
	<input class="deptid" type="hidden" value="${sessionScope.login_user.deptid}" id="deptid" />
	<input id="backcurrent" type="hidden" value=""  data-id=""/>	
	<nav class="navbar navbar-default">
		<div class="container-fluid">			
			<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav">				    
					<li onclick="exportExcel();">
						<a href="javascript:;" class="create"><span class="glyphicon glyphicon-level-up"></span>导出</a>
					</li>					
					<li onclick="wuorder.ShowDiv('studentfilter','fade')">
						<a href="javascript:;" class="create"><span class="glyphicon glyphicon-sort-by-attributes"></span>筛选</a>
					</li>										
					<li onclick="checkassignall('#assgincustom')">
						<a href="javascript:;" class="create"><span class="glyphicon glyphicon-indent-right"></span>分配</a>
					</li>
					<li onclick="checkassignall('#Transfercustom')" id="zgtransfer" style="display:none">
						<a href="javascript:;" class="create"><span class="glyphicon glyphicon-retweet"></span>转移</a>
					</li>
					<form class="navbar-form navbar-left ng-pristine ng-valid" role="search">
						<div class="form-group">
							<input type="text"  id="searchname" class="form-control check_tiaoj ng-pristine ng-untouched ng-valid" placeholder="学员姓名/身份证/手机号">
						</div>
						<a href="javascript:;" class="btn" style="background: #4c7cba;" onclick="fastquery()">查询</a>
					</form>
				</ul>
			</div>			
		</div>		
	</nav>	
	<div class="table-responsive" data-example-id="simple-table">
        <table class="table table-bordered table-hover table-striped able-condensed" id="accountTable" style="width:2000px">              	                   
            <!-- <tr class="henglan">
				<th><input type="checkbox" class="all" /></th>
				<td class="serialnumber">序号</td>				
				<th class="studentName">姓名</th>				
				<th class="idCard">身份证</th>
				<th class="phone">手机</th>
				<th class="company">单位</th>				
				<th class="companyAddr">地址</th>
				<th class="belongName">招生老师</th>
                <th class="arrive_money">到账费用</th>
                <th class="matchinfo_time">到账日期</th>
                <th class="ispass">是否通过</th>
                <th>投资</th>
                <th>保险</th>
                <th>税务</th>
                <th>福利</th>
                <th>综合</th>               
                <th>详情</th>
            </tr> 
            <tr>
                <td><input type="checkbox" class="{{infor.resourceId}}"/></td>                
				<td class="serialnumber">序号</td>
                <td class="studentName">姓名</td>       
                <td class="idCard">身份证</td>
                <td class="phone">手机</td>               
                <td class="company">单位</td>       
                <td class="companyAddr">地址</td>                 
                <td class="belongName">招生老师</td>                
                <td class="arrive_money">到账费用</td>
                <td class="matchinfo_time">到账日期</td>
                <td class="ispass">是否通过</td>
                <td>投资</td>
                <td>保险</td>
                <td>税务</td>
                <td>福利</td>
                <td>综合</td>
                <td>详情</td>                    
            </tr> -->
        </table>
        <!-- 分页 -->
    </div> 
	<div id="pagination">
	     
	</div>        
	
	<!-- 到账学员总表_筛选 -->
	<div id="studentfilter"  class="ui-wrap">
		<div class="addnew-wrap">
			<h2 class="wrap-tit"><b class="addnewTit">到账学员总表_筛选</b><span class="close closebtn"  onclick="wuorder.CloseDiv('studentfilter','fade')">×</span></h2>
			
			<form class="form-horizontal add_form" action="/student/excelExportStudent.do" method="POST" id="excel">
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
					<label for="inputPassword3" class="col-md-3 control-label">学员状态</label>
					<div class="col-md-9">
						<select class="form-control" id="studentstate" name="studentstate">
							<option value="">请选择学员状态</option>	
							<option value="3">已到账 </option>	
							<option value="4">已分配</option>	
							<option value="5">已转入</option>	
							<option value="6">已通过考试</option>	
							<option value="7">已关课</option>							
						</select>	
					</div>
				</div>
				<div class="form-group">
					<label for="inputPassword3" class="col-md-3 control-label">地址</label>
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
					<label for="inputPassword3" class="col-md-3 control-label">课程</label>
					<div class="col-md-9">
						<select class="form-control" id="course" name="course">
							<option value="">请选择课程</option>							
						</select>	
					</div>
				</div>
				<div class="form-group">
					<label for="inputPassword3" class="col-md-3 control-label">金额</label>
					<div class="col-md-9">
						<input type="text" value="" name="arrive_money" id="arrive_money" class="form-control">	
					</div>
				</div>
				<div class="form-group">
					<label for="inputEmail3" class="col-md-3 control-label">收款日期</label>
					<div class="col-md-9">
						<input type="text" value="" id="taskStartTime" placeholder="开始时间" name="createStartTime" class="chioce_time">
						<span class="until">-</span>
						<input type="text" value="" id="taskEndTime" placeholder="结束时间" name="createEndTime" class="chioce_time">
					</div>
				</div>	
				<div class="form-group">
					<label for="inputPassword3" class="col-md-3 control-label">汇款方式</label>
					<div class="col-md-9">
						<input type="text" value="" name="remitWay" id="remitWay" class="form-control">	
					</div>
				</div>
				<div class="form-group">
					<label for="inputEmail3" class="col-md-3 control-label">确认到账时间</label>
					<div class="col-md-9">
						<input type="text" value="" id="deterStartTime" placeholder="开始时间" name="createStartTime" class="chioce_time">
						<span class="until">-</span>
						<input type="text" value="" id="deterEndTime" placeholder="结束时间" name="createEndTime" class="chioce_time">
					</div>
				</div>
								
				<div class="form-group">
					<div class="col-md-offset-4 col-md-6">
						<a href="javascript:;" class="btn addsave" onclick="studentfilter()">筛选</a>						
						<a href="javascript:;"  class="btn"  onclick="wuorder.CloseDiv('studentfilter','fade')" style="background: #4c7cba;">取消</a>
					</div>
				</div>
			</form>
		</div>
	</div>	
	<!-- 分配和转移 -->
	<!-- <div id="distributeTransfer"  class="ui-wrap">
		<div class="addnew-wrap">
			<h2 class="wrap-tit"><b class="addnewTit">到账学员_分配</b><span class="close closebtn"  onclick="wuorder.CloseDiv('distributeTransfer','fade')">×</span></h2>
			
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
						<span href="javascript:;" class="btn addsave" id="assignall">确定</span>	
						<span href="javascript:;" class="btn addsave" id="transfer">确定</span>						
						<span href="javascript:;" class="btn" style="background: #4c7cba;" onclick="wuorder.CloseDiv('distributeTransfer','fade')">取消</span>
					</div>
				</div>
			</form>
		</div>
	</div>	 -->
	<!-- 分配 -->
	<div id="assgincustom"  class="ui-wrap">
		<div class="addnew-wrap">
			<h2 class="wrap-tit"><b class="addnewTit">到账学员_分配</b><span class="close closebtn"  onclick="wuorder.CloseDiv('assgincustom','fade')">×</span></h2>
			
			<form class="form-horizontal add_form">
				<div class="form-group">
					<label for="inputPassword3" class="col-md-3 control-label">选择客服员</label>
					<div class="col-md-9">
						<select class="form-control" id="assginCustomer">
							 <option value="">请选择选择客服员</option> 
						</select>
					</div>
				</div>
				<div class="form-group">
					<div class="col-md-offset-4 col-md-6">						
						<span href="javascript:;" class="btn addsave"  onclick="assignall()">确定</span>						
						<span href="javascript:;" class="btn" style="background: #4c7cba;" onclick="wuorder.CloseDiv('assgincustom','fade')">取消</span>
					</div>
				</div>
			</form>
		</div>
	</div>	
	<!-- 转移 -->
	<div id="Transfercustom"  class="ui-wrap">
		<div class="addnew-wrap">
			<h2 class="wrap-tit"><b class="addnewTit">到账学员_转移</b><span class="close closebtn"  onclick="wuorder.CloseDiv('Transfercustom','fade')">×</span></h2>
			
			<form class="form-horizontal add_form">
				<div class="form-group">
					<label for="inputPassword3" class="col-md-3 control-label">选择客服员</label>
					<div class="col-md-9">
						<select class="form-control" id="TransferCustomer">
							 <option value="">请选择选择客服员</option> 
						</select>
					</div>
				</div>
				<div class="form-group">
					<div class="col-md-offset-4 col-md-6">
						<span href="javascript:;" class="btn addsave" onclick="transfer()">确定</span>									
						<span href="javascript:;" class="btn" style="background: #4c7cba;" onclick="wuorder.CloseDiv('Transfercustom','fade')">取消</span>
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
	<script type="text/javascript" src="../executive/js/accountStudent.js"></script>
</body>
</html>