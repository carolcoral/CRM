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
	<title>用户录入统计</title>	
	<style type="text/css">
		#ThreeRanking .hotRanking,
		#ThreeRanking .manyRanking,
		#ThreeRanking .fireRanking {
		    width: 18.2%;
		    margin-right: 1.7%;
		    background: #fff;
		    border: 1px solid #e6e6e6;
		    float: left
		}
	</style>
</head>
<body ng-app="resource" ng-controller="resourceCtrl" style="height:auto;">
	<input class="userid" type="hidden" value="${sessionScope.login_user.userid}" id="userid" />
	<input class="roleid" type="hidden" value="${sessionScope.login_user.roleid}" id="roleid" />
	<input class="deptid" type="hidden" value="${sessionScope.login_user.deptid}" id="deptid" />
	<div id="findTeacher">
	    <div id="TeacherRanking">
	        <div class="Rankingtop" style="border-bottom: none;">
	            <span class="title">用户录入统计</span>
	            <span class="fr">
	        </span>
	        </div>
	        <!-- <div class="Rankingbott ">
	            <div class="summary-item summary-item1">
	                <div>入驻投顾</div>
	                <div class="num">25</div>
	            </div>
	            <div class="summary-item summary-item2">
	                <div>发布观点</div>
	                <div class="num">2324</div>
	            </div>
	            <div class="summary-item summary-item3">
	                <div>发布直播</div>
	                <div class="num">49403</div>
	            </div>
	            <div class="summary-item summary-item4">
	                <div>解答问题</div>
	                <div class="num">1974</div>
	            </div>
	            <div class="summary-item summary-item5">
	                <div>影响人数</div>
	                <div class="num">2569</div>
	            </div>
	        </div> -->
	    </div>
	    <!-- 排行详细 -->
	    <div id="ThreeRanking">
	        <div class="ranking hotRanking">
	            <div class="ranktitle">
	                <span><i class="icon fa fa-commenting"></i>总录入</span>
	             	<span class="indicates">
	                	 <a class="btn filterder export" onclick="excelexport('0')">导出</a> 
	                </span>
	            </div>
	            <div class="rankContent">
	                <div class="rest">
	                    <div class="wrap">
		                    <div class="item" ng-repeat="infor in listall | filter:query | orderBy:col:desc track by $index">
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
			                               <span class="index" ng-bind="infor.addCount"></span>
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
	                <span><i class="icon fa fa-tv"></i>本月录入</span>
	                <span class="indicates">
	                	<a class="btn filterder export" onclick="excelexport('1')">导出</a>  
	                </span>
	            </div>
	            <div class="rankContent">
	                <div class="rest">
	                    <div class="wrap">
		                    <div class="item" ng-repeat="infor in listmoth | filter:query | orderBy:col:desc track by $index">
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
			                               <span class="index" ng-bind="infor.addCount"></span>
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
	                <span><i class="icon fa fa-tv"></i>本周录入</span>
	                <span class="indicates">
	                	<a class="btn filterder export" onclick="excelexport('4')">导出</a>  
	                </span>
	            </div>
	            <div class="rankContent">
	                <div class="rest">
	                    <div class="wrap">
		                    <div class="item" ng-repeat="infor in listWeek | filter:query | orderBy:col:desc track by $index">
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
			                               <span class="index" ng-bind="infor.addCount"></span>
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
	                <span><i class="icon fa fa-comments-o"></i>今日录入</span>
	                <span class="indicates">
	                	 <a class="btn filterder export" onclick="excelexport('2')">导出</a>     
	                </span>
	            </div>
	            <div class="rankContent">
	                <div class="rest">
	                    <div class="wrap">
		                    <div class="item" ng-repeat="infor in listtoday | filter:query | orderBy:col:desc track by $index">
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
			                               <span class="index" ng-bind="infor.addCount"></span>
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
	                <span><i class="icon fa fa-comments-o"></i>按时间筛选</span>
	                <span class="indicates" style="width: 177px;">
	                	<input type="text" id="taskStartTime" class="" ng-model="date" placeholder="选择日期">
                    	<a class="btn filterder" ng-click="listtimefilter('/report/queryUserAddCountTime.do')">提交</a>  
                    	<a class="btn filterder export" id="filterExport">导出</a>        
	                </span>
	            </div>
	            <div class="rankContent">
	                <div class="rest">
	                    <div class="wrap">
		                    <div class="item" ng-repeat="infor in listtime | filter:query | orderBy:col:desc track by $index">
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
			                               <span class="index" ng-bind="infor.addCount"></span>
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
<script type="text/javascript" src="../common/js/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="../common/js/angular.min.js"></script>
<script type="text/javascript" src="../common/js/jquery.datetimepicker.js"></script>
<script type="text/javascript" src="../common/js/boot.js"></script>
<script type="text/javascript" src="../common/js/js-extend.js"></script>	
<script type="text/javascript" src="../common/js/ajaxPage.js"></script>
<script type="text/javascript">
	/*初始化时间插件*/
     $('#taskStartTime').datetimepicker({
         lang:"ch",           //语言选择中文
         format:"Y-m-d",      //格式化日期
         timepicker:false,    //关闭时间选项
         validateOnBlur:false
     });
	var userid = document.getElementById("userid").value;
	var deptid = document.getElementById("deptid").value;
    var myresource = angular.module('resource', []);
	myresource.controller('resourceCtrl',function($scope,$http){
    forPages('/report/queryUserAddCountAll.do',userid,deptid,function(){});
   	moth('/report/queryUserAddCountMoth.do',userid,deptid,function(){});
    today('/report/queryUserAddCountToday.do',userid,deptid,function(){});
    week ('/report/queryUserAddCountWeek.do',userid,deptid,function(){})
    //获取数据
    function forPages (url,userid,deptid,callback){
       $http.get(url+"?deptid="+deptid+"&userid="+userid).success(function(Data){
           
           $scope.list = Data.rows;
         
	       angular.forEach($scope.list, function(array){
	       		$scope.listall=$scope.list.listall;//总资源量
		   });
           callback();
       });
   	}
  
  	function moth (url,userid,deptid,callback){
  			$http.get(url+"?deptid="+deptid+"&userid="+userid).success(function(Data){
           
           $scope.list = Data.rows;
         
	       angular.forEach($scope.list, function(array){
	       		
	       		$scope.listmoth=$scope.list.listmoth;//总资源量
	       		
		   });
           callback();
       });
  		}
  	function week (url,userid,deptid,callback){
       $http.get(url+"?deptid="+deptid+"&userid="+userid).success(function(Data){
           $scope.list = Data.rows;
         
	       angular.forEach($scope.list, function(array){
	       		
	       		$scope.listWeek=$scope.list.listWeek;//总资源量 
		   });
           callback();
       });
   	}
   function today (url,userid,deptid,callback){
       $http.get(url+"?deptid="+deptid+"&userid="+userid).success(function(Data){
           
           $scope.list = Data.rows;
         
	       angular.forEach($scope.list, function(array){
	       		
	       		$scope.listtoday=$scope.list.listtoday;//总资源量 
		   });
           callback();
       });
   	}
   	$scope.listtimefilter = function (url){
   		var listtime = document.getElementById("taskStartTime").value;
      		$http.get(url+"?deptid="+deptid+"&userid="+userid+"&countTime="+listtime).success(function(Data){  
      		   $scope.list = Data.rows;
	       		angular.forEach($scope.list, function(array){
	       			$scope.listtime=$scope.list.listtime;//总资源量 
		   		});
        	});                                                                                                                                                                                                                                                                                                                                                                                               
      	}
	})
	//导出只有主管可见
	var roleid = document.getElementById("roleid").value;//角色Id
	if(roleid=="2"||roleid=="4"||roleid=="6"||roleid=="8"||roleid=="10"||roleid=="12"||roleid=="14"||roleid=="16"||roleid=="18"||roleid=="20"||roleid=="22"||roleid=="24"||roleid=="26"||roleid=="31"||roleid=="32"||roleid=="35"||roleid=="36"||roleid=="49"||roleid=="51"){
		$('.export').css('display','block');
	}
	$('#filterExport').on('click',function(){
		var listtime = document.getElementById("taskStartTime").value;
		excelexport('3',listtime);
	})
	//导出
	function excelexport(num,time){
	
		var exportExcel = "export_excel";	
		if(time == "" || time == null){
			dataParams = {
				sign:num
			};
		}else {
			dataParams = {
				sign:num,
				countTime:time			 			
			};
		}
		var params = $.param(dataParams);
		
		var url = '/report/excelExportReportAddCount.do'+"?"+params;
		
		$('<form method="post" action="' + url + '"></form>').appendTo('body').submit().remove();
		
		delete dataParams[exportExcel];
	}
	//ifram高度
    var iframe = $(window.parent.document).find("#iframepage"); 
    iframe.height(0);         
	function reinitIframe() {          
	     try {   
	            bHeight =document.body.scrollHeight;               
	            iframe.height(bHeight);           
	        } catch (ex) { }       
	}       
	window.setInterval("reinitIframe()", 200);//定时去检查iframe的高度,这样保证时时都是自动高了
</script>
</body>
</html>