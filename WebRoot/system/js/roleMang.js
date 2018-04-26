//angular 分页展示数据
var myrole = angular.module('role', []);
myrole.controller('roleCtrl',function($scope,$http){
	
	$scope.show=true;	
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
        $http.get("../role/queryAllRole.do?currentPage="+page+"&pageSize="+size).success(function(Data){  
        	 		        	             	
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
	/*查询所有部门*/
	$http.get("../dept/queryDept.do").success(function(Data){
     	$scope.deptname = Data.rows;    	
    });	
	
	
});
//增加
function addRole(){
	var rolename = $('#rolename').val();
	var deptname=$('#deptname option:selected').val();		 
	if (rolename =="" || rolename== null) {		
		$('#rolenameError').html('部门名称不能为空！')
		return false;
	}
	if (deptname =="" || deptname== "请选择部门") {		
		$('#deptnameError').html('请选择部门！')
		return false;
	}	
	$.ajax({
		url: '../role/addOrUpdateRole.do',
		type: 'POST',
		data: {
			rolename:rolename,
			deptid:deptname
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
		$("#list input[type='checkbox']").prop("checked", true);
	} else {
		$("#list input[type='checkbox']").prop("checked", false);
	}
});

var checkboxval = [];
var dataid =null;
function changeRole(){
	
	checkboxval = [];//清空上次的修改条数
	
	$("#list input[type='checkbox']:not(:first):checked").each(function() {
		
		var boxid = $(this).attr('class');
		
		checkboxval.push(boxid);
		
	});
	
	dataid =$('#list input[type="checkbox"]:checked').parent().parent().attr('data-id');//获取角色ID	
	
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
//左侧导航菜单收缩展开

$(document).on('click','.arrow', function(event) {
	$(this).stop().toggleClass('glyphicon-chevron-right');
	$(this).parent().siblings().stop().slideToggle(400);
});
//权限分配
function assess(){
	
	checkboxval = [];//清空上次的修改条数
	
	$("#list input[type='checkbox']:not(:first):checked").each(function() {
		
		var boxid = $(this).attr('class');
		
		checkboxval.push(boxid);
		
	});
	
	var dataid =$('#list input[type="checkbox"]:checked').parent().parent().attr('data-id');//获取角色ID		
	
	if(checkboxval.length == 0) {				
		$.winTip({
			title: "提示~~~",
			msg: "至少选择一条信息",
			src:"./system/img/tishi.png"
		});
		
	}else {	
		
		$.ajax({        			
			   type: 'GET',
			   data: {				 
				 roleid:dataid
			   },
			   url: "../rolemenu/queryAllMenuAndSelected.do",		   
			   success: function(data) {	
				   $('.assessCon').html('');
				   var dataObj=eval("("+data+")");//转换为json对象
				   
				   var list = dataObj.rows;			   
				  
				   $.each(list, function(index, array) {			        	
			        	//循环获取数据
		                var menuParaname = list[index].menuname|| "";	
		                var dataId = list[index].menuid|| "";	
		                var children = list[index].children;
		                var selected= list[index].selected;
		                
		                selected = eval(selected.toLowerCase());//转成布尔值		                
		                
		                var mytree= '<div class="assesstree">'+
							'<p class="assessTit">'+
								'<span class="glyphicon glyphicon-chevron-down arrow" aria-hidden="true"></span>'+
								'<input type="checkbox" data-id="'+dataId+'" class="parent"/>'+menuParaname+'</p>'+
								'<ul class="assessList">'+'</ul>'
						'</div>'
								                
			        	$('.assessCon').append(mytree);			             
		                
		                $(":checkbox[data-id='"+dataId+"']").prop("checked",selected);		                
		                
		                tree(index,index);		                
		                
				    });
				   function tree(num1,num2){
					   $.each(list[num1].children, function(index, item) {	
						   
						   checked = eval(item.selected.toLowerCase());//转成布尔值
				        	//循环获取子集数据	                		                	
		                	var li = '<li>'+'<input type="checkbox" data-id="'+item.menuid+'" />'+item.menuname+'</li>';	
		                	
			        		$('.assesstree .assessList').eq(num2).append(li);			        		
			        		
			        		 $(":checkbox[data-id='"+item.menuid+"']").prop("checked",checked);
					    });
				   }
				  
			 } 
		})		
		
		$('#change-access').show();
	}
}

//保存权限分配
var menuid = [];
function saveAssess(){	
		
	menuid = [];//清空上次的修改条数
	
	$(".assessCon input[type='checkbox']:checked").each(function() {
		
		var boxid = $(this).attr('data-id');		
		menuid.push(boxid);		
	});
	
	var menuids = menuid.join(",");
	var dataid =$('#list input[type="checkbox"]:checked').parent().parent().attr('data-id');//获取角色ID	
	$.ajax({
		url: '../rolemenu/assignMenu.do',
		type: 'POST',
		data: {
			roleid:dataid,
			menuid:menuids
		},			
		success: function(data) {			   
			  if(data=="success"){	
				   window.location.reload();
					
			   }else if(data=="0"){					   
				   $('#deptParanameError').html('参数为空！');
			   }			   
		 }
	})
}
//修改
//==============获取循环添加的tr展示修改数据====================
function changeText(objTag){
	var roleid=$('tr[data-id='+'"'+objTag+'"'+']').attr('data-id');
	var rolename=$('tr[data-id='+'"'+objTag+'"'+']').children('td').eq(2).html();
	var deptname=$('tr[data-id='+'"'+objTag+'"'+']').children('td').eq(3).html();
	var deptid=$('tr[data-id='+'"'+objTag+'"'+']').children('td').eq(3).attr('data-id');
	
	$('#change-role').show();	
	$('#ch-rolename').val(rolename);
	$('#ch-rolename').attr("data-id",""+roleid);
	$('#ch-deptname option:selected').html(deptname);	
	$('#ch-deptname option:selected').val(deptid);
};
//修改保存
function edit(){
	var roleid = $('#ch-rolename').attr('data-id');
	var rolename = $('#ch-rolename').val();
	var deptid =  $('#ch-deptname option:selected').val();
 	$.ajax({        			
		   type: 'POST',
		   data: {
			 deptid:deptid,
			 roleid:roleid,
			 rolename:rolename
		   },
		   url: "../role/addOrUpdateRole.do",		   
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
	
	ids = [];//清空上次的修改条数
	
	$("#list input[type='checkbox']:not(:first):checked").each(function() {
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
		url: '../role/deleteRole.do',
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
