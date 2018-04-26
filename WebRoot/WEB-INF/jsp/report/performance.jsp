 <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
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
	<link rel="stylesheet" type="text/css" href="../common/css/jquery.datetimepicker.css" />
	<link rel="stylesheet" type="text/css" href="../report/css/userCount.css" />
	<title>业绩统计</title>
	<style type="text/css">
		#ThreeRanking .hotRanking,
		#ThreeRanking .manyRanking,
		#ThreeRanking .fireRanking {
		    width: 22.2%;
		    margin-right: 1.7%;
		    background: #fff;
		    border: 1px solid #e6e6e6;
		    float: left
		}
	
	</style>	
</head>
<body ng-app="resource" ng-controller="resourceCtrl"  style="height:auto;">
	<input class="userid" type="hidden" value="${sessionScope.login_user.userid}" id="userid" />
	<input class="roleid" type="hidden" value="${sessionScope.login_user.roleid}" id="roleid" />
	<input class="deptid" type="hidden" value="${sessionScope.login_user.deptid}" id="deptid" />
	<p class="shuiyin"><%=loginName%></p>
	<p class="shuiyin suiyintwo"><%=loginName%></p>
	<p class="shuiyin suiyinthree"><%=loginName%></p>
	<ul class="listnav">
		<li class="current" id="perlist">业绩列表</li>
		<li ng-click="performanceDetail('/report/queryPerformanceDetail.do')" id="perdetail">业绩明细</li>
	</ul>
	<div class="reportWrap">
		<div class="list" id="listreport">
			<div id="findTeacher">
			    <div id="TeacherRanking">
			        <div class="Rankingtop" style="border-bottom: none;">
			            <span class="title">业绩统计</span>
			        </div>
			    </div>
			    <!-- 排行详细 -->
			    <div id="ThreeRanking">
			        <div class="ranking hotRanking">
			            <div class="ranktitle">
			                <span><i class="icon fa fa-commenting"></i>2017总业绩</span>
			            </div>
			            <div class="rankContent">
			                <div class="rest">
			                    <div class="wrap">
				                    <div class="item" ng-repeat="infor in listall"  ng-click="performanceDetail('/report/queryPerformanceDetail.do',infor.userid)">
					                   <span class="sort" ng-bind="($index+1)"></span>
					                   <div class="favicon">
					                       <a href="javascriot:;"  class="avatar">
					                           <span class="glyphicon glyphicon-user userrest"></span>
					                       </a>
					                   </div>
					                   <div class="info">
					                       <div class="row2">
					                           <div class="wrap">
					                               <a href="javascript:;" ng-bind="infor.username" ></a>
					                               <span class="index" ng-bind="infor.sumMoney"></span>
					                           </div>
					                       </div>
					                   </div>
						            </div>
			                    </div>
			                </div>
			            </div>
			        </div>
			        
			        <div class="ranking manyRanking">
			            <div class="ranktitle">
			                <span><i class="icon fa fa-tv"></i>本月业绩</span>
			            </div>
			            <div class="rankContent">
			                <div class="rest">
			                    <div class="wrap">
				                    <div class="item" ng-repeat="infor in listtodaymoth ">
					                   <span class="sort" ng-bind="($index+1)"></span>
					                   <div class="favicon">
					                       <a href="javascriot:;"  class="avatar">
					                           <span class="glyphicon glyphicon-user userrest"></span>
					                       </a>
					                   </div>
					                   <div class="info">
					                       <div class="row2">
					                           <div class="wrap">
					                               <a href="javascript:;" ng-bind="infor.username"></a>
					                               <span class="index" ng-bind="infor.sumMoney"></span>
					                           </div>
					                       </div>
					                   </div>
						            </div>
			                    </div>
			                </div>
			            </div>
			        </div>
			        
			        <div class="ranking fireRanking">
			            <div class="ranktitle">
			                <span><i class="icon fa fa-comments-o"></i>课程业绩</span>
			                <span class="indicates" style="width: 201px;">
			                	<select id="selectmoth" style="width: 120px;">
			                		<option value="">请选择</option>
			                		<option value="1,2">AFP</option>
			                		<option value="3,4">CFP</option>
			                		<option value="6,7,8,9,19">基金</option>
			                		<option value="14">证券从业</option>
			                		<option value="5">银行从业</option>
			                		<option value="10">中级经济师</option>
			                		<option value="13">期货从业</option>
			                		<option value="11">会计从业</option>
			                		<option value="12">初级会计</option>
			                	</select>
		                    	<a class="btn filterder" ng-click="course('/report/queryPerformanceCourse.do','tag')">提交</a>  
			                </span>
			            </div>
			            <div class="rankContent">
			                <div class="rest">
			                    <div class="wrap">
				                    <div class="item" ng-repeat="infor in listcourse ">
					                   <span class="sort" ng-bind="($index+1)"></span>
					                   <div class="favicon">
					                       <a href="javascriot:;"  class="avatar">
					                           <span class="glyphicon glyphicon-user userrest"></span>
					                       </a>
					                   </div>
					                   <div class="info">
					                       <div class="row2">
					                           <div class="wrap">
					                               <a href="javascript:;" ng-bind="infor.username"></a>
					                               <span class="index" ng-bind="infor.sumMoney"></span>
					                           </div>
					                       </div>
					                   </div>
						            </div>
			                    </div>
			                </div>
			            </div>
			        </div>		        
			        
			        <div class="ranking fireRanking">
			            <div class="ranktitle">
			                <span><i class="icon fa fa-comments-o"></i>按月份筛选</span>
			                <span class="indicates" style="width: 201px;">
			                	<input type="text" id="taskStartTime" class="" ng-model="date" placeholder="选择日期" style="width: 120px;">
		                    	<a class="btn filterder" ng-click="listtimefilter('/report/queryPerformanceMoth.do')">提交</a>  
			                </span>
			            </div>
			            <div class="rankContent">
			                <div class="rest">
			                    <div class="wrap">
				                    <div class="item" ng-repeat="infor in listmoth ">
					                   <span class="sort" ng-bind="$index+1"></span>
					                   <div class="favicon">
					                       <a href="javascriot:;"  class="avatar">
					                           <span class="glyphicon glyphicon-user userrest"></span>
					                       </a>
					                   </div>
					                   <div class="info">
					                       <div class="row2">
					                           <div class="wrap">
					                               <a href="javascript:;" ng-bind="infor.username"></a>
					                               <span class="index" ng-bind="infor.sumMoney"></span>
					                           </div>
					                       </div>
					                   </div>
						            </div>
			                    </div>
			                </div>
			            </div>
			        </div>
			        
			    </div>
			</div>
		</div>
		<!-- 业绩明细 -->
	    <div class="charts" id="detailreport">	    
		    <div class="table-responsive" data-example-id="simple-table">
		    	<div class="preoDetail">
			        <div class="xingm">
						<input type="text" value=""  placeholder="姓名搜索" name="username" class="chioce_time" ng-model="query">
						<a class="btn filterder">查询</a> 
					</div>
			    	<div class="caidan">
						<input type="text" value="" id="startTime" placeholder="开始时间" name="startTime" class="chioce_time">
						<span class="until">-</span>
						<input type="text" value="" id="endTime" placeholder="结束时间" name="endTime" class="chioce_time">
						<a class="btn filterder" ng-click="performanceDetail('/report/queryPerformanceDetail.do')">提交</a> 
						<a class="btn filterder" id="filterExport" style="visibility: hidden;">导出</a> 
					</div>                                                                                                                                                                                                                           
		    	</div>
		        <table class="table table-bordered table-hover table-striped able-condensed" id="performanceTable">              	                   
		            <tr class="henglan">
						<th>序号</th>
						<th>姓名</th>
						<th>提成基础</th>
						<th>A面</th>
						<th>A网</th>
						<th>C面</th>
						<th>C网</th>
						<th>银行从业</th>
						<th>基金串讲班</th>
						<th>基金保过班</th>
						<th>基金退费班</th>
						<th>基金后付费班</th>
						<th>基金题库班</th>
						<th>中级经济师</th>
						<th>会计从业</th>
						<th>初级会计</th>
						<th>期货从业</th>
						<th>证券从业</th>
						<th>A真题实战班</th>
		            </tr> 
		            <tr ng-repeat="infor in PerformanceDetail | filter:query | orderBy:col:desc track by $index">
						<td ng-bind="($index+1)"></td>
						<td ng-bind="infor.username"></td>
						<td ng-bind="infor.priceAll"></td>
						<td ng-bind="infor.priceAM"></td>
						<td ng-bind="infor.priceAW"></td>
						<td ng-bind="infor.priceCM"></td>
						<td ng-bind="infor.priceCW"></td>
						<td ng-bind="infor.priceYHCY"></td>
						<td ng-bind="infor.priceJJCJB"></td>
						<td ng-bind="infor.priceJJBGB"></td>
						<td ng-bind="infor.priceJJTFB"></td>
						<td ng-bind="infor.priceJJHFFB"></td>
						<td ng-bind="infor.priceJJTKB"></td>
						<td ng-bind="infor.priceZJJJS"></td>
						<td ng-bind="infor.priceKJCY"></td>
						<td ng-bind="infor.priceCJKJ"></td>
						<td ng-bind="infor.priceQHCY"></td>
						<td ng-bind="infor.priceZQCY"></td>
						<td ng-bind="infor.priceAZTSZB"></td>
		            </tr>
		        </table>
    		</div>
	    </div>
	    
	</div>	
<script type="text/javascript" src="../common/js/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="../common/js/angular.min.js"></script>
<script type="text/javascript" src="../common/js/jquery.datetimepicker.js"></script>
<script type="text/javascript" src="../common/js/echarts.min.js"></script>
<script type="text/javascript" src="../report/js/performance.js"></script>
</body>
</html>