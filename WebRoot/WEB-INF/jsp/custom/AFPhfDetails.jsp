<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
	pageContext.setAttribute("PATH", request.getContextPath());
			
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta charset="utf-8">
<title>客服-AFP回访详情</title>
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
</head>
<body>
<c:forEach items="${liststudent}" var="liststudent">
	<div class="detailscom">		
		<h2 class="detailTti">基本信息<a class="btn navbar-left" href="javascript:;" onclick="parent.showdeiatil()">
				<span class="glyphicon glyphicon-arrow-left" aria-hidden="true" style="font-size:20px;"></span>
			</a>
		</h2>
		<div class="detailseWrap">
			<div class="detailsedit">
				<form class="form-horizontal" role="form">
					<input class="userid" type="hidden" value="${sessionScope.login_user.userid}" id="userid" />
					<input class="roleid" type="hidden" value="${sessionScope.login_user.roleid}" id="roleid" />
					<input class="deptid" type="hidden" value="${sessionScope.login_user.deptid}" id="deptid" />
					<input type="hidden" value="${liststudent.studentId}" id="studentId"/>
					<input type="hidden" value="${liststudent.phone }" id="resourcephone"/>	
					<div class="form-group">
						<label for="inputPassword3" class="col-md-3 control-label">学员姓名：</label>
						<div class="col-md-8">
							<input class="form-control" rows="3" name="studentName" id="studentName"  disabled="disabled" value="${liststudent.studentName}"></input>
						</div>
					</div>	
					<div class="form-group">
						<label for="inputPassword3" class="col-md-3 control-label">手机号：</label>
						<div class="col-md-8">
							<input class="form-control" rows="3" name="phone" id="phone"  disabled="disabled" value="${liststudent.phone}"></input>
						</div>
					</div>			
					<div class="form-group">
						<label for="inputPassword3" class="col-md-3 control-label">身份证号：</label>
						<div class="col-md-8">
							<input class="form-control" rows="3" name="idCard" id="idCard"  disabled="disabled" value="${liststudent.idCard}"></input>
							
						</div>
					</div>	
					<div class="form-group">
						<label for="inputPassword3" class="col-md-3 control-label">固定电话：</label>
						<div class="col-md-8">
							<input class="form-control" rows="3" name="tel" id="tel"  disabled="disabled" value="${liststudent.tel}"></input>							
						</div>
					</div>	
					<div class="form-group">
						<label for="inputEmail3" class="col-md-3 control-label">学员状态：</label>
						<div class="col-md-8">
							<input  class="form-control" type="text" id="studentstate"  disabled="disabled" value="<c:if test="${liststudent.studentstate ==0}">新增</c:if><c:if test="${liststudent.studentstate ==1}">已成交</c:if><c:if test="${liststudent.studentstate ==2}">已提交</c:if><c:if test="${liststudent.studentstate ==3}">已到账</c:if><c:if test="${liststudent.studentstate ==5}">已转入</c:if><c:if test="${liststudent.studentstate ==7}">已关课</c:if><c:if test="${liststudent.studentstate ==8}">已退回</c:if>"/>
						</div>
					</div>
					<div class="form-group">
						<label for="inputPassword3" class="col-md-3 control-label">邮箱：</label>
						<div class="col-md-8">
							<input class="form-control" rows="3" name="email" id="email"  disabled="disabled" value="${liststudent.email}"></input>					
						</div>
					</div>	
					<div class="form-group">
						<label for="inputPassword3" class="col-md-3 control-label">工作单位：</label>
						<div class="col-md-8">
							<input class="form-control" rows="3" name="company" id="company"  disabled="disabled" value="${liststudent.company}"></input>					
						</div>
					</div>
					<div class="form-group">
						<label for="inputPassword3" class="col-md-3 control-label">职务：</label>
						<div class="col-md-8">
							<input class="form-control" rows="3" name="position" id="position"  disabled="disabled" value="${liststudent.position}"></input>					
						</div>
					</div>
					<div class="form-group">
						<label for="inputEmail3" class="col-md-3 control-label">渠道：</label>
						<div class="col-md-8">							
							<input  class="form-control" type="text" disabled="disabled" value="<c:if test="${resource.source ==0}">自建</c:if><c:if test="${resource.source ==1}">课程注册</c:if><c:if test="${resource.source ==2}">在线注册</c:if><c:if test="${resource.source ==3}">app下载注册</c:if><c:if test="${resource.source ==4}">电话咨询</c:if><c:if test="${resource.source ==5}">金考网注册用户</c:if><c:if test="${resource.source ==6}">线上渠道</c:if><c:if test="${resource.source ==7}">在线注册</c:if><c:if test="${resource.source ==8}">大库资源</c:if><c:if test="${liststudent.source ==9}">在线购买</c:if>"/>
						</div>
					</div>
					<div class="form-group">
						<label for="inputPassword3" class="col-md-3 control-label">班主任：</label>
						<div class="col-md-8">
							<input class="form-control" rows="3" name="customerName" id="customerName" disabled="disabled" value="${liststudent.customerName}"></input>
						</div>
					</div>	
					<div class="form-group">
						<label for="inputPassword3" class="col-md-3 control-label">毕业院校：</label>
						<div class="col-md-8">
							<input class="form-control" rows="3" name="school" id="school"  disabled="disabled" value="${liststudent.school}"></input>					
						</div>
					</div>	
					<div class="form-group">
						<label for="inputPassword3" class="col-md-3 control-label">学历：</label>
						<div class="col-md-8">
							<input class="form-control" rows="3" name="education" id="education"  disabled="disabled" value="${liststudent.education}"></input>					
						</div>
					</div>
					<div class="form-group">
						<label for="inputPassword3" class="col-md-3 control-label">公司地址：</label>
						<div class="col-md-8">
							<input class="form-control" rows="3" name="address" id="companyAddr" disabled="disabled"  value="${liststudent.companyAddr}"></input>					
						</div>
					</div>		
					<div class="form-group">
						<label for="inputPassword3" class="col-md-3 control-label">微信：</label>
						<div class="col-md-8">
							<input class="form-control" rows="3" name="weixin" id="weixin"   value="${liststudent.weixin}"></input>					
						</div>
					</div>				
					<div class="form-group">
						<label for="inputPassword3" class="col-md-3 control-label">招生老师：</label>
						<div class="col-md-8">
							<input class="form-control" rows="3" name="belongName" id="belongName" disabled="disabled" value="${liststudent.belongName}"></input>					
						</div>
					</div>	
					<div class="form-group">
						<label for="inputPassword3" class="col-md-3 control-label">是否签协议：</label>
						<div class="col-md-8">
							<select class="form-control" name="isSignAgreement" id="isSignAgreement">
								<option value="">请选择</option>
								<option value="0" <c:if test="${liststudent.isSignAgreement == 0}">selected="selected"</c:if>>未签订</option>
								<option value="1" <c:if test="${liststudent.isSignAgreement == 1}">selected="selected"</c:if>>已签订</option>
							</select>				
						</div>
					</div>	
					<div class="form-group">
						<label for="inputPassword3" class="col-md-3 control-label">是否上课：</label>
						<div class="col-md-8">
							<select class="form-control" name="isHaveCourse " id="isHaveCourse ">
								<option value="">请选择</option>
								<option value="0" <c:if test="${liststudent.isHaveCourse  == 0}">selected="selected"</c:if>>未上课</option>
								<option value="1" <c:if test="${liststudent.isHaveCourse  == 1}">selected="selected"</c:if>>已上课</option>
							</select>				
						</div>
					</div>			
				</form>
			</div>
			
			<div class="detailsedit">
				<form class="form-horizontal" role="form">
					
					<div class="form-group">
						<label for="inputPassword3" class="col-md-3 control-label">到账费用：</label>
						<div class="col-md-8">
							<input class="form-control" rows="3" name="arrive_money" id="arrive_money" disabled="disabled" value="${liststudent.arrive_money}"></input>					
						</div>
					</div>
					<div class="form-group">
						<label for="inputPassword3" class="col-md-3 control-label">到账日期：</label>
						<div class="col-md-8">
							<input class="form-control" rows="3" name="arrive_time" id="arrive_time" disabled="disabled" value="${liststudent.arrive_time}"></input>					
						</div>
					</div>
					<div class="form-group">
						<label for="inputPassword3" class="col-md-3 control-label">汇款方式：</label>
						<div class="col-md-8">
							<input class="form-control" rows="3" name="remitWay" id="remitWay" disabled="disabled" value="${liststudent.remitWay}"></input>					
						</div>
					</div>
					<div class="form-group">
						<label for="inputPassword3" class="col-md-3 control-label">课件版本：</label>
						<div class="col-md-8">
							<input class="form-control" rows="3" name="courseversion" id="courseversion"  value="${liststudent.courseversion}"></input>					
						</div>
					</div>					
					<div class="form-group">
						<label for="inputPassword3" class="col-md-3 control-label">邮寄时间：</label>
						<div class="col-md-8">
							<input class="form-control" rows="3" name="mailTime" id="mailTime"  value="${liststudent.mailTime}"></input>					
						</div>
					</div>	
					<div class="form-group">
						<label for="inputPassword3" class="col-md-3 control-label">上课班号：</label>
						<div class="col-md-8">
							<input class="form-control" rows="3" name="classNum" id="classNum"  value="${liststudent.classNum}"></input>					
						</div>
					</div>	
					<div class="form-group">
						<label for="inputEmail3" class="col-md-3 control-label">地域：</label>
						<div class="col-md-8">
							<select class="form-control" name="address" id="address" disabled="disabled">
								<option value="" <c:if test="${liststudent.address == ''}">selected="selected"</c:if>>请选择地域</option>
								<option value="北京" <c:if test="${liststudent.address == '北京'}">selected="selected"</c:if>>北京</option>
								<option value="上海" <c:if test="${liststudent.address == '上海'}">selected="selected"</c:if>>上海</option>
								<option value="广东" <c:if test="${liststudent.address == '广东'}">selected="selected"</c:if>>广东</option>
								<option value="天津" <c:if test="${liststudent.address == '天津'}">selected="selected"</c:if>>天津</option>
								<option value="河北" <c:if test="${liststudent.address == '河北'}">selected="selected"</c:if>>河北</option>
								<option value="内蒙古" <c:if test="${liststudent.address == '内蒙古'}">selected="selected"</c:if>>内蒙古</option>
								<option value="吉林"<c:if test="${liststudent.address == '吉林'}">selected="selected"</c:if>>吉林</option>
								<option value="辽宁"<c:if test="${liststudent.address == '辽宁'}">selected="selected"</c:if>>辽宁</option>
								<option value="黑龙江"<c:if test="${liststudent.address == '黑龙江'}">selected="selected"</c:if>>黑龙江</option>
								<option value="福建"<c:if test="${liststudent.address == '福建'}">selected="selected"</c:if>>福建</option>
								<option value="安徽"<c:if test="${liststudent.address == '安徽'}">selected="selected"</c:if>>安徽</option>
								<option value="山西"<c:if test="${liststudent.address == '山西'}">selected="selected"</c:if>>山西</option>
								<option value="重庆"<c:if test="${liststudent.address == '重庆'}">selected="selected"</c:if>>重庆</option>
								<option value="江苏"<c:if test="${liststudent.address == '江苏'}">selected="selected"</c:if>>江苏</option>
								<option value="江西"<c:if test="${liststudent.address == '江西'}">selected="selected"</c:if>>江西</option>
								<option value="山东"<c:if test="${liststudent.address == '山东'}">selected="selected"</c:if>>山东</option>
								<option value="浙江"<c:if test="${liststudent.address == '浙江'}">selected="selected"</c:if>>浙江</option>
								<option value="河南"<c:if test="${liststudent.address == '河南'}">selected="selected"</c:if>>河南</option>
								<option value="湖北"<c:if test="${liststudent.address == '湖北'}">selected="selected"</c:if>>湖北</option>
								<option value="湖南"<c:if test="${liststudent.address == '湖南'}">selected="selected"</c:if>>湖南</option>
								<option value="海南"<c:if test="${liststudent.address == '海南'}">selected="selected"</c:if>>海南</option>
								<option value="广西"<c:if test="${liststudent.address == '广西'}">selected="selected"</c:if>>广西</option>
								<option value="贵州"<c:if test="${liststudent.address == '贵州'}">selected="selected"</c:if>>贵州</option>
								<option value="四川"<c:if test="${liststudent.address == '四川'}">selected="selected"</c:if>>四川</option>
								<option value="西藏"<c:if test="${liststudent.address == '西藏'}">selected="selected"</c:if>>西藏</option>
								<option value="云南"<c:if test="${liststudent.address == '云南'}">selected="selected"</c:if>>云南</option>
								<option value="甘肃"<c:if test="${liststudent.address == '甘肃'}">selected="selected"</c:if>>甘肃</option>
								<option value="宁夏"<c:if test="${liststudent.address == '宁夏'}">selected="selected"</c:if>>宁夏</option>
								<option value="青海"<c:if test="${liststudent.address == '青海'}">selected="selected"</c:if>>青海</option>
								<option value="陕西"<c:if test="${liststudent.address == '陕西'}">selected="selected"</c:if>>陕西</option>
								<option value="新疆"<c:if test="${liststudent.address == '新疆'}">selected="selected"</c:if>>新疆</option>
							</select>
						</div>
					</div>				
					<div class="form-group">					
						<label for="inputPassword3" class="col-md-3 control-label">班级:</label>
						<div class="col-md-8">
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
								<option value="C网" <c:if test="${liststudent.banci == 'C网'}">selected="selected"</c:if>>C网</option>
							</select>
						</div>	
					</div>	
				
					<div class="form-group">
						<label for="inputPassword3" class="col-md-3 control-label">发票情况：</label>
						<div class="col-md-8">
							<input class="form-control" rows="3" name="invoiceinfo" id="invoiceinfo" disabled="disabled" value="${liststudent.invoiceinfo}"></input>					
						</div>
					</div>
					<div class="form-group">
						<label for="inputPassword3" class="col-md-3 control-label">报考密码：</label>
						<div class="col-md-8">
							<input class="form-control" rows="3" name="baokaopassword" id="baokaopassword"  value="${liststudent.baokaopassword}"></input>					
						</div>
					</div>
					<div class="form-group">
						<label for="inputPassword3" class="col-md-3 control-label">是否通过：</label>
						<div class="col-md-8">
							<input class="form-control" rows="3" name="ispass" id=ispass disabled="disabled" value="<c:if test="${liststudent.ispass == 0}">未通过</c:if><c:if test="${liststudent.ispass == 1}">通过</c:if><c:if test="${liststudent.ispass == 2}">缺考</c:if>"></input>					
						</div>
					</div>
					<div class="form-group">
						<label for="inputPassword3" class="col-md-3 control-label">销售备注：</label>
						<div class="col-md-8">
							<input class="form-control" rows="3" name="xingzhengNote" id="xingzhengNote"  disabled="disabled" value="${liststudent.xingzhengNote}"></input>					
						</div>
					</div>
					<div class="form-group">
						<label for="inputPassword3" class="col-md-3 control-label">行政备注：</label>
						<div class="col-md-8">
							<input class="form-control" rows="3" name="kefuNote1" id="kefuNote1" value="${liststudent.kefuNote1}"></input>					
						</div>
					</div>	
					<div class="form-group">
						<label for="inputPassword3" class="col-md-3 control-label">学员评价：</label>
						<div class="col-md-8">
							<select class="form-control" name="pingjia" id="pingjia">
								<option value="">请选择</option>
								<option value="优" <c:if test="${liststudent.studentEvaluate == '优'}">selected="selected"</c:if>>优</option>
								<option value="良" <c:if test="${liststudent.studentEvaluate == '良'}">selected="selected"</c:if>>良</option>
								<option value="中" <c:if test="${liststudent.studentEvaluate == '中'}">selected="selected"</c:if>>中</option>
								<option value="差" <c:if test="${liststudent.studentEvaluate == '差'}">selected="selected"</c:if>>差</option>
							</select>				
						</div>
					</div>	
					<div class="form-group">
						<label for="inputPassword3" class="col-md-3 control-label">特殊情况：</label>
						<div class="col-md-8">
							<select class="form-control" name="specialinfo" id="specialinfo">
								<option value="">请选择</option>
								<option value="已失联" <c:if test="${liststudent.specialinfo == '已失联'}">selected="selected"</c:if>>已失联</option>
								<option value="已关课" <c:if test="${liststudent.specialinfo == '已关课'}">selected="selected"</c:if>>已关课</option>
								<option value="已退费" <c:if test="${liststudent.specialinfo == '已退费'}">selected="selected"</c:if>>已退费</option>
								<option value="已转课" <c:if test="${liststudent.specialinfo == '已转课'}">selected="selected"</c:if>>已转课</option>
							</select>				
						</div>
					</div>	
					<div class="form-group">
					    <div class="col-md-offset-9 col-md-3">
					      <button type="button" class="btn btn-danger" onclick="saveDetails()">保存</button>
					    </div>
					</div>	
				</form>
			</div>
		</div>
		
		<h2 class="detailTti">添加信息</h2>			
		<div class="detailseWrap">
			<div class="detailsedit">
				<form class="form-horizontal" role="form">
					<div class="form-group">
						<label for="inputPassword3" class="col-md-3 control-label">回访记录:</label>
						<div class="col-md-6">
							<textarea class="form-control" rows="3" name="visitRecord" id="visitRecord"></textarea>
							<span class="error" id="visiterror"></span>
						</div> 
					    <div class="col-md-2">
					      	<button type="button" class="btn" onclick="addrecord()">添加</button>
					    </div>
					</div>
					<table class="table table-bordered  table-hover" id="recoredtable">								
						<c:forEach items="${record}" var="record">
						<tr>
							<td class="text-primary col-md-3">${record.create_time }</td>
							<td class="text-primary col-md-6">${record.visitRecord}</td>
						</tr>
						</c:forEach>
					</table>	
				</form>
			</div>
			<div class="detailsedit">
				<form class="form-horizontal" role="form">								
					<label for="inputPassword3" class="col-md-3 control-label" style="text-align: left;padding: 0 0 20px 0;">成交记录:</label>
					<table class="table table-bordered  table-hover" id="dealtable">								
						<tr>
							<th class="text-primary">成交时间</th>
			                <th class="text-primary">课程</th>
			                <th class="text-primary">科目</th>
			                <th class="text-primary">考试成绩</th>
			                <th class="text-primary">考试时间</th>
			                <th class="text-primary">是否通过</th>
			                <th class="text-primary">修改</th>					
						</tr>			
						<c:forEach items="${dealrecord}" var="dealrecord">
						<tr data-id="${dealrecord.dealrecordId}">
							<td>${liststudent.dealtime}</td>							
							<td data-id="${dealrecord.courseid}">${dealrecord.courseName }</td>
							<td>${dealrecord.subjectname}</td>
							<td>${dealrecord.score }</td>
							<td>${dealrecord.scoretime }</td>
							<td>						
								<c:if test="${dealrecord.ispass ==0}">未通过</c:if>
								<c:if test="${dealrecord.ispass ==1}">通过</c:if>
								<c:if test="${dealrecord.ispass ==2}">缺考</c:if>
							</td>
							<td class="text-primary xiugai"><span class="glyphicon glyphicon-edit changedit" data-id="${dealrecord.dealrecordId}"></span></td>					
						</tr>
						</c:forEach>		
					</table>
				</form>
			</div>
		</div>
	</div>			
</c:forEach>
<!--记录_修改-->
<div id="changedeal"  class="ui-wrap">
	<div class="addnew-wrap" style="top: 40%;">
		<h2 class="wrap-tit"><b class="addnewTit">记录_修改</b><span class="close closebtn"  onclick="wuorder.CloseDiv('changedeal','fade')">×</span></h2>
		
		<div class="form-horizontal add_form">
			<div class="form-group">
				<label for="inputPassword3" class="col-md-3 control-label">考试成绩:</label>
				<div class="col-md-8">
					<input class="form-control" rows="3" name="score" id="ch-score"></input>
				</div>
			</div>
			<div class="form-group">
				<label for="inputPassword3" class="col-md-3 control-label">考试时间:</label>
				<div class="col-md-8">
					<input class="form-control" rows="3" name="scoretime" id="ch-scoretime"></input>
					<span class="error" id="scoretimeError"></span>
				</div>
			</div>
			<div class="form-group">
				<label for="inputPassword3" class="col-md-3 control-label">是否通过:</label>
				<div class="col-md-8">
					<select class="form-control" id="ch-ispass">
					    <option value="">请选择</option>
						<option value="0">未通过</option>
						<option value="1">通过</option>
						<option value="2">缺考</option>				
					</select>					
				</div>
			</div>			
			<div class="form-group">
				<div class="col-md-offset-4 col-md-6">
					<a href="javascript:;" class="btn addsave" onclick="changedeal()">修改</a>						
					<a href="javascript:;"  class="btn"  onclick="wuorder.CloseDiv('changedeal','fade')" style="background: #4c7cba;">取消</a>
				</div>
			</div>
		</div>
	</div>
</div>	
<!-- 提示 -->
<div class="themisWrap" style="display:none;" >
  <div class="themisGray"></div>
    <div class="themis" style="top:30%;">
       <h3 class="themistit"><span class="themisTipPic" style="float: left;padding-top: 17px;padding-left: 10px;margin-right: 10px;"><img class="pic" src="../system/img/tishi.png" height="25" width="25" alt="" /></span>友情提示</h3>
       <div class="themispay">
            <div class="themistip" style="margin-bottom: 20px; color:red; font-size:14px;"><b id="tipinfo"></b>手机：<span id="showphone"></span></div>                             
            <button class="btn navbar-right" id="patterTypeDel" style="background: #4c7cba;" onclick="history.go(-1)">确定</button>                    
       </div>
    </div>
</div> 		
<script type="text/javascript" src="../common/js/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="../common/js/winTip.js"></script>
<script type="text/javascript" src="../common/js/boot.js"></script>
<script type="text/javascript" src="../common/js/jquery.datetimepicker.js"></script>
<script type="text/javascript" src="../custom/js/hfDetails.js"></script>
<script type="text/javascript">
	var ispass = $('#ispass').val();//是否全科通过
	var phone = $('#phone').val();
	var specialinfo = $('#specialinfo option:selected').val();	
	if($.trim(ispass) == "通过"){
		$('.themisWrap').css('display','block');
		$('#tipinfo').html("此学员已全科通过，请在【通过学员】中查看");
		$('#showphone').html(phone);
	}
</script>
</body>
</html>
