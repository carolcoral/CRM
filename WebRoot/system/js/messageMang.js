
//angular 分页展示数据
var mymessage = angular.module('message', []);
mymessage.controller('messageCtrl',function($scope,$http){
	
	$scope.send = function (status) {
	    var p = "";            	    
	    if (0 == status) {
	        p = '未发布';	        
	    } else if (1 == status) {
	        p = '已发布';
	    }
	    return p;
	};
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
        $http.get("../system/querySystemmessages.do?currentPage="+page+"&pageSize="+size).success(function(Data){  
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
});
//全选
$('.all').click(function() {
	if(this.checked) {
		$("#messageTable input[type='checkbox']").prop("checked", true);
	} else {
		$("#messageTable input[type='checkbox']").prop("checked", false);
	}
});
/*新增科目*/
function addMess(){	
	$('#messagetitle').val();
	$('#messageContent').val();
	$('.addnewTit').html("消息管理_增加");
	var messagetitle = $('#messagetitle').val();
	var messageContent = $('#messageContent').val();
	$.ajax({        			
		   type: 'POST',
		   data: {
			   messagetitle:messagetitle,
			   messageContent:messageContent
		   },
		   url: "../system/saveOrUpdateMessage.do",		   
		   success: function(data) {
			   if(data=="success"){				   
				   window.location.reload();
 					
 			   }else if(data=="0"){					   
 				  $.winTip({
					title: "提示~~~",
					msg: "参数为空",
					src:"./system/img/tishi.png"
 				  });
 			   }else {
 				 
 			   }
		 }
	});
}
//修改科目
var msgVal = [];
var msgId =null;
function changeMsg(){
	
	msgVal = [];//清空上次的修改条数
	
	$("#messageTable input[type='checkbox']:not(:first):checked").each(function() {
		
		var boxid = $(this).attr('class');
		
		msgVal.push(boxid);
		
	});
	
	msgId =$('#messageTable input[type="checkbox"]:checked').attr('class');//获取要修改的信息ID
	
	
	if(msgVal.length == 0) {				
		$.winTip({
			title: "提示~~~",
			msg: "至少选择一条信息",
			src:"./system/img/tishi.png"
		});
		
	}else if(msgVal.length > 1){
		$.winTip({
			title: "提示~~~",
			msg: "请选择一条信息修改",
			src:"./system/img/tishi.png"
		});
	}else {		
		changesbujectText(msgId);
	}
}

//==============获取循环添加的tr展示修改数据====================
function changesbujectText(objTag){	
	var messagetitle=$('tr[data-id='+'"'+objTag+'"'+']').children('td').eq(2).html();	
	var messageContent=$('tr[data-id='+'"'+objTag+'"'+']').children('td').eq(3).html();
	
	$('#newmessage').show();
	$('.addnewTit').html("消息管理_修改");
	
	$('#saveSubject').css("display","none");//保存新增按钮隐藏
	$('#saveChSub').css({"display":"block","float": "left"});//保存修改按钮显示	
	
	$('#messagetitle').val(messagetitle);
	$('#messageContent').val(messageContent);
};
//保存修改科目
function saveChMess(){	
	var messagetitle = $('#messagetitle').val();
	var messageContent = $('#messageContent').val();
	var note = $('#note').val();
 	$.ajax({        			
		   type: 'POST',
		   data: {			 
			 systemmessageId:msgId,
			 messagetitle:messagetitle,
			 messageContent:messageContent
		   },
		   url: "../system/saveOrUpdateMessage.do",		   
		   success: function(data) {
			   if(data=="success"){				   
 				   window.location.reload();				   
 			   }else if(data=="failed"){					   
 				  $.winTip({
 						title: "提示~~~",
 						msg: "参数为空",
 						src:"./system/img/tishi.png"
 	 				  });
 			   }else {
 				 
 			   }
		 }
	});
}
//确定删除科目
var sendid = [];	
function send(issend){	
	
	$("#messageTable input[type='checkbox']:not(:first):checked").each(function() {
		
		var boxid = $(this).attr('class');
		
		sendid.push(boxid);
		
	});
	
	msgId = $('#messageTable input[type="checkbox"]:checked').attr('class');//获取要修改的信息ID	
	
	if(sendid.length == 0) {				
		$.winTip({
			title: "提示~~~",
			msg: "至少选择一条信息",
			src:"./system/img/tishi.png"
		});
		
	}else if(sendid.length > 1){
		$.winTip({
			title: "提示~~~",
			msg: "请选择一条信息修改",
			src:"./system/img/tishi.png"
		});
	} else {
		$.ajax({
			type: "GET",
			url: '../system/sendMessage.do',
			data: {
				systemmessageId:msgId,
				issend:issend
			},
			success: function(data) {				
				window.location.reload();		
			}
		});
	}
};
