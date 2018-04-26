 //angular 分页展示数据
var mymenu = angular.module('menu', []);
mymenu.controller('menuCtrl',function($scope,$http){
    //获取数据    
    $http.get("../menu/queryAllMenu.do").success(function(Data){
    	$scope.list = Data.rows;
    	
     	console.info($scope.list);
    });
    
    $scope.Financial_show=true;    
    $scope.isActive = true;
});
//增加
function addMenu(){
	 var menuParaname=$('#menuParaname option:selected').val();	
	 var menuno = $('#menuno').val();
	 var menuname = $('#menuname').val();
	 var menucode = $('#menucode').val();
	 var menuurl = $('#menuurl').val();	
	 var menuimgurl = $('#menuimgurl').val();
	 	
	if (menuname =="" || menuname== null) {		
		$('#menunameError').html('菜单名称不能为空！')
		return false;
	}		
	$.ajax({
		url: '../menu/addOrUpdateMenu.do',
		type: 'POST',
		data: {
			menuparaid:menuParaname,
			menuno:menuno,
			menuname:menuname,
			menucode:menucode,
			menuurl:menuurl,
			menuimgurl:menuimgurl
		     
		},			
		success: function(data) {			   
			   if(data=="success"){	
				   window.location.reload();					
			   }else if(data=="0"){					   
				   $('#usernameError').html('参数为空！');
			   }
			   else if(data=="1"){
				   $('#usernameError').html('用户不存在！');
				   
			   }
			   else if(data=="2"){
				   $('#usernameError').html('密码错误！');
			   }
			   else if(data=="3"){
				   $('#usernameError').html('旧密码不匹配！');
			   }
			   else if(data=="4"){
				   $('#usernameError').html(data);
			   }
			   else if(data=="5"){
				   $('#usernameError').html('用户已经存在！');
				   window.location.reload();
			   }else {
				 
			   }
		 },
		 error: function (data){
			 console.log(data);
		 }
	}) 	
			 
}

//修改
var dataid = null;
function changeMenu(){
	if($('.menutree').find('p').hasClass("menuactive")) {
		dataid = $(".menutree p[class$='active']").attr("data-id");
		changeText(dataid);
		$('#meunchange').show();
	}else {	
		$.winTip({
			title: "提示~~~",
			msg: "至少选择一条信息",
			src:"./system/img/tishi.png"
		});
	}
}
//==============获取循环添加的tr展示修改数据====================
function changeText(objTag){	
	var html = [];	
	$('p[data-id='+'"'+objTag+'"'+']').children('b').each(function() {
		var boxid = $(this).html();
		html.push(boxid);
	});	
	$('#ch-upmenu option:selected').html(html[0]);	
	$('#ch-menuno').val(html[1]);
	$('#ch-menuname').val(html[2]);
	$('#ch-menucode').val(html[3]);
	$('#ch-menuurl').val(html[4]);		
	$('#ch-menuimgurl').val(html[5]);	
};

function meunsave(){
	var menuParaname = $('#ch-upmenu option:selected').val();
	var menuno = $('#ch-menuno').val();
	var menuname = $('#ch-menuname').val();
	var menucode = $('#ch-menucode').val();
	var menuurl = $('#ch-menuurl').val();		
	var menuimgurl = $('#ch-menuimgurl').val();
	
 	$.ajax({        			
	   type: 'POST',
	   data: {
		   menuid:dataid,
		   menuparaid:menuParaname,		   
		   menuno:menuno,
		   menuname:menuname,
		   menucode:menucode,
		   menuurl:menuurl,
		   menuimgurl:menuimgurl
	   },
	   url: "../menu/addOrUpdateMenu.do",		   
	   success: function(data) {
		   console.log(data);
		   if(data==5){
				console.log("用户已经存在!") ; 
		   }else {
			 console.log("修改成功") ;
			 window.location.reload();
		   }
	   }
	});
}


var ids = [];	
function showTip() {		
	$(".menutree p[class$='active']").each(function() {
		var boxid = $(this).attr("data-id");
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

//确定删除
function del() {
	$('.themisWrap').css('display','none');
	var id = ids.join(",");	
	$.ajax({
		type: "POST",
		url: '../menu/delete.do',
		data: {
			ids: id
		},
		success: function(data) {
			   if(data=="success"){	
				   window.location.reload();					
			   }else{					   
				   $.winTip({
						title: "提示~~~",
						msg: ""+data,
						src:"./system/img/tishi.png"
					});
			   }
			  
		}
	});
};
$('#quxiao').on('click',function(){
	$('.themisWrap').css('display','none');
})





