/*初始化时间插件*/
$('#taskStartTime').datetimepicker({
    lang:"ch",           //语言选择中文
    format:"Y-m",      //格式化日期
    timepicker:false,    //关闭时间选项
    validateOnBlur:false
});
/*初始化时间插件*/
$('#startTime').datetimepicker({
    lang:"ch",           //语言选择中文
    format:"Y-m-d",      //格式化日期
    timepicker:false,    //关闭时间选项
    validateOnBlur:false
});
/*初始化时间插件*/
$('#endTime').datetimepicker({
    lang:"ch",           //语言选择中文
    format:"Y-m-d",      //格式化日期
    timepicker:false,    //关闭时间选项
    validateOnBlur:false
});
var userid = document.getElementById("userid").value;
var deptid = document.getElementById("deptid").value;
var roleid = document.getElementById("roleid").value;

/*禁止文字复制*/
document.oncontextmenu=function(evt){ 
    evt.preventDefault(); 
} 

document.onselectstart=function(evt){ 
    evt.preventDefault(); 
};
var myresource = angular.module('resource', []);

myresource.controller('resourceCtrl',function($scope,$http){
   //初始化第一页
   forPages('../report/queryPerformanceAll.do',userid,deptid,roleid,function(){});
   moth('../report/queryPerformanceTodayMoth.do',userid,deptid,roleid,function(){});
   week('../report/queryPerformanceTodayWeek.do',userid,deptid,roleid,function(){});
   yestoday('../report/queryPerformanceYestoday.do',userid,deptid,roleid,function(){});
   
   forPagesnotAC('../report/queryPerformanceAllnotAC.do',userid,deptid,roleid,function(){});
   mothnotAC('../report/queryPerformanceTodayMothnotAC.do',userid,deptid,roleid,function(){});
   weeknotAC('../report/queryPerformanceTodayWeeknotAC.do',userid,deptid,roleid,function(){});
   yestodaynotAC('../report/queryPerformanceYestodaynotAC.do',userid,deptid,roleid,function(){});
   //获取数据
   function forPages (url,userid,deptid,roleid,callback){
      $http.get(url+"?deptid="+deptid+"&userid="+userid+"&roleid="+roleid).success(function(Data){
          
          $scope.count=Data.total;
          $scope.list = Data.rows;
        
	       angular.forEach($scope.list, function(array){
	       		$scope.listall=$scope.list.listall;//总资源量
		   });
      });
  	}
   
   function forPagesnotAC (url,userid,deptid,roleid,callback){
	      $http.get(url+"?deptid="+deptid+"&userid="+userid+"&roleid="+roleid).success(function(Data){
	          
	          $scope.count=Data.total;
	          $scope.list = Data.rows;
	        
		       angular.forEach($scope.list, function(array){
		       		$scope.listallnotAC=$scope.list.listallnotAC;//总资源量
			   });
	      });
	  	}


   
	function moth (url,userid,deptid,roleid,callback){
		$http.get(url+"?deptid="+deptid+"&userid="+userid+"&roleid="+roleid).success(function(Data){
      
	      $scope.count=Data.total;
	      $scope.list = Data.rows;
	    
	       angular.forEach($scope.list, function(array){
	       		
	       		$scope.listtodaymoth=$scope.list.listtodaymoth;//总资源量
	       		
		   });
	  });
	}
	function mothnotAC (url,userid,deptid,roleid,callback){
		$http.get(url+"?deptid="+deptid+"&userid="+userid+"&roleid="+roleid).success(function(Data){
      
	      $scope.count=Data.total;
	      $scope.list = Data.rows;
	    
	       angular.forEach($scope.list, function(array){
	       		
	       		$scope.listtodaymothnotAC=$scope.list.listtodaymothnotAC;//总资源量
	       		
		   });
	  });
	}
   function weeknotAC (url,userid,deptid,roleid,callback){
		$http.get(url+"?deptid="+deptid+"&userid="+userid+"&roleid="+roleid).success(function(Data){
     
	      $scope.count=Data.total;
	      $scope.list = Data.rows;
	      angular.forEach($scope.list, function(array){
	       	$scope.listtodayweeknotAC=$scope.list.listtodayweeknotAC;//总资源量
	       		
		  });
	  });
	}
   function week (url,userid,deptid,roleid,callback){
		$http.get(url+"?deptid="+deptid+"&userid="+userid+"&roleid="+roleid).success(function(Data){
    
	      $scope.count=Data.total;
	      $scope.list = Data.rows;
	      angular.forEach($scope.list, function(array){
	       	$scope.listtodayweek=$scope.list.listtodayweek;//总资源量
	       		
		  });
	  });
	}
	function yestoday (url,userid,deptid,roleid,callback){
		$http.get(url+"?deptid="+deptid+"&userid="+userid+"&roleid="+roleid).success(function(Data){
      
	      $scope.count=Data.total;
	      $scope.list = Data.rows;
	    
	       angular.forEach($scope.list, function(array){
	       		
	       		$scope.listyestoday=$scope.list.listyestoday;//总资源量
	       		
		   });
	  });
	}
	function yestodaynotAC (url,userid,deptid,roleid,callback){
		$http.get(url+"?deptid="+deptid+"&userid="+userid+"&roleid="+roleid).success(function(Data){
      
	      $scope.count=Data.total;
	      $scope.list = Data.rows;
	    
	       angular.forEach($scope.list, function(array){
	       		
	       		$scope.listyestodaynotAC=$scope.list.listyestodaynotAC;//总资源量
	       		
		   });
	  });
	}
	$scope.course = function (url,tag){
		var course = document.getElementById("selectmoth");
		var courseid = course.options[course.selectedIndex].value; // 选中值
		if(courseid ==""){
			alert("请选择课程")
		}
  		$http.get(url+"?deptid="+deptid+"&userid="+userid+"&courseid="+courseid).success(function(Data){  
  		   $scope.list = Data.rows;
	       angular.forEach($scope.list, function(array){
	       		
	       		$scope.listcourse=$scope.list.listcourse;//总资源量 
		   });
    	});                                                                                                                                                                                                                                                                                                                                                                                               
  	}
  	$scope.listtimefilter = function (url){
  		var moth = document.getElementById("taskStartTime").value;
  		$http.get(url+"?deptid="+deptid+"&userid="+userid+"&moth="+moth).success(function(Data){  
  		   $scope.list = Data.rows;
	       angular.forEach($scope.list, function(array){
	       		
	       		$scope.listmoth=$scope.list.listmoth;//总资源量 
		   });
    	});                                                                                                                                                                                                                                                                                                                                                                                               
  	}
  	$scope.weixintimefilter = function (url){
  		var weixinmoth = document.getElementById("weixinTime").value;
  		$http.get(url+"?deptid="+deptid+"&userid="+userid+"&moth="+weixinmoth).success(function(Data){  
  		   $scope.list = Data.rows;
	       angular.forEach($scope.list, function(array){
	       		
	       		$scope.listmoth=$scope.list.listmoth;//总资源量 
		   });
    	});                                                                                                                                                                                                                                                                                                                                                                                               
  	}
  	//业绩明细
  	var listreport = document.getElementById("listreport");
  	var detailreport = document.getElementById("detailreport");
	var perdetail = document.getElementById("perdetail");
	var perlist = document.getElementById("perlist");
  	$scope.performanceDetail = function (url,userid){
  		var startTime = document.getElementById("startTime").value;
  		var endTime = document.getElementById("endTime").value;
  		if((startTime != "" && startTime != null) || (endTime != "" && endTime != null)){
  			params = url+"?startTime="+startTime+"&endTime="+endTime;
  		}else if (userid != "" && userid != null){
  			params = url+"?userid="+userid;
  			detailreport.style.display = "block";
  			addClass(perdetail, "current")
  			listreport.style.display = "none";
  			removeClass(perlist, "current")
  			$(window.parent.document).find("#iframepage").height(0);
  		}else {
  			params = url;
  		}
  		$http.get(params).success(function(Data){ 
  		  $scope.PerformanceDetail = Data.rows;
    	});                                                                                                                                                                                                                                                                                                                                                                                               
  	}
  	function hasClass( elements,cName ){    
  	    return !!elements.className.match( new RegExp( "(\\s|^)" + cName + "(\\s|$)") );   
  	}; 
  	function addClass( elements,cName ){    
  	    if( !hasClass( elements,cName ) ){    
  	        elements.className += " " + cName;    
  	    };    
  	};
  	function removeClass( elements,cName ){    
  	    if( hasClass( elements,cName ) ){    
  	        elements.className = elements.className.replace( new RegExp( "(\\s|^)" + cName + "(\\s|$)" ), " " );  
  	    };    
  	}; 
})

//切换业绩明细
$('.listnav>li').on('click', function(event) {
	$(this).addClass('current').siblings().removeClass('current')
	$('.reportWrap>div').eq($(this).index()).css('display','block').siblings().css('display','none');
});

//导出只有主管可见
if(roleid=="2"||roleid=="4"||roleid=="6"||roleid=="8"||roleid=="10"||roleid=="12"||roleid=="14"||roleid=="16"||roleid=="18"||roleid=="20"||roleid=="22"||roleid=="24"||roleid=="26"||roleid=="31"||roleid=="32"||roleid=="35"||roleid=="36"||roleid=="49"||roleid=="51"){
	$('#filterExport').css('visibility','visible');
}
$('#filterExport').on('click',function(){
	var startTime = document.getElementById("startTime").value;
	var endTime = document.getElementById("endTime").value;
	excelexport(startTime,endTime);
})
function excelexport(startTime,endTime){
	var exportExcel = "export_excel";
	if (startTime != null && startTime !="" && endTime != null && endTime !="") {
		dataParams = {
			startTime:startTime,
			endTime:endTime			 			
		};
		excel(dataParams)
	}else{
		dataParams = {
				
		};
		excel(dataParams)
	}
	
}
function excel(dataParams){
	var params = $.param(dataParams);
	
	var url = '../report/exportPerformanceDetail.do'+"?"+params;
	
	$('<form method="POST" action="' + url + '"></form>').appendTo('body').submit().remove();
	
	delete dataParams[exportExcel];
}
//ifram高度
var iframe = $(window.parent.document).find("#iframepage");
iframe.height(0);  
function reinitIframe() {          
	 bHeight =document.body.scrollHeight;   
     iframe.height(bHeight);     
}       
window.setInterval("reinitIframe()", 200);//定时去检查iframe的高度,这样保证时时都是自动高了