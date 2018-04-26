<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	pageContext.setAttribute("PATH", request.getContextPath());
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>提成发放总表</title>
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
	<style type="text/css">
		.coursePanel {
		    height: 50px;
		    background: #3d3d3d;
		    color: #fff;
		    line-height: 50px;
		    font-size: 15px;
		    margin-top: 20px;
		}
		.glyphicon-th-list {
		    padding: 0 10px;
		    color: #fff;
		}
	</style>
</head>
<body ng-app="commission" ng-controller="commissionCtrl">
	<input type="hidden" value="${sessionScope.login_user.userid}" id="userid" />
	<input type="hidden" value="${sessionScope.login_user.roleid}" id="roleid" />
	<input type="hidden" value="${sessionScope.login_user.deptid}" id="deptid" />
	<input type="hidden" value="${sessionScope.login_user.deptgroup}" id="deptgroup" />
	<div class="meun-btn">
       	<ul class="nav navbar-nav">
			<form class="navbar-form navbar-left" role="search">
				<div class="form-group">
					<input type="text" value="" id="taskStartTime" placeholder="开始时间" name="arriveStartTime" class="chioce_time">
					<span class="until">-</span>
					<input type="text" value="" id="taskEndTime" placeholder="结束时间" name="arriveEndTime" class="chioce_time">
				</div>
				<a href="javascript:;" ng-click="forPages('1','15')" class="btn" style="background: #4c7cba;">查询</a>
			</form>
		</ul>
	</div>
    <div class="bs-example table-responsive" data-example-id="simple-table"> 
    	<div class="coursePanel">
		  <span class="glyphicon glyphicon-th-list"></span>提成发放总表	  
		</div>              
     	<table class="table table-bordered table-hover table-striped">                    
               <tr class="henglan">
                   <td class="text-primary">序号</td>
                   <td class="text-primary">部门大类</td>
                   <td class="text-primary">所属部门</td>
                   <td class="text-primary">招生老师</td>
                   <td class="text-primary">姓名</td>
                   <td class="text-primary">手机</td>
                   <td class="text-primary">费用</td>
                   <td class="text-primary">到账日期</td>
                   <td class="text-primary">我司收入</td>
                   <td class="text-primary">提成比例</td>
                   <td class="text-primary">应发提成金额</td>
                   <td class="text-primary">实发提成金额</td>
                   <td class="text-primary">科目</td>
                   <td class="text-primary">课程</td>
                   <td class="text-primary">备注</td>
               </tr> 
               <tr ng-repeat="infor in list | filter:query | orderBy:col:desc track by $index" data-id="{{infor.userid}}">
                   <td>{{(currentPage-1)*pageSize+($index+1)}}</td>
                   <td ng-bind="infor.deptgroupName" class="deptgroupName"></td>
                   <td ng-bind="infor.deptName"></td>
                   <td ng-bind="infor.belongName"></td>
                   <td ng-bind="infor.studentName"></td>
                   <td ng-bind="infor.phone"></td>
                   <td ng-bind="infor.arrive_money"></td>
                   <td ng-bind="infor.arrive_time"></td>
                   <td ng-bind="infor.dealprice"></td>
                   <td ng-bind="infor.proportion"></td>
                   <td ng-bind="infor.shouldpayMoney"></td>
                   <td ng-bind="infor.actualpayMoney"></td>
                   <td ng-bind="infor.subjectname"></td>
                   <td ng-bind="infor.courseName"></td>
                   <td ></td>
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
<script type="text/javascript" src="../common/js/jquery.datetimepicker.js"></script>
<script type="text/javascript" src="../common/js/winTip.js"></script>
<script type="text/javascript" src="../common/js/boot.js"></script>
<script type="text/javascript" src="../system/js/login.js"></script>
<script type="text/javascript">
	/*初始化时间插件*/
     $('#taskStartTime').datetimepicker({
         lang:"ch",           //语言选择中文
         format:"Y-m-d",      //格式化日期
         timepicker:false,    //关闭时间选项
         validateOnBlur:false
     });
     $('#taskEndTime').datetimepicker({
         lang:"ch",           //语言选择中文
         format:"Y-m-d",      //格式化日期
         timepicker:false,    //关闭时间选项
         validateOnBlur:false
     });
	//angular 分页展示数据
	var userid = document.getElementById("userid").value;
	var deptid = document.getElementById("deptid").value;
	var roleid = document.getElementById("roleid").value;
	var deptgroup = document.getElementById("deptgroup").value;
	
	var mycourse = angular.module('commission', []);
	mycourse.controller('commissionCtrl',function($scope,$http){   
	  //配置
	    $scope.count = 0;//总条数
	    $scope.pageSize = 15;//每页15条
	    //变量
	    $scope.currentPage = 1;//当前页
	    $scope.pageTotal =0; // 总页数 
	    $scope.pages = [];
	    //获取数据
	     $scope.forPages = function(page,size){
	     	var dealStartTime = document.getElementById("taskStartTime").value;
			var dealEndTime = document.getElementById("taskEndTime").value;
	        $http.get("/commission/queryCommissionAll.do?currentPage="+page+"&pageSize="+size+"&userid="+userid+"&deptid="+deptid+"&roleid="+roleid+"&deptgroup="+deptgroup+"&dealStartTime="+dealStartTime+"&dealEndTime="+dealEndTime).success(function(Data){  
	        	$scope.count=Data.total;
	         	$scope.list = Data.rows;
	            $scope.currentPage = Data.currentPage;
	            $scope.pageTotal =Math.ceil($scope.count/$scope.pageSize);
	            reloadPno(); 		        	             	
	        }); 
	    }
	     $scope.forPages($scope.currentPage,$scope.pageSize);
	    //首页
	    $scope.firstPage = function(){
	        $scope.loadPage(1);
	    }
	    //尾页
	    $scope.lastPage = function(){
	        $scope.loadPage($scope.pageTotal);
	    }
	    //加载某一页
	    $scope.loadPage = function(page){
	    	$scope.forPages(page,$scope.pageSize,function(){ });
	    };
	    //初始化页码  
	   	var reloadPno = function(){  
	       	$scope.pages=calculateIndexes($scope.currentPage,$scope.pageTotal,10);  
	    };  
		//分页算法  
		var calculateIndexes = function (current, length, displayLength) {  
		   var indexes = [];  
		   var start = Math.round(current - displayLength / 2);  
		   var end = Math.round(current + displayLength / 2);  
		    if (start <= 1) {  
		        start = 1;  
		        end = start + displayLength - 1;  
		       if (end >= length - 1) {  
		           end = length - 1;  
		        }  
		    }  
		    if (end >= length - 1) {  
		       end = length;  
		        start = end - displayLength + 1;  
		       if (start <= 1) {  
		           start = 1;  
		        }  
		    }  
		    for (var i = start; i <= end; i++) {  
		        indexes.push(i);  
		    }  
		    return indexes;  
		 		console.info(indexes);
		};
	    
	});
	//ifram高度
	function reinitIframe() {          
	     var iframe = $(window.parent.document).find("#iframepage");          
	     try {               
	            var bHeight =document.body.scrollHeight;               
	            var dHeight =document.documentElement.scrollHeight;              
	            var height = Math.max(bHeight, dHeight);
	            iframe.height(bHeight);           
	        } catch (ex) { }       
	}       
	window.setInterval("reinitIframe()", 200);//定时去检查iframe的高度,这样保证时时都是自动高了
</script>
</body>
</html>