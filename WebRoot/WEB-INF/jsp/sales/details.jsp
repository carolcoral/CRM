<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<title>销售-资源详情</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<meta name="msapplication-tap-highlight" content="no" />
<meta name="robots" content="noindex" />
<meta http-equiv="X-UA-Compatible" content="IE=Edge,chrome=1" />
<meta name="renderer" content="webkit" />
<link rel="stylesheet" type="text/css" href="../common/css/bootstrap.min.css" />
<link rel="stylesheet" type="text/css" href="../common/css/common.css" />
<link rel="stylesheet" type="text/css" href="../common/css/jquery.datetimepicker.css" />
<link rel="stylesheet" type="text/css" href="../common/css/winTip.css" />
<link rel="stylesheet" type="text/css" href="../common/css/details.css" />
<style>
.saledetailsedit {
    width: 50%;
    box-sizing: border-box;
    padding: 20px;
    overflow: hidden;
    margin:0 auto;
}
.detailsedit {width: 100%;}
</style>
</head>
<body>
<c:forEach items="${resource}" var="resource">
	<div class="detailscom">		
		<h2 class="detailTti">基本信息
			<a class="btn navbar-left" href="javascript:;" onclick="parent.showdeiatil()" style="background-color: #337ab7;">
				<span class="glyphicon glyphicon-arrow-left" aria-hidden="true" style="font-size:20px;"></span>
			</a>
		</h2>
		<div class="detailseWrap">
			<div class="saledetailsedit">
				<form class="form-horizontal" role="form">
					<input class="userid" type="hidden" value="${sessionScope.login_user.userid}" id="userid" />
					<input class="roleid" type="hidden" value="${sessionScope.login_user.roleid}" id="roleid" />
					<input class="deptid" type="hidden" value="${sessionScope.login_user.deptid}" id="deptid" />
					<input type="hidden" value="${resource.resourceId }" id="resourceId"/>
					<input type="hidden" value="${resource.phone }" id="resourcephone"/>
					<div class="form-group">
						<label for="inputEmail3" class="col-md-3 control-label">创建者：</label>
						<div class="col-md-8">
							<input  class="form-control" type="text"  value="${resource.createrName}" disabled="disabled"/>
						</div>
					</div>
					<div class="form-group">
						<label for="inputEmail3" class="col-md-3 control-label">创建时间：</label>
						<div class="col-md-8">
							<input  class="form-control" type="text"  value="${resource.create_time }"  disabled="disabled"/>
						</div>
					</div>
								
					<div class="form-group">
						<label for="inputEmail3" class="col-md-3 control-label">渠道：</label>
						<div class="col-md-8">
							<input  class="form-control" type="text"  disabled="disabled"  value="<c:if test="${resource.source ==0}">自建</c:if><c:if test="${resource.source ==1}">课程注册</c:if><c:if test="${resource.source ==2}">在线注册</c:if><c:if test="${resource.source ==3}">app下载注册</c:if><c:if test="${resource.source ==4}">电话咨询</c:if><c:if test="${resource.source ==5}">金考网注册用户</c:if><c:if test="${resource.source ==6}">线上渠道</c:if><c:if test="${resource.source ==7}">在线注册</c:if><c:if test="${resource.source ==8}">大库资源</c:if><c:if test="${liststudent.source ==9}">在线购买</c:if>"/>
						</div>
					</div>
					<div class="form-group">
						<label for="inputEmail3" class="col-md-3 control-label">资源状态：</label>
						<div class="col-md-8">
							<input  class="form-control" type="text"  disabled="disabled" value="<c:if test="${resource.state ==0}">未分配</c:if><c:if test="${resource.state ==1}">已分配</c:if><c:if test="${resource.state ==2}">已处理</c:if>	"/>
						</div>
					</div>
					<div class="form-group">
						<label for="inputEmail3" class="col-md-3 control-label">资源分配时间：</label>
						<div class="col-md-8">
							<input  class="form-control" type="text"  disabled="disabled" value="${resource.assignTime }"/>
						</div>
					</div>
					<div class="form-group">
						<label for="inputEmail3" class="col-md-3 control-label">运营备注：</label>
						<div class="col-md-8">
							<input  class="form-control" type="text"  disabled="disabled" value="${resource.yunYingNote }"/>
						</div>
					</div>
					<div class="form-group">
						<label for="inputEmail3" class="col-md-3 control-label">归属者：</label>
						<div class="col-md-8">
							<input  class="form-control" type="text"  disabled="disabled" value="${resource.belongName }"/>
						</div>
					</div>
					<div class="form-group">
						<label for="inputEmail3" class="col-md-3 control-label">地域：</label>
						<div class="col-md-8">
							<select class="form-control" name="address" id="address">
								<option value="" <c:if test="${resource.address == ''}">selected="selected"</c:if>>请选择地域</option>
								<option value="北京" <c:if test="${resource.address == '北京'}">selected="selected"</c:if>>北京</option>
								<option value="上海" <c:if test="${resource.address == '上海'}">selected="selected"</c:if>>上海</option>
								<option value="广东" <c:if test="${resource.address == '广东'}">selected="selected"</c:if>>广东</option>
								<option value="天津" <c:if test="${resource.address == '天津'}">selected="selected"</c:if>>天津</option>
								<option value="河北" <c:if test="${resource.address == '河北'}">selected="selected"</c:if>>河北</option>
								<option value="内蒙古" <c:if test="${resource.address == '内蒙古'}">selected="selected"</c:if>>内蒙古</option>
								<option value="吉林"<c:if test="${resource.address == '吉林'}">selected="selected"</c:if>>吉林</option>
								<option value="辽宁"<c:if test="${resource.address == '辽宁'}">selected="selected"</c:if>>辽宁</option>
								<option value="黑龙江"<c:if test="${resource.address == '黑龙江'}">selected="selected"</c:if>>黑龙江</option>
								<option value="福建"<c:if test="${resource.address == '福建'}">selected="selected"</c:if>>福建</option>
								<option value="安徽"<c:if test="${resource.address == '安徽'}">selected="selected"</c:if>>安徽</option>
								<option value="山西"<c:if test="${resource.address == '山西'}">selected="selected"</c:if>>山西</option>
								<option value="重庆"<c:if test="${resource.address == '重庆'}">selected="selected"</c:if>>重庆</option>
								<option value="江苏"<c:if test="${resource.address == '江苏'}">selected="selected"</c:if>>江苏</option>
								<option value="江西"<c:if test="${resource.address == '江西'}">selected="selected"</c:if>>江西</option>
								<option value="山东"<c:if test="${resource.address == '山东'}">selected="selected"</c:if>>山东</option>
								<option value="浙江"<c:if test="${resource.address == '浙江'}">selected="selected"</c:if>>浙江</option>
								<option value="河南"<c:if test="${resource.address == '河南'}">selected="selected"</c:if>>河南</option>
								<option value="湖北"<c:if test="${resource.address == '湖北'}">selected="selected"</c:if>>湖北</option>
								<option value="湖南"<c:if test="${resource.address == '湖南'}">selected="selected"</c:if>>湖南</option>
								<option value="海南"<c:if test="${resource.address == '海南'}">selected="selected"</c:if>>海南</option>
								<option value="广西"<c:if test="${resource.address == '广西'}">selected="selected"</c:if>>广西</option>
								<option value="贵州"<c:if test="${resource.address == '贵州'}">selected="selected"</c:if>>贵州</option>
								<option value="四川"<c:if test="${resource.address == '四川'}">selected="selected"</c:if>>四川</option>
								<option value="西藏"<c:if test="${resource.address == '西藏'}">selected="selected"</c:if>>西藏</option>
								<option value="云南"<c:if test="${resource.address == '云南'}">selected="selected"</c:if>>云南</option>
								<option value="甘肃"<c:if test="${resource.address == '甘肃'}">selected="selected"</c:if>>甘肃</option>
								<option value="宁夏"<c:if test="${resource.address == '宁夏'}">selected="selected"</c:if>>宁夏</option>
								<option value="青海"<c:if test="${resource.address == '青海'}">selected="selected"</c:if>>青海</option>
								<option value="陕西"<c:if test="${resource.address == '陕西'}">selected="selected"</c:if>>陕西</option>
								<option value="新疆"<c:if test="${resource.address == '新疆'}">selected="selected"</c:if>>新疆</option>
							</select>
						</div>
					</div>	
					<div class="form-group">
						<label for="inputEmail3" class="col-md-3 control-label">学员姓名：</label>
						<div class="col-md-8">
							<input  class="form-control" type="text" id="resourceName"  value="${resource.resourceName}"/>
						</div>
					</div>		
					<div class="form-group">
						<label for="inputEmail3" class="col-md-3 control-label">手机号：</label>
						<div class="col-md-8">
							<input  class="form-control" type="text" id="phone" value="${resource.phone }"/>
						</div>
					</div>	
				
					<div class="form-group">
						<label for="inputEmail3" class="col-md-3 control-label">邮箱：</label>
						<div class="col-md-8">
							<input  class="form-control" type="text" id="email"  value="${resource.email }"/>
						</div>
					</div>
					<div class="form-group">
						<label for="inputEmail3" class="col-md-3 control-label">身份证：</label>
						<div class="col-md-8">
							<input  class="form-control" type="text" id="idCard"  value="${resource.idCard }"/>
						</div>
					</div>
					
					<div class="form-group">
						<label for="inputEmail3" class="col-md-3 control-label">咨询课程：</label>
						<div class="col-md-8">
							<select class="form-control" name="course" id="course">
								<option value="${resource.courseid}">${resource.courseName }</option>
							</select>
						</div>
					</div>						
					<div class="form-group">
						<label for="inputEmail3" class="col-md-3 control-label"> 固定电话：</label>
						<div class="col-md-8">
							<input  class="form-control" type="text" id="tel"  value="${resource.tel }"/>
						</div>
					</div>
					<div class="form-group">
						<label for="inputEmail3" class="col-md-3 control-label"> 微信：</label>
						<div class="col-md-8">
							<input  class="form-control" type="text" id="weixin"  value="${resource.weixin }"/>
						</div>
					</div>
					<div class="form-group">
						<label for="inputEmail3" class="col-md-3 control-label"> QQ号：</label>
						<div class="col-md-8">
							<input  class="form-control" type="text" id="qq"  value="${resource.qq }"/>
						</div>
					</div>
						
					<div class="form-group">
						<label for="inputEmail3" class="col-md-3 control-label">下次回访时间：</label>
						<div class="col-md-8">
							<input  class="form-control" type="text" id="nextVisitTime"  value="<fmt:formatDate value="${resource.nextVisitTime }" pattern="yyyy-MM-dd HH:mm:ss"/>">
						</div>
					</div>
					<div class="form-group">
						<label for="inputPassword3" class="col-md-3 control-label">销售备注：</label>
						<div class="col-md-8">
							<textarea class="form-control" rows="3" name="xiaoShouNote" id="xiaoShouNote">${resource.xiaoShouNote}</textarea>
						</div>
					</div>
					<div class="form-group">
						<label for="inputEmail3" class="col-md-3 control-label">资源等级：</label>
						<div class="col-md-8">
							<select class="form-control" name="resourceLevel" id="resourceLevel">
								<option value="">请选择资源等级</option>						
								<option value="A"<c:if test="${resource.resourceLevel == 'A'}">selected="selected"</c:if>>A</option>
								<option value="B"<c:if test="${resource.resourceLevel == 'B'}">selected="selected"</c:if>>B</option>
								<option value="C"<c:if test="${resource.resourceLevel == 'C'}">selected="selected"</c:if>>C</option>
								<option value="D"<c:if test="${resource.resourceLevel == 'D'}">selected="selected"</c:if>>D</option>
							</select>
						</div>
					</div>
					
				    <div class="form-group">
						<table class="table table-bordered  table-hover" id="recoredtable">								
							<c:forEach items="${record}" var="record">
							<tr>
								<td class="text-primary col-md-3">${record.create_time }</td>
								<td class="text-primary col-md-8">${record.visitRecord}</td>
							</tr>
							</c:forEach>
						</table>
					</div>	
					<div class="form-group" style="background: #f7f8fa;padding: 20px 0;">
						<label for="inputPassword3" class="col-md-3 control-label">回访记录:</label>
						<div class="col-md-8">
							<textarea class="form-control" rows="3" name="visitRecord" id="visitRecord"></textarea>
							<span class="error" id="visitRecordError"></span>	
						</div>
					</div>
					<!-- <div class="form-group">
					    <div class="col-md-offset-9 col-md-3">
					      <button type="button" class="btn" onclick="addrecord()">添加</button>
						</div>
					</div> -->
					
					<div class="form-group">
					    <div class="col-md-offset-9 col-md-3">
					    	<a class="btn navbar-left" href="javascript:;" onclick="parent.showdeiatil()" style="background-color: #337ab7;" >
								<span class="glyphicon glyphicon-arrow-left" aria-hidden="true" style="font-size:20px;"></span>
							</a>
						<c:if test="${sessionScope.login_user.deptid!=2}">
					       <button type="button" class="btn btn-danger" onclick="saveDetails()" >保存</button>
					    </c:if>
					    </div>
					</div>	
								
				</form>
			</div>
			
			<!-- <div class="saledetailsedit">
				<form class="form-horizontal" role="form">
					
				</form>
			</div> -->
		</div>
		
		<h2 class="detailTti">成交信息</h2>			
		<div class="detailseWrap">
			<div class="saledetailsedit">
				<form class="form-horizontal" role="form">
					<div class="form-group">
						<label for="inputPassword3" class="col-md-3 control-label">成交时间:</label>
						<div class="col-md-8">
							<input class="form-control" rows="3" name="dealtime" id="dealtime" disabled="disabled"></input>
							<span class="error" id="dealtimeError"></span>
						</div>
					</div>
					<div class="form-group">					
						<label for="inputPassword3" class="col-md-3 control-label">成交课程:</label>
						<div class="col-md-8">
							<select class="form-control" name="courseid" id="courseid">
								<option value="">请选择成交课程</option>
							</select>
							<span class="error" id="courseError"></span>
						</div>	
					</div>
					<div class="form-group" id="dealrecord">
						<label for="inputEmail3" class="col-md-3 control-label">选择科目:</label>
						<div class="col-md-8" id="subjectid">
							
						</div>
					</div>
					<div class="form-group">
						<label for="inputPassword3" class="col-md-3 control-label">成交金额:</label>
						<div class="col-md-8">
							<input class="form-control" rows="3" name="dealprice" id="dealprice"></input>
							<span class="error" id="dealpriceError"></span>
							<p class="error">此处禁止填写网络培训费</p>
						</div>
					</div>
					<div class="form-group">
						<label for="inputPassword3" class="col-md-3 control-label">网络培训费(有/无):</label>
						<div class="col-md-8">
							<label class="radio-inline">
							 	<input type="radio" name="ishavenetedu" value="1"> 有
							</label>
							<label class="radio-inline">
							  	<input type="radio" checked="checked" name="ishavenetedu" value="0">无
							</label>
						</div>
					</div>
					<div class="form-group">
						<label for="inputPassword3" class="col-md-3 control-label">网络培训费金额:</label>
						<div class="col-md-8">
							<input class="form-control" rows="3" name="netedumoney" id="netedumoney"></input>
							<span class="error" id="netedumoneyError"></span>
						</div>
					</div>
					<div class="form-group">
							<label for="inputPassword3" class="col-md-3 control-label">是否在线购买:</label>
							<div class="col-md-8">
								<label class="radio-inline">
								 	<input type="radio" name="isOnlineBuy" value="1"> 是
								</label>
								<label class="radio-inline">
								  	<input type="radio" checked="checked" name="isOnlineBuy" value="0">否
								</label>
							</div>
						</div>
					<div class="form-group">
						<label for="inputPassword3" class="col-md-3 control-label">优惠信息:</label>
						<div class="col-md-8">
							<input class="form-control" rows="3" name="preferinfo" id="preferinfo"></input>						
						</div>
					</div>	
					<div class="form-group">
						<label for="inputPassword3" class="col-md-3 control-label">汇款人:</label>
						<div class="col-md-8">
							<input class="form-control" rows="3" name="remituser" id="remituser" placeholder="此处只填写汇款人/代汇款人/微信支付单号"></input>
							<span class="error" id="remituserError"></span>						
							<p class="error" id="dealError"></p>	
						</div>
					</div>
					<div class="form-group">
					    <div class="col-md-offset-9 col-md-3">
					    <c:if test="${sessionScope.login_user.deptid!=2}">
					      <button type="button" class="btn" onclick="deal()">添加</button>
					      </c:if>
						</div>
					</div>	
				</form>
			</div>		
		</div>
	</c:forEach>
	<!-- 提示 -->
    <div class="themisWrap" style="display:none;" id="dealtip">
      <div class="themisGray"></div>
        <div class="themis" style="top:30%;" id="dealtipFade">
           <h3 class="themistit"><span class="themisTipPic" style="float: left;padding-top: 17px;padding-left: 10px;margin-right: 10px;"><img class="pic" src="../system/img/tishi.png" height="25" width="25" alt="" /></span>友情提示</h3>
           <div class="themispay">
                <div class="themistip" style="margin-bottom: 20px; color:red; font-size:14px;">此资源已成交，请在学员管理中编辑&nbsp;&nbsp;&nbsp;手机：<span id="showphone"></span></div>                             
                <button class="btn navbar-right" id="patterTypeDel" style="background: #4c7cba;" onclick="reload()">确定</button>                    
           </div>
        </div>
    </div> 
    <!-- 提示 -->
    <div class="themisWrap" style="display:none;" id="network">
      <div class="themisGray"></div>
        <div class="themis" style="top:30%;"  id="networkFade">
           <h3 class="themistit"><span class="themisTipPic" style="float: left;padding-top: 17px;padding-left: 10px;margin-right: 10px;"><img class="pic" src="../system/img/tishi.png" height="25" width="25" alt="" /></span>友情提示</h3>
           <div class="themispay">
           <div class="themistip" style="margin-bottom: 20px; font-size:14px;">是否有网络培训费金额：<span id="isnetmoney"></span><br/>网络培训费金额：<span id="netmoney"></span></div>
                <div class="themistip" style="margin-bottom: 20px; color:red; font-size:14px;">请确认网络培训费信息是否正确，如正确，请点击确定按钮，如需修改，请点击取消按钮</div>                             
                <button class="btn navbar-right" id="patterTypeDel" style="background: #4c7cba;" onclick="dernetwork()">确定</button>
                <button class="btn navbar-right" id="patterTypeDel" style="background: #4c7cba;" onclick="wuorder.CloseDiv('network','fade')">取消</button>                     
           </div>
        </div>
    </div>
<script type="text/javascript" src="../common/js/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="../common/js/winTip.js"></script>
<script type="text/javascript" src="../common/js/boot.js"></script>
<script type="text/javascript" src="../common/js/jquery.datetimepicker.js"></script>
<script type="text/javascript" src="../sales/js/details.js"></script>
</body>
</html>
