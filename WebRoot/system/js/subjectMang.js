
//angular 分页展示数据
var mysubject = angular.module('subject', []);
mysubject.controller('subjectCtrl',function($scope,$http){
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
        $http.get("../subject/querySubject.do?currentPage="+page+"&pageSize="+size).success(function(Data){  
        	 		        	             	
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
	 	
	};
	//查询所有课程
	$http.get("../course/queryCourse.do").success(function(Data){
    	$scope.course = Data.rows;
    });
	
});
//全选
$('.all').click(function() {
	if(this.checked) {
		$("#sbujectTable input[type='checkbox']").prop("checked", true);
	} else {
		$("#sbujectTable input[type='checkbox']").prop("checked", false);
	}
});
/*新增科目*/
function addSubject(){
	var courseid = $('#courseName option:selected').val();
	var subjectname = $('#subjectname').val();
	var subjectcode = $('#subjectcode').val();
	var note = $('#note').val();
	$.ajax({        			
		   type: 'POST',
		   data: {
			 note:note,
			 courseid:courseid,
			 subjectname:subjectname,
			 subjectcode:subjectcode
		   },
		   url: "../subject/addOrUpdateSubject.do",		   
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
//修改科目
var subjectVal = [];
var sbujectId =null;
function changeSub(){
	
	subjectVal = [];//清空上次的修改条数
	
	$("#sbujectTable input[type='checkbox']:not(:first):checked").each(function() {
		
		var boxid = $(this).attr('class');
		
		subjectVal.push(boxid);
		
	});
	
	sbujectId =$('#sbujectTable input[type="checkbox"]:checked').attr('class');//获取要修改的信息ID
	
	
	if(subjectVal.length == 0) {				
		$.winTip({
			title: "提示~~~",
			msg: "至少选择一条信息",
			src:"./system/img/tishi.png"
		});
		
	}else if(subjectVal.length > 1){
		$.winTip({
			title: "提示~~~",
			msg: "请选择一条信息修改",
			src:"./system/img/tishi.png"
		});
	}else {		
		changesbujectText(sbujectId);
	}
}

//==============获取循环添加的tr展示修改数据====================
function changesbujectText(objTag){	
	var coursename=$('tr[data-id='+'"'+objTag+'"'+']').children('td').eq(2).html();
	var courseid=$('tr[data-id='+'"'+objTag+'"'+']').children('td').eq(2).attr('data-id');
	var subjectname=$('tr[data-id='+'"'+objTag+'"'+']').children('td').eq(3).html();	
	var subjectcode=$('tr[data-id='+'"'+objTag+'"'+']').children('td').eq(4).html();
	var note=$('tr[data-id='+'"'+objTag+'"'+']').children('td').eq(5).html();
	
	$('#subject').show();
	$('.addnewTit').html("科目管理_修改");
	$('#saveSubject').css("display","none");//保存新增按钮隐藏
	$('#saveChSub').css({"display":"block","float": "left"});//保存修改按钮显示	
	$('#courseName option:selected').html(coursename);
	$('#courseName option:selected').val(courseid);
	$('#subjectname').val(subjectname);
	$('#subjectcode').val(subjectcode);
	$('#note').val(note);
	var temp = '';  //去除重复元素
	$('#courseName option').each(function(){
		if(temp.indexOf($(this).html())!=-1)
		$(this).remove();
		temp += $(this).html();
	})
};
//保存修改科目
function saveChSubject(){	
	var subjectid = $('#sbujectTable input[type="checkbox"]:checked').attr('class');
	var courseid =  $('#courseName option:selected').val();	
	var subjectname = $('#subjectname').val();
	var subjectcode = $('#subjectcode').val();
	var note = $('#note').val();
 	$.ajax({        			
		   type: 'POST',
		   data: {			 
			 note:note,
			 courseid:courseid,
			 subjectid:subjectid,
			 subjectname:subjectname,
			 subjectcode: subjectcode
		   },
		   url: "../subject/addOrUpdateSubject.do",		   
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
//确定删除科目
var delsubject = [];	
function delShow() {	
	
	$("#sbujectTable input[type='checkbox']:not(:first):checked").each(function() {
		
		var boxid = $(this).attr('class');
		
		delsubject.push(boxid);
		
	});		
	if(delsubject.length == 0) {
		$.winTip({
			title: "提示~~~",
			msg: "至少选择一条信息",
			src:"./system/img/tishi.png"
		});
	} else {
		$('.themisWrap').css('display','block');
	}
};
function delSunject() {
	$('.themisWrap').css('display','none');
	var delsbujectids = delsubject.join(",");	
	$.ajax({
		type: "POST",
		url: '../subject/deleteSubject.do',
		data: {
			subjectids:delsbujectids
		},
		success: function() {			 	   
			window.location.reload();		
		}
	});
};
$('#quxiao').on('click',function(){
	$('.themisWrap').css('display','none');
})


