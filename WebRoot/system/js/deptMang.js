//angular 分页展示数据
var mydept = angular.module('dept', []);
mydept.controller('deptCtrl',function($scope,$http){
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
        $http.get("../dept/queryDept.do?currentPage="+page+"&pageSize="+size).success(function(Data){  
        	 		        	             	
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
	/*查询所有部门*/
	$http.get("../dept/queryDept.do").success(function(Data){
     	$scope.deptname = Data.rows;
    }); 
	
});


//增加
function addDept(){
	 var deptParaname=$('#deptParaname option:selected').val();
	 var deptname = $('#deptname').val();
	 var deptcode = $('#deptcode').val();
	if (deptParaname =="" || deptParaname== "请选择部门") {		
		$('#deptparaidError').html('请选择部门！')
		return false;
	}	
	if (deptname =="" || deptname== null) {		
		$('#deptnameError').html('部门名称不能为空！')
		return false;
	}
	$.ajax({
		url: '../dept/saveOrUpdate.do',
		type: 'POST',
		data: {
			deptparaid:deptParaname,
			deptname:deptname,
			deptcode:deptcode
		},			
		success: function(data) {			   
			   if(data=="success"){	
				   window.location.reload();
					
			   }else if(data=="0"){					   
				   $('#deptParanameError').html('参数为空！');
			   }
			   else if(data=="1"){
				   $('#deptParanameError').html('用户不存在！');
				   
			   }
			   else if(data=="2"){
				   $('#deptParanameError').html('密码错误！');
			   }
			   else if(data=="3"){
				   $('#deptParanameError').html('旧密码不匹配！');
			   }
			   else if(data=="4"){
				   $('#deptParanameError').html(data);
			   }
			   else if(data=="5"){
				   $('#deptParanameError').html('用户已经存在！');
				   window.location.reload();
			   }else {
				 
			   }
		 }
	}) 	
			 
}

//全选
$('.all').click(function() {
	if(this.checked) {
		$("input[type='checkbox']").prop("checked", true);
	} else {
		$("input[type='checkbox']").prop("checked", false);
	}
});

var checkboxval = [];
var dataid =null;
function changeDept(){
	
	checkboxval = [];//清空上次的修改条数
	
	$("input[type='checkbox']:not(:first):checked").each(function() {
		
		var boxid = $(this).attr('class');
		
		checkboxval.push(boxid);
		
	});
	
	dataid =$('input[type="checkbox"]:checked').parent().parent().attr('data-id');//获取要修改的信息ID
	
	
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
		changeText(dataid);
	}
}
//修改
//==============获取循环添加的tr展示修改数据====================
function changeText(objTag){
	
	var deptname=$('tr[data-id='+'"'+objTag+'"'+']').children('td').eq(2).html();
	var deptcode=$('tr[data-id='+'"'+objTag+'"'+']').children('td').eq(3).html();
	var deptParaname=$('tr[data-id='+'"'+objTag+'"'+']').children('td').eq(4).html();
	
	$('#change-dept').show();	
	
	$('#ch-deptParaname option:contains("+'+deptParaname+'+")').remove();
	
	$('#ch-deptParaname option:selected').html(deptParaname);
	
	$('#ch-deptcode').val(deptcode);
	$('#ch-deptname').val(deptname);
};
//修改保存
function edit(){		
	var deptparaid=$('#ch-deptParaname option:selected').val();
	var deptcode = $('#ch-deptcode').val();
	var deptname = $('#ch-deptname').val();
 	$.ajax({        			
		   type: 'POST',
		   data: {
			 deptid:dataid,
			 deptparaid:deptparaid,
			 deptcode:deptcode,
			 deptname:deptname
		   },
		   url: "../dept/saveOrUpdate.do",		   
		   success: function(data) {
			   if(data=="success"){	
				   window.location.reload();
					
			   }else if(data=="0"){					   
				   $('#deptParanameError').html('参数为空！');
			   }
			   else if(data=="1"){
				   $('#deptParanameError').html('用户不存在！');
				   
			   }
			   else if(data=="2"){
				   $('#deptParanameError').html('密码错误！');
			   }
			   else if(data=="3"){
				   $('#deptParanameError').html('旧密码不匹配！');
			   }
			   else if(data=="4"){
				   $('#deptParanameError').html(data);
			   }
			   else if(data=="5"){
				   $('#deptParanameError').html('用户已经存在！');
				   window.location.reload();
			   }else {
				 
			   }
		 }
	});
}
//确定删除
var ids = [];	
function show() {		
	$("input[type='checkbox']:not(:first):checked").each(function() {
		var boxid = $(this).attr('class');
		ids.push(boxid);
	});	
	if(ids.length == 0) {
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
	var id = ids.join(",");
	$.ajax({
		type: "POST",
		url: '../dept/delete.do',
		data: {
			ids: id
		},
		success: function() {
			window.location.reload();
		}
	});
};
$('#quxiao').on('click',function(){
	$('.themisWrap').css('display','none');
})
