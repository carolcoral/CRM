//angular 分页展示数据
var mycourse = angular.module('course', []);
mycourse.controller('courseCtrl',function($scope,$http){   
  //配置
    $scope.count = 0;//总条数
    $scope.pageSize = 15;//每页15条
    //变量
    $scope.currentPage = 1;//当前页
    $scope.pageTotal =0; // 总页数 
    $scope.pages = [];
    //初始化第一页
    forPages($scope.currentPage,$scope.pageSize,function(){});
    //获取数据
     function forPages (page,size,callback){
        $http.get("../course/queryCourse.do?currentPage="+page+"&pageSize="+size).success(function(Data){  
        	 		        	             	
         	$scope.count=Data.total;
         	$scope.list = Data.rows;
            $scope.currentPage = Data.currentPage;
            $scope.pageTotal =Math.ceil($scope.count/$scope.pageSize);
            reloadPno();
            callback();           
        }); 
    }
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
    	forPages(page,$scope.pageSize,function(){ });
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
//新增课程
function saveCourse(){	  
     var courseTeacherName = $('#courseTeacherName').val();
     var courseName = $('#courseName').val();
     var courseMoney = $('#courseMoney').val();
     var courseDescribe = $('#courseDescribe').val();
     var note = $('#note').val();
     
     var reg = /^[0-9]*$/;
     
     if(courseName==""||courseName==null){
    	 $('#courseNameError').html("课程名称不能为空");
    	 return false;
     }
     if(!reg.test(courseMoney)){         
         $('#courseMoneyError').html("请输入数字!");
    	 return false;
     } 
     
     $.ajax({
 		url: '../course/addCourse.do',
 		type: 'POST',
 		data: {
 			courseTeacherName:courseTeacherName,
 			courseName:courseName,
 			courseMoney:courseMoney,
 			courseDescribe:courseDescribe,
 			note:note
 		},			
 		success: function(data) {			   
 			   if(data=="success"){	
 				   window.location.reload();
 					
 			   }else if(data=="failed"){					   
 				  alert('请求失败！');
 			   }else {
 				 
 			   }
 		 }
 	})
     
}
//全选
$('#courseAll').click(function() {
	if(this.checked) {
		$("#course input[type='checkbox']").prop("checked", true);
	} else {
		$("#course input[type='checkbox']").prop("checked", false);
	}
});

//修改课程
var checkboxval = [];
var courseid =null;
function changecourse(){
	
	checkboxval = [];//清空上次的修改条数
	
	$("#course input[type='checkbox']:not(:first):checked").each(function() {
		
		var boxid = $(this).attr('class');
		
		checkboxval.push(boxid);
		
	});
	
	courseid =$('#course input[type="checkbox"]:checked').parent().parent().attr('data-id');//获取要修改的信息ID
	
	
	if(checkboxval.length == 0) {				
		$.winTip({
			title: "提示~~~",
			msg: "至少选择一条信息",
			src:"./system/img/tishi.png"
		});
		
	}else if(checkboxval.length > 1){
		$.winTip({
			title: "提示~~~",
			msg: "请选择一条信息修改",
			src:"./system/img/tishi.png"
		});
	}else {		
		changeText(courseid);
	}
}
//==============获取循环添加的tr展示修改数据====================
function changeText(objTag){
	
	var courseTeacherName=$('tr[data-id='+'"'+objTag+'"'+']').children('td').eq(2).html();
	var courseName=$('tr[data-id='+'"'+objTag+'"'+']').children('td').eq(3).html();
	var courseMoney=$('tr[data-id='+'"'+objTag+'"'+']').children('td').eq(4).html();
	var courseDescribe=$('tr[data-id='+'"'+objTag+'"'+']').children('td').eq(5).html();
	var note=$('tr[data-id='+'"'+objTag+'"'+']').children('td').eq(6).html();
	$('#courseTit').html("课程管理_修改")
	$('#addCourse').show();	
	$('.saveCourse').css("display","none");//保存新增按钮隐藏
	$('.changeCourse').css({"display":"block","float": "left"});//保存修改按钮显示	
	$('#courseTeacherName').val(courseTeacherName);
	$('#courseName').val(courseName);	
	$('#courseMoney').val(courseMoney);	
	$('#courseDescribe').val(courseDescribe);
	$('#note').val(note);
};
//修改保存课程
function savChangeCourse(){		
	var courseTeacherName = $('#courseTeacherName').val();
	var courseName = $('#courseName').val();	
	var courseMoney = $('#courseMoney').val();	
	var courseDescribe = $('#courseDescribe').val();
	var note = $('#note').val();
 	$.ajax({        			
		   type: 'POST',
		   data: {
			 courseid:courseid,
			 courseTeacherName:courseTeacherName,
			 courseName:courseName,
			 courseMoney:courseMoney,
			 courseDescribe:courseDescribe,
			 note:note
		   },
		   url: "../course/addCourse.do",		   
		   success: function(data) {
			   if(data=="success"){	
 				   window.location.reload();
 					
 			   }else if(data=="failed"){					   
 				  alert('请求失败！');
 			   }else {
 				 
 			   }
		 }
	});
}

//确定删除课程
var delCourseid = [];	
function show() {
		
	$("#course input[type='checkbox']:not(:first):checked").each(function() {
		var boxid = $(this).attr('class');
		delCourseid.push(boxid);
	});	
	if(delCourseid.length == 0) {
		$.winTip({
			title: "提示~~~",
			msg: "至少选择一条信息",
			src:"./system/img/tishi.png"
		});
	} else {
		$('.themisWrap').css('display','block');
	}
};
function del() {
	$('.themisWrap').css('display','none');
	var delCourseids = delCourseid.join(",");	
	$.ajax({
		type: "POST",
		url: '../course/deleteCourse.do',
		data: {
			courseids:delCourseids
		},
		success: function() {
			window.location.reload();
		}
	});
};
$('#quxiao').on('click',function(){
	$('.themisWrap').css('display','none');
})